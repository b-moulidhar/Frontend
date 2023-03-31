package com.valtech.poc.sms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.service.EmployeeService;
import com.valtech.poc.sms.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/managerByEmpId/{empId}")
	public ResponseEntity<Employee> managerProfileInfo(@PathVariable("empId") int empId) throws SQLException {
		Employee employee=managerService.getManagerByEmpId(empId);
		return ResponseEntity.ok().body(employee);
	}
	
	@ResponseBody
	@GetMapping("/GetAllManagers")
	public List<String> ManagerList() {
		return managerService.getAllManagerNames();
		
	}
	
	@GetMapping("/profileDetailsManager/{mgrId}")
    public Employee getEmployeeById(@PathVariable int eId) {
        return employeeService.getEmployeeByeId(eId);
    }
	
    	
}
