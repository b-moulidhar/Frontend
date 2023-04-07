package com.valtech.poc.sms.dao;

import java.util.List;
import java.util.Map;

import com.valtech.poc.sms.entities.Employee;

public interface EmployeeDAO {

	Employee getEmployeeByeId(int eId) throws Exception;

	List<Employee> getAllEmployees(int empID);

	List<Map<String, Object>> getAllEmployeesUnderTheManager(int eId);

}