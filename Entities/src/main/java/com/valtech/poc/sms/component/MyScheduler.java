package com.valtech.poc.sms.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.service.MailContent;
import com.valtech.poc.sms.service.MailService;

@Component
public class MyScheduler {
	
	
	@Autowired
	MailContent mailContent;
	
	@Autowired
	SeatsBookedRepo seatsBookedRepo;
	
	@Autowired
	MailService mailService;
	
    @Scheduled(cron = "0 0/2 * * * ?") // Runs every 2mins
    public void myTask() {
//        System.out.println("scheduled");
    }
    
    @Scheduled(cron = "0 0 11 * * ?")
    
    
//    @Scheduled(cron = "0 0 0 * * ?")
    public void unsentMails() {
    	List<Mail> unsentMails = mailService.getAllUnsentMail();

    			for (Mail mail : unsentMails) {
    				mailContent.unsentMails(mail);
    			}
    }
    
    
    
}

