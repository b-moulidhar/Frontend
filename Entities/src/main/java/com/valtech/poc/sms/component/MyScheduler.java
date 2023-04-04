package com.valtech.poc.sms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.service.MailContent;

@Component
public class MyScheduler {
	
	
	@Autowired
	MailContent mailContent;
	
	@Autowired
	SeatsBookedRepo seatsBookedRepo;
	
    @Scheduled(cron = "0 0/2 * * * ?") // Runs every 2mins
    public void myTask() {
        System.out.println("scheduled");
    }
    

    public void dailyNotification(Employee emp) {
    	mailContent.dailyNotification(emp);
    }
    
    
}

