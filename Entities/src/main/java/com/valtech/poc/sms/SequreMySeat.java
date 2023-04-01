package com.valtech.poc.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SequreMySeat {

	public static void main(String[] args) {
		SpringApplication.run(SequreMySeat.class, args);
	}

}
