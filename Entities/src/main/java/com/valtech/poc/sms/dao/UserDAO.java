package com.valtech.poc.sms.dao;

import java.util.List;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;
import com.valtech.poc.sms.entities.User;

public interface UserDAO {

//	int getMidByMname(String managerName);
//
//	void saveEmployee(Employee employee, int mId);

//	void saveUser(User user, Employee eId);

	int getRidByRoleName(String role);

	void saveUserRole(int uId, int rId);


	void saveManager(Manager mng);

	Manager getMidByMname(String managerName, Employee emp);

	int checkIfEmpIdExist(int empId);

	void deleteEmployee(Employee emp);

	void deleteUserRoles(int getuId);

	List<String> getMangerNames();

//	int getMidByMname(String managerName);

}