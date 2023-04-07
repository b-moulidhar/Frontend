package com.valtech.poc.sms.service;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.entities.User;

public interface MailContent {

	void registeredSuccessfully(User user);

	void registerationFailure(User user);

	void sendOTP(String email, String pass);

	void successfulPasswordChange(User user);

	void notifyRegisteration(User user);

	void attendanceApprovalRequest(String mail);

	void attendanceApproved(String mail);

	void attendanceDisApproved(String mail);

	void dailyNotification(Employee emp);

	void unsentMails(Mail mail);

}