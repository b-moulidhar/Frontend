package com.valtech.poc.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.repo.MailRepo;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	MailRepo mailRepo;

	/**
	 * This method retrieves all unsent mail objects from the database.
	 * 
	 * @return List<Mail> The list of all unsent mails.
	 */
	@Override
	public List<Mail> getAllUnsentMail() {
		logger.info("Getting all unsent mail");
		return mailRepo.findAllByStatusFalse();
	}

	/**
	 * This method saves the mail data to the database.
	 * 
	 * @return Mail The saved mail object.
	 */
	@Override
	public Mail saveMail(Mail mail) {
		logger.info("Saving mail object");
		return mailRepo.save(mail);
	}

}
