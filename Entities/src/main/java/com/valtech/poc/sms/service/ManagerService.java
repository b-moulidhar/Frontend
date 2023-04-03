package com.valtech.poc.sms.service;

import java.sql.SQLException;
import java.util.List;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;

public interface ManagerService {

	Employee getManagerByEmpId(int empId) throws SQLException;

	List<String> getAllManagerNames();

	Manager saveManager(Manager manager);

	Manager getManagerByEId(Employee eId);



}