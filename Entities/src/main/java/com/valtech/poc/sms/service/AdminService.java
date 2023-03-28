package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Map;

import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;

public interface AdminService {

	int getFoodCount(String ftDate);

	int getSeatBookedCount(String sbStartDate);

	int getCount(String ftDate);
	
	void updateAttendance(int atId);

	List<String> findRoles();

	void automaticRegularization(int sbId, AttendanceTable attendance);

	Employee getSpecificEmploye(AttendanceTable attendance);

	AttendanceTable getList(int atId);

	List<Map<String, Object>> getCompleteAttendanceList();

	Map<String, Object> getAttendanceListForEachEmployee(int atId);

	String generateQrCode(int empId);



}