package com.valtech.poc.sms.dao;


import java.util.List;

import com.valtech.poc.sms.entities.Employee;

public interface ManagerDAO {

	Employee getManagerByEmpId(int empId);

	List<String> getAllManagerNames();



}