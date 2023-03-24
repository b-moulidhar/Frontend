package com.valtech.poc.sms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/managerByEmpId/{empId}")
	public Employee managerProfileInfo(@PathVariable("empId") int empId) throws SQLException {
		System.out.println(managerService.getManagerByEmpId(empId));
		return managerService.getManagerByEmpId(empId);
	}
}
