package com.valtech.poc.sms.component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.SeatsBookedRepo;

@Component
public class ScheduledTask {
	
	@Autowired
	private SeatsBookedRepo seatsBookedRepo;
	
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> scheduledFuture;

    public ScheduledTask() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public String scheduleTask(long delay, SeatsBooked sb) {
        scheduledFuture = executorService.schedule(() -> {
        	Boolean b = sb.isVerified();
        	System.out.println(b);
        	if(b == false) {
        		sb.setCurrent(false);
        		sb.setNotifStatus(false);
        		sb.setCode(null);
        		seatsBookedRepo.save(sb);
        		System.out.println("test scheduled task");
        		return "Seat has been cancelled";
        	}
        	System.out.println("test b fail scheduled task");
			return null;
        	
        }, delay, TimeUnit.MILLISECONDS);
        return "Seat booking is active";
    }

    public void cancelTask() {
        scheduledFuture.cancel(false);
    }
}
