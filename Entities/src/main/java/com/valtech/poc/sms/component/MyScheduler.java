package com.valtech.poc.sms.component;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Configurations;
import com.valtech.poc.sms.entities.Mail;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.ConfigurationsRepo;
import com.valtech.poc.sms.service.MailContent;
import com.valtech.poc.sms.service.MailService;
import com.valtech.poc.sms.service.SeatBookingService;

@Component
public class MyScheduler {

	private static final Logger logger = Logger.getLogger(MyScheduler.class.getName());

	@Autowired
	MailContent mailContent;

	@Autowired
	SeatBookingService seatBookingService;

	@Autowired
	MailService mailService;

	@Autowired
	ScheduledTask scheduledTask;

	@Autowired
	ConfigurationsRepo configurationsRepo;

	private String shiftCancelCron;

	@Scheduled(cron = "0 0/2 * * * ?") // Runs every 2mins
	public void myTask() {
//    	System.out.println("scheduled");
	}

//    @PostConstruct
//    public void init() {
//        Configurations cfg = configurationsRepo.findByName("shift");
//        int shift = cfg.getValue();
//        String shiftCancelCron = "0 0 "+shift+" * * ?"; 
//    }

	// Fetches all seatsbooked for firstshift and triggers a timer that cancels the
	// seat booking
	// if emloyee doesn't check in before the buffer time
	@Scheduled(cron = "0 0 9 * * ?")
	public void firstShift() {
		logger.info("Running firstShift() method which calls the timer to cancel seats automatically");
//    	Properties prop = new Properties();
//    	String fileName = "app.config";
//    	try (FileInputStream fis = new FileInputStream(fileName)) {
//    		prop.load(fis);
//    	} catch (FileNotFoundException ex) {
//    		logger.log(Level.SEVERE, "FileNotFoundException caught", ex);
//    	} catch (IOException ex) {
//    		logger.log(Level.SEVERE, "IOException caught", ex);
//    	}
//    	String delay = prop.getProperty("shift.cancel.delay");
//    	int delayInHrs = Integer.parseInt(delay);
//    	long Delay = 3600000*delayInHrs;
		Configurations cfg = configurationsRepo.findByName("buffer");
		int buffer = cfg.getValue();
		int delay = 3600000 * buffer;
		List<SeatsBooked> sbl = seatBookingService.getSBBySTAndDate(9, LocalDate.now().toString());
		for (SeatsBooked seatsBooked : sbl) {
			scheduledTask.scheduleTask(delay, seatsBooked);
		}

	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void unsentMails() {
		logger.info("Running unsentMails() method");
		List<Mail> unsentMails = mailService.getAllUnsentMail();

		for (Mail mail : unsentMails) {
			mailContent.unsentMails(mail);
		}
	}

}
