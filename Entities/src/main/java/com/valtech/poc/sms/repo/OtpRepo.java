package com.valtech.poc.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valtech.poc.sms.entities.Otp;

public interface OtpRepo extends JpaRepository<Otp, Integer>{
	
	

}
