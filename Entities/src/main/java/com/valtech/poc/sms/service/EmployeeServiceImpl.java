package com.valtech.poc.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.EmployeeDAO;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Employee getEmployeeByeId(int eId) {
		return employeeDAO.getEmployeeByeId(eId);
	}

	@Autowired
	private EmployeeRepo empRepo;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee findByEmpName(String empName) {
//		System.out.println("ESI "+empRepo.findByEmpName(empName));
		return empRepo.findByEmpName(empName);
	}

	@Override
	public Employee findById(int id) {
		return empRepo.findById(id).get();
	}

	@Override
	public List<Employee> getAllEmployees(int eID) {
		logger.info("Getting All Employee Details by manager");
		return employeeDAO.getAllEmployees(eID);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployeesUnderTheManager(int eId) {
		return employeeDAO.getAllEmployeesUnderTheManager(eId);
	}

	public int findeIdByMailId(String email) {
		// TODO Auto-generated method stub
		return empRepo.findByMailId(email).geteId();
	}

}
