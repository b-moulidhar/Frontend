package com.valtech.poc.sms.service;

import java.util.Optional;

import com.valtech.poc.sms.entities.ShiftTimings;

public interface ShiftTimingsService {
	
	Optional<ShiftTimings> findById(int stId);

	int getStId(String startTime, String endTime);

}