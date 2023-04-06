package com.valtech.poc.sms.component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.SeatsBookedRepo;

@Component
public class ScheduledTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

	@Autowired
	private SeatsBookedRepo seatsBookedRepo;
	
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> scheduledFuture;

    public ScheduledTask() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    // Schedule the task to run after the specified delay
    public String scheduleTask(long delay, SeatsBooked sb) {
        scheduledFuture = executorService.schedule(() -> {
        	Boolean b = sb.isVerified();
        	LOGGER.info("Seat verification status: {}", b);
        	if(b == false) {
        		sb.setCurrent(false);
        		sb.setNotifStatus(false);
        		sb.setCode(null);
        		seatsBookedRepo.save(sb);
        		LOGGER.info("Seat has been cancelled");
        		return "Seat has been cancelled";
        	}
			return null;	
        }, delay, TimeUnit.MILLISECONDS);
        LOGGER.info("Seat booking is active");
        return "Seat booking is active";
    }

    // Cancel the scheduled task
    public void cancelTask() {
        scheduledFuture.cancel(false);
        LOGGER.info("Seat booking has been cancelled");
    }
}
