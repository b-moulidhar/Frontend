package com.valtech.poc.sms.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.valtech.poc.sms.entities.Mail;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SendMailImpl implements SendMail {
	
	@Autowired
	MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(SendMailImpl.class);

	// @Value("${sender.email.id}")
	// private String fromId;
	// @Value("${sender.email.password}")
	// private String password;

	@Override
	@RequestMapping("sms")
	public void sendMail(String email, String subject, String body) {
		logger.info("Sending mail....");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// smtp.gmail.com supports TLSv1.2 and TLSv1.3
		// smtp.office365.com supports only TLSv1.2
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");
		Properties prop = new Properties();
		String fileName = "app.config";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			// FileNotFoundException catch is optional and can be collapsed
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				logger.info("Getting Password Authenticated....");
				String username = prop.getProperty("mail.username");
				String password = prop.getProperty("mail.password");
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			String username = prop.getProperty("mail.username");
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			Mail mail = new Mail(email, subject, body, true, LocalDateTime.now());
			mailService.saveMail(mail);
			logger.info("Mail Sent Successfully!");
		} catch (MessagingException e) {
			Mail mail = new Mail(email, subject, body, false, LocalDateTime.now());
			mailService.saveMail(mail);
			logger.error("Error Occurred while Sending Mail in SendMailImpl");
		}
	}
}