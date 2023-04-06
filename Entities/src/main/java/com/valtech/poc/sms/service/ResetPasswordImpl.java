package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.entities.Otp;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.OtpRepo;
import com.valtech.poc.sms.repo.UserRepo;

@Service
public class ResetPasswordImpl implements ResetPassword {

	private static final Logger logger = LoggerFactory.getLogger(ResetPasswordImpl.class);

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@Autowired
	OtpRepo otpRepo;

	@Autowired
	MailContent mailContent;

	// password encoder bean
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	// check if email is present in the database
	@Override
	public boolean checkMailId(String email) {
		List<User> allUsers = userRepo.findAll();
		logger.info("Checking if email {} is present in the database", email);
		for (User user1 : allUsers) {
			if (email.equals(user1.getEmpDetails().getMailId())) {
				return true;
			}
		}
		return false;
	}

	// generate a random six-digit OTP
	@Override
	public String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		return String.format("%06d", number);
	}

	// generate and send an OTP to the user's email
	@Override
	public boolean generateOtp(String email) {
		User usr = userService.findByEmail(email);
		String otpKey = getRandomNumberString();
		Otp otp = new Otp(otpKey);

		// save the generated OTP to the database
		otpRepo.save(otp);
		logger.info("Generated and saved OTP for user with email: {}", email);

		// send the OTP to the user's email
		mailContent.sendOTP(email, otpKey);
		logger.info("Sent OTP to user with email: {}", email);

		// store the OTP in the user's record
		usr.setOtp(otp);
		userRepo.save(usr);
		logger.info("Stored OTP for user with email: {}", email);

		return true;
	}

	// update the user's password if the OTP is valid
	@Override
	public boolean newPasswod(String email, String otpKey, String password) {
		User usr = userService.findByEmail(email);
		String key = usr.getOtp().getOtpKey();

		// compare the OTP entered by the user with the OTP in the database
		if (otpKey.equals(key)) {
			// encrypt and save the new password to the database
			usr.setPass(bCryptPasswordEncoder.encode(password));
			usr.setOtp(null);
			userRepo.save(usr);

			// send a success message to the user's email
			mailContent.successfulPasswordChange(usr);
			logger.info("Password updated successfully for user with email {}", email);
			return true;
		}
		logger.info("Invalid OTP entered for user with email {}", email);
		return false;
	}

}
