package com.valtech.poc.sms.service;

public interface ResetPassword {

	boolean checkMailId(String email);

	String getRandomNumberString();

	boolean generateOtp(String email);

	boolean newPasswod(String email, String otpKey, String password);

}