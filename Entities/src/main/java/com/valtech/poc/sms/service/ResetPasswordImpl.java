package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.entities.Otp;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.OtpRepo;
import com.valtech.poc.sms.repo.UserRepo;

@Service
public class ResetPasswordImpl implements ResetPassword {

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@Autowired
	OtpRepo otpRepo;
	
	@Autowired
	MailContent mailContent;

//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean checkMailId(String email) {
		List<User> allUsers = userRepo.findAll();
		for (User user1 : allUsers) {
			if (email.equals(user1.getEmpDetails().getMailId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		return String.format("%06d", number);
	}

	@Override
	public boolean generateOtp(String email) {
		User usr = userService.findByEmail(email);
		String otpKey = getRandomNumberString();
		Otp otp = new Otp(otpKey);
		System.out.println("otp1= " + otpKey);
		otpRepo.save(otp);
//		mailMessage.sendOTP(usr.getEmail(), otp1);
		mailContent.sendOTP(email, otpKey);
//		String Pass = bCryptPasswordEncoder.encode(otpKey);
		usr.setOtp(otp);
		userRepo.save(usr);
//		ResponseEntity<UserDetails> user1= userfacade.saveUser(usr);
		System.out.println(usr);
		return true;
	}

	@Override
	public boolean newPasswod(String email, String otpKey, String password) {
		User usr = userService.findByEmail(email);
		String key = usr.getOtp().getOtpKey();
//		System.out.println("key = "+key);
//		System.out.println("otpKey= "+otpKey);
		if (otpKey.equals(key)) {
			usr.setPass(new BCryptPasswordEncoder().encode(password));
			usr.setOtp(null);
			userRepo.save(usr);
			mailContent.successfulPasswordChange(usr);
			return true;
		}
		return false;
	}

}
