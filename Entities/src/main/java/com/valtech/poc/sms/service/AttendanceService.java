package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Map;

import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;

public interface AttendanceService {

	Employee getSpecificEmployee(AttendanceTable attendance);

	AttendanceTable getList(int atId);

	List<Map<String, Object>> getCompleteAttendanceList();

	Map<String, Object> getAttendanceListForEachEmployee(int atId);

	List<Map<String, Object>> getAttendanceForEmployeeBasedOnEmployeeId(int eId);

	List<Map<String, Object>> getAttendanceListForApproval(int eId);

	void deleteAttendanceRequest(int atId,String mail);

	String getMailIdByAtId(int atId);

	void automaticRegularization(int sbId);

	void updateAttendance(int atId,String mail);

	void saveAttendance(int eId);
}
