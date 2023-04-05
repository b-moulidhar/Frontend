package com.valtech.poc.sms.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.service.MailContent;
import com.valtech.poc.sms.service.MailService;
import com.valtech.poc.sms.service.SeatBookingService;

@Component
public class MyScheduler {
	
	
	@Autowired
	MailContent mailContent;
	
	@Autowired
	SeatBookingService seatBookingService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ScheduledTask scheduledTask;
	


    @Scheduled(cron = "0 0/2 * * * ?") // Runs every 2mins
    public void myTask() {
    	
    }
    
    @Scheduled(cron = "0 0 9 * * ?")
    public void firstShift() {
//    	List<SeatsBooked> sb = seatBookingService.
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
    	String delay = prop.getProperty("shift.cancel.delay");
    	int delayInHrs = Integer.parseInt(delay);
    	long Delay = 60000*delayInHrs;
    	//scheduledTask.scheduleTask(Delay, sb);
    }
    
    
//    @Scheduled(cron = "0 0 0 * * ?")
    public void unsentMails() {
    	List<Mail> unsentMails = mailService.getAllUnsentMail();

    			for (Mail mail : unsentMails) {
    				mailContent.unsentMails(mail);
    			}
    }
    
    
    
}

