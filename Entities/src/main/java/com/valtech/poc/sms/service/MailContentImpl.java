package com.valtech.poc.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.entities.User;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MailContentImpl implements MailContent {

	@Autowired
	SendMail sendMail;

	@Autowired
	UserService userService;

	@Autowired
	SeatBookingService seatBookingService;

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
		String body = "Hello, \nPlease use this OTP to reset your account password: " + pass
				+ " \nDO NOT SHARE THIS OTP WITH ANYONE!!. -admin";
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
				+ ", \nYour password has been changed successfully. \nYou can now log in to your account through the website using your registered email id and new password. \n-admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void notifyRegisteration(User user) {
		String email = user.getEmpDetails().getMailId();
		String subject = "Registeration form recieved";
		String body = "Hello, \nYour registeration form is received, "
				+ " You will be notified regarding the approval soon. \n-admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void attendanceApprovalRequest(AttendanceTable attendanceTable) {
		String email = attendanceTable.geteId().getManagerDetails().getManagerDetails().getMailId();
		String subject = "Approval Request recieved";
		String body = "Hello, \nYour Attendance Request form is received, "
				+ " You will be notified regarding the approval soon. \n-admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void attendanceApproved(String mail) {
		String email = mail;
		String subject = "Attendance Request has been approved";
		String body = "Hello, \nYour Attendance Request has been approved by your manager  \n-admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void attendanceDisApproved(String mail) {
		String email = mail;
		String subject = "Attendance Request is been disapproved";
		String body = "Hello, \nYour Attendance Request disapproved  \n-admin";
		sendMail.sendMail(email, subject, body);
	}

	@Override
	public void dailyNotification(Employee emp) {
		SeatsBooked sb = seatBookingService.findCurrentSeatBookingDetails(emp);
		String email = sb.geteId().getMailId();
		String subject = "You have a seat booked for today";
		String body = "Hello, \n You have a seat booked for today, please find the booking details below. "
				+ "\n Seat booked: " + sb.getsId().getsName() + " \n Seat reserved for : " + sb.getSbDate();
		sendMail.sendMail(email, subject, body);
	}
	
	@Override
	public void unsentMails(Mail mail) {
		String email = mail.getEmail();
		String subject = mail.getSubject();
		String body = mail.getBody();
		sendMail.sendMail(email, subject, body);
		mail.setStatus(true);
	}
	
	

}