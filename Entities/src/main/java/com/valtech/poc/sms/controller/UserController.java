package com.valtech.poc.sms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public String SaveEmployee(@RequestBody EmployeeRequest employeeRequest) {
	    Employee employee = employeeRequest.getEmployee();
	    User user = employeeRequest.getUser();
	    String managerName = employeeRequest.getManagerName();
	    String role = employeeRequest.getRole();
	   
	    int mId=userService.getMidByMname(managerName);
		userService.saveEmployee(employee,mId);
		int eId=employee.geteId();
		userService.saveUser(user,employee);
		if(role=="Manager") {
			userService.saveManager(mId,eId);
		}
		int rId=userService.getRidByRoleName(role);
		int uId=user.getuId();
		userService.saveUserRoles(uId,rId);
		return "saved all data";
	  
	}


}
