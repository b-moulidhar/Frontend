package com.valtech.poc.sms.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@ResponseBody
	@GetMapping("/checkout")
	public String checkOut(@RequestParam("empId") int empId) {
		User usr = userService.findByEmpId(empId);
		Employee emp = usr.getEmpDetails();
		SeatsBooked sb = seatBookingService.findCurrentSeatBookingDetails(emp);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(formatter.format(now), formatter);
//		LocalDateTime dateTime = formatter.format(now);
		sb.setCurrent(false);
		sb.setPunchOut(dateTime);
		seatsBookedRepo.save(sb);
		attendanceService.automaticRegularization(sb.getSbId());
		return "checked out";
	}

	@ResponseBody
	@GetMapping("/viewPass/{eId}")
	public String viewPasscode(@PathVariable("eId") int eId) {
//		User usr = userService.findByEId(eId);
//		Employee emp = usr.getEmpDetails();
		Employee emp = employeeService.findById(eId);
		System.out.println(emp.getEmpName());
		SeatsBooked sb = seatBookingService.findCurrentSeatBookingDetails(emp);
		System.out.println(sb);
		String code = sb.getCode();
		System.out.println(code);
		return code;
	}
	
	@ResponseBody
	@PostMapping("/qr/verification/{eId}")
	public boolean verifyQrCode(@PathVariable("eId") int eId, @RequestParam("code") String code) {
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
	@GetMapping("/foodCountWithJpa/{ftDate}")
	public int getCountByFtdate(@PathVariable("ftDate") String ftDate) {
		String foodDate = ftDate + " 00:00:00";
		return adminService.getCount(foodDate);
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
	public Employee getEmployeeById(@PathVariable int eId) {
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
    public Employee getAdminById(@PathVariable int eId) {
        return employeeService.getEmployeeByeId(eId);
    }
	
	
	@ResponseBody
	@GetMapping("/foodCountBasedOnDates")
	public int getCountByDate(@RequestParam("sbDate")String sbDate) {
		String SeatDate = sbDate + " 00:00:00";
		int count = adminService.getCountOfFoodOpt(SeatDate);
		return count;
	}
	
}