package com.valtech.poc.sms.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valtech.poc.sms.dao.SeatBookingDao;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.repo.UserRepo;
import com.valtech.poc.sms.service.AdminService;
import com.valtech.poc.sms.service.AttendanceService;
import com.valtech.poc.sms.service.EmployeeService;
import com.valtech.poc.sms.service.SeatBookingService;
import com.valtech.poc.sms.service.UserService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	SeatBookingService seatBookingService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	UserService userService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	SeatBookingDao seatBookingDao;

	@Autowired
	AttendanceService attendanceService;

	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@ResponseBody
	@GetMapping("/foodCount/{ftDate}")
	public int getFoodCount(@PathVariable("ftDate") String ftDate) {
		logger.info("Fetching the food count");
		String foodDate = ftDate + " 00:00:00";
		int count = adminService.getFoodCount(foodDate);
		return count;
	}

	@Autowired
	SeatsBookedRepo seatsBookedRepo;

	// This method handles the punch out of an employee
	@ResponseBody
	@GetMapping("/checkout")
	public String checkOut(@RequestParam("empId") int empId) {
		// Find the user associated with the employee ID
		User usr = userService.findByEmpId(empId);
		// Get the employee details
		Employee emp = usr.getEmpDetails();
		// Find the current seat booking details for the employee
		SeatsBooked sb = seatBookingService.findCurrentSeatBookingDetails(emp);
		// Get the current date and time
		LocalDateTime now = LocalDateTime.now();
		// Format the date and time as a string with a specific format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(formatter.format(now), formatter);
		// Set the current seat booking status to false and set the punch out time to the current date and time
		sb.setCurrent(false);
		sb.setPunchOut(dateTime);
		// Save the updated seat booking details to the database
		seatsBookedRepo.save(sb);
		// Automatically regularize the attendance for the seat booking
		attendanceService.automaticRegularization(sb.getSbId());
		return "checked out";
	}


	// This method retrieves the current passcode for an employee's seat booking
	@ResponseBody
	@GetMapping("/viewPass/{eId}")
	public String viewPasscode(@PathVariable("eId") int eId) {
		// Find the employee associated with the employee ID
		Employee emp = employeeService.findById(eId);
		System.out.println(emp.getEmpName());
		// Find the current seat booking details for the employee
		SeatsBooked sb = seatBookingService.findCurrentSeatBookingDetails(emp);
		String code = sb.getCode();
		System.out.println(code);
		return code;
	}

	// This method verifies a QR code for an employee's seat booking
	@ResponseBody
	@PostMapping("/qr/verification/{eId}")
	public boolean verifyQrCode(@PathVariable("eId") int eId, @RequestParam("code") String code) {
		// Call the AdminService to verify the QR code
		boolean b = adminService.verifyQr(eId, code);
		return b;
	}


	@ResponseBody
	@GetMapping("/qr/codeGenerator/{empId}")
	public String getCodeForQrGeneration(@PathVariable("empId") int empId) {
		// call function which returns "code" from seat_booked table based on current
		// status for this empId
		String qrCodeKey = adminService.generateQrCode(empId);// this generates new code everytime (for test purpose
																// only)
		return qrCodeKey;
	}

	@ResponseBody
	@GetMapping("/seatCount/{sbDate}")
	public int getCountBySbDate(@PathVariable("sbDate") String sbDate) {
		logger.info("Fetching the seat booked count");
		String SeatDate = sbDate + " 00:00:00";
		int count = adminService.getSeatBookedCount(SeatDate);
		return count;

	}

	@ResponseBody
	@GetMapping("/shiftStart")
	public List<String> findShiftStartTimings() {
		logger.info("fetching all the shift start timings");
		return adminService.findShiftStartTimings();
	}

	@ResponseBody
	@GetMapping("/shiftEnd")
	public List<String> findShiftEndTimings() {
		logger.info("fetching all the shift end timings");
		return adminService.findShiftEndTimings();
	}

	@ResponseBody
	@GetMapping("/roleNames")
	public List<String> findRoles() {
		logger.info("fetching all the roles");
		return adminService.findRoles();
	}

	@GetMapping("/{eId}")
	public Employee getEmployeeById(@PathVariable int eId) throws Exception {
		return employeeService.getEmployeeByeId(eId);
	}

	@ResponseBody
	@PutMapping("/registrationApproval/{empId}")
	public String RegistrationApproval(@PathVariable("empId") int empId) {
		User u = userRepo.findByEmpId(empId);
		if (u.isApproval() == false) {
			adminService.ApproveRegistration(u.getuId());
			return "Approved";
		} else {
			return "The User is Already Approved";
		}
	}

	@ResponseBody
	@DeleteMapping("/registrationDisapproval/{empId}")
	public String RegistrationDisApproval(@PathVariable("empId") int empId) {
		adminService.deleteUser(empId);
		return "DisApproved";

	}

	@ResponseBody
	@GetMapping("/registrationApprovalList")
	public List<Map<String, Object>> getRegistrationListForApproval() {
		logger.info("fetching the list of Approval Request");
		return adminService.getRegistrationListForApproval();
	}

	@ResponseBody
	@GetMapping("/profileDetailsAdmin/{admnId}")

	public Employee getAdminById(@PathVariable int eId) throws Exception {
		return employeeService.getEmployeeByeId(eId);
	}

	@ResponseBody
	@GetMapping("/foodCountBasedOnDates")
	public int getCountByDate(@RequestParam("sbDate") String sbDate) {
		String SeatDate = sbDate + " 00:00:00";
		int count = adminService.getCountOfFoodOpt(SeatDate);
		return count;
	}

	@ResponseBody
	@GetMapping("/test/sb/shift/{start}")
	public List<SeatsBooked> testList(@PathVariable("start") int start, @RequestParam("date") String date) {
//		return seatBookingDao.findSBIdByShiftTimingsAndDate(start, date);
		return seatBookingService.getSBBySTAndDate(start, date);
	}

	@ResponseBody
	@GetMapping("/foodCountBasedOnDates/{sbDate}")
	public int getCountByDates(@PathVariable("sbDate") String sbDate) {
		String SeatDate = sbDate + " 00:00:00";
		int count = adminService.getCountOfFoodOpt(SeatDate);
		return count;
	}

}