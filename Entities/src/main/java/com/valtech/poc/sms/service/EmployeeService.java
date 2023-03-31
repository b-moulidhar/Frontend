package com.valtech.poc.sms.service;

import java.util.List;

import com.valtech.poc.sms.entities.Employee;

public interface EmployeeService {

	Employee getEmployeeByeId(int eId);

	Employee findByEmpName(String empName);

	Employee findById(int id);

	List<Employee> getAllEmployees(int empID);

	Employee saveEmployee(Employee employee);


}