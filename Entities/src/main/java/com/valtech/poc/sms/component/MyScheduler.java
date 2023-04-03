package com.valtech.poc.sms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.service.MailContent;

@Component
public class MyScheduler {
	
	@Autowired
	MailContent mailContent;
	
    @Scheduled(cron = "0 0/2 * * * ?") // Run at 12:00 PM every day
    public void myTask() {
        System.out.println("scheduled");
    }
    
    public void dailyNotification() {
    	mailContent.dailyNotification();
    }
    
    public void autoCancellation(SeatsBooked sb) {
    	Boolean b = sb.isVerified();
    	
    }
}

