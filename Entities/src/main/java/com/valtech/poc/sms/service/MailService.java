package com.valtech.poc.sms.service;

import java.util.List;

import com.valtech.poc.sms.entities.Mail;

public interface MailService {

	List<Mail> getAllUnsentMail();

	Mail saveMail(Mail mail);

}