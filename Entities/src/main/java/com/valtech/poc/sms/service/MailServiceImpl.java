package com.valtech.poc.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.repo.MailRepo;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	MailRepo mailRepo;
	
	
	
	@Override
	public List<Mail> getAllUnsentMail(){
		return mailRepo.findAllByStatusFalse();
	}
	
	@Override
	public Mail saveMail(Mail mail) {
		return mailRepo.save(mail);
	}

}
