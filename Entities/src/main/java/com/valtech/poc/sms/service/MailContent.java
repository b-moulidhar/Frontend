package com.valtech.poc.sms.service;

import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.User;

public interface MailContent {

	void registeredSuccessfully(User user);

	void registerationFailure(User user);

	void sendOTP(String email, String pass);

	void successfulPasswordChange(User user);

	void notifyRegisteration(User user);

	void attendanceApprovalRequest(AttendanceTable attendanceTable);

	void attendanceApproved(String mail);

	void attendanceDisApproved(String mail);

	void dailyNotification(Employee emp);

}