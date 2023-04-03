package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Map;

import com.valtech.poc.sms.entities.Employee;

import com.valtech.poc.sms.entities.Manager;
import com.valtech.poc.sms.entities.User;

public interface UserService {

	String login(int empId, String pass);

	User findByEmpId(int empId);

	void register(int empId, String pass, Employee empDetails);

	User findByEmail(String email);

//	int getMidByMname(String managerName);

	void saveEmployee(Employee employee, Manager manager);

	User saveUser(User user, Employee employee);

	int getRidByRoleName(String role);

	void saveUserRoles(int uId, int rId);

	void saveManager(Manager mng);

	Manager getManagerByMname(String managerName, Employee emp);

	void deleteEmployee(Employee emp);

	User findByEId(int eId);

	int getMidByName(String managerName);
	User save(User user);

	List<String> getManagerNames();

//	Manager getManagerByMname(String managerName);


//	void saveEmployee(Employee employee, int mid);

}