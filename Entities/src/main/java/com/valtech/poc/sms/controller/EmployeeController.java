package com.valtech.poc.sms.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.valtech.poc.sms.dao.EmployeeDAOImpl;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.service.EmployeeService;

@Controller
@RequestMapping("/employee")
@CrossOrigin(origins = "http://10.191.80.112/:3000")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@ResponseBody
	@GetMapping("/profileDetailsEmployee/{eId}")
	public Employee getEmployeeById(@PathVariable int eId) throws Exception {
		try {
			return employeeService.getEmployeeByeId(eId);
		} catch (Exception e) {
			logger.error("An error occurred while fetching employee with id {}", eId, e);
			throw new Exception("An error occurred while fetching employee with id " + eId, e);
		}
	}
	
	@ResponseBody
	@GetMapping("/getAllManagers/{eId}")
	public  List<Employee> getAllEmployees(@PathVariable ("eId") int eId) {
		return employeeService.getAllEmployees(eId);
		
	}
	
	@ResponseBody
	@GetMapping("/getAllEmployeesUnderTheManager/{eId}")
	public List<Map<String, Object>>getAllEmployeesUnderTheManager(@PathVariable ("eId") int eId){
		try {
			return employeeService.getAllEmployeesUnderTheManager(eId);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Employee details not found " + eId);
		}
	}
}
