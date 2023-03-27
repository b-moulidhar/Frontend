package com.valtech.poc.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ResponseBody
	@GetMapping("/getAllEmployees/{empID}")
	public  List<Employee> getAllEmployees(@PathVariable ("empID") int empID) {
		
		return employeeService.getAllEmployees(empID);
		
	}
}
