package com.valtech.poc.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;
import com.valtech.poc.sms.exception.ResourceNotFoundException;
import com.valtech.poc.sms.repo.AttendanceRepository;
import com.valtech.poc.sms.repo.EmployeeRepository;
import com.valtech.poc.sms.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;

	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@ResponseBody
	@GetMapping("/foodCount/{ftDate}")
	public int getFoodCount(@PathVariable("ftDate") String ftDate) {
		logger.info("Fetching the food count");
		int count=adminService.getFoodCount(ftDate);
	    return count;
	}
	
	@ResponseBody
	@GetMapping("/foodCountWithJpa/{ftDate}")
		public int getCountByFtdate(@PathVariable("ftDate")String ftDate) {
		    return adminService.getCount(ftDate);
		}
	
    @ResponseBody
	  @GetMapping("/seatCount/{sbDate}")
    public int getCountBySbDate(@PathVariable("sbDate")String sbDate) {
    	logger.info("Fetching the seat booked count");
    	int count=adminService.getSeatBookedCount(sbDate);
        return count;
    	
    }
	
	    @ResponseBody
	    @PostMapping("/attendanceRegularization")
	    public String saveAttendance(@RequestBody AttendanceTable attendance) {
	        Employee employee = employeeRepository.findById(attendance.geteId().geteId())
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found" ));
	        Manager manager = employee.getManagerDetails();
	        attendance.seteId(employee);
	        attendanceRepository.save(attendance);
	        return "saved";
	    }
}