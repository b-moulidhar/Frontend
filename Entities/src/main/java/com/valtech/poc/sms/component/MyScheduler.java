package com.valtech.poc.sms.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {
    @Scheduled(cron = "0 0/2 * * * ?") // Run at 12:00 PM every day
    public void myTask() {
        System.out.println("scheduled");
    }
}

