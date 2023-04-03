package com.valtech.poc.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.User;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MailContentImpl implements MailContent {

	@Autowired
	SendMail sendMail;

	@Autowired
	UserService userService;

	@Override
	public void registeredSuccessfully(User user) {
		String email = user.getEmpDetails().getMailId();
		String subject = "Congratulations! ";
		String body = "Hello, You have successfully registered as " + user.getRoles()
				+ " at SequereMySeat, you can now log in to your account through the website using your registered email id and password. Thank you for choosing us :) -admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void registerationFailure(User user) {
		String email = user.getEmpDetails().getMailId();
		String subject = "SequreMySeat ";
		String body = "Hello, We regret to inform you that your registration request was not successful "  
				+ " . This might have occured due to various reasons. Please contact us for more info. Thank you for understanding, you can try registering again if you think there was a mistake  -admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void sendOTP(String email, String pass) {
		String subject = "OTP to reset password ";
		String body = "Hello, Please use this OTP to reset your account password: " + pass
				+ " DO NOT SHARE THIS OTP WITH ANYONE!!. -admin";
		try {
			sendMail.sendMail(email, subject, body);
//			mailData.saveMail(email, subject, body, true);
		} catch (Exception e) {
//			mailData.saveMail(email, subject, body, false);
			e.printStackTrace();
		}
	}

	@Override
	public void successfulPasswordChange(User user) {
		String email = user.getEmpDetails().getMailId();
		String subject = "Password Changed Successfully ";
		String body = "Congratulations " + user.getEmpDetails().getEmpName()
				+ ", your password has been changed successfully. You can now log in to your account through the website using your registered email id and new password. -admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void notifyRegisteration(User user) {
		String email = user.getEmpDetails().getMailId();
		String subject = "Registeration form recieved";
		String body = "Hello, Your registeration form is received "
				+ " You will be notified regarding the approval soon. -admin";
		sendMail.sendMail(email, subject, body);
	}
	
	@Override
	public void attendanceApprovalRequest(AttendanceTable attendanceTable) {
		String email = attendanceTable.geteId().getManagerDetails().getManagerDetails().getMailId();
		String subject = "Approval Request recieved";
		System.out.println("test data: "+attendanceTable);
		String body = "Hello, Your Attendance Request form is received "
				+ " You will be notified regarding the approval soon. -admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void attendanceApproved(String mail) {
		String email = mail;
		String subject = "Attendance Request is been approved";
		String body = "Hello, Your Attendance Request approved  -admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void attendanceDisApproved(String mail) {
		String email = mail;
		String subject = "Attendance Request is been disapproved";
		String body = "Hello, Your Attendance Request disapproved  -admin";
		sendMail.sendMail(email, subject, body);
	}
	
	
	

}