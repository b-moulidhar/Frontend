package com.valtech.poc.sms.controller;

import java.util.List;
import java.util.Map;

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

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.service.EmployeeService;

@Controller
@RequestMapping("/employee")
@CrossOrigin(origins = "http://10.191.80.112/:3000")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ResponseBody
	@GetMapping("/profileDetailsEmployee/{eId}")
    public Employee getEmployeeById(@PathVariable int eId) {
        return employeeService.getEmployeeByeId(eId);
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
