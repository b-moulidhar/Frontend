package com.valtech.poc.sms.service;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.User;

public interface UserService {

	User findByEmail(String email);

	int getMidByMname(String managerName);

	void saveEmployee(Employee employee, int mId);

	void saveUser(User user, Employee employee);

	int getRidByRoleName(String role);

	void saveUserRoles(int uId, int rId);

	void saveManager(int mId, int eId);


}