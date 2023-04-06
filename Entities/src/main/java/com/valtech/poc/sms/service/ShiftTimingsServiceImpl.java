package com.valtech.poc.sms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.ShiftTimingsDao;
import com.valtech.poc.sms.entities.ShiftTimings;
import com.valtech.poc.sms.repo.ShiftTimingsRepo;

@Service
public class ShiftTimingsServiceImpl implements ShiftTimingsService {
	
	@Autowired
	private ShiftTimingsRepo shiftTimingsRepo;
	
	@Autowired
	private ShiftTimingsDao shiftTimingsDao;

	@Override
	public Optional<ShiftTimings> findById(int stId) {
		return shiftTimingsRepo.findById( stId);
	}

	@Override
	public int getStId(String startTime, String endTime) {
		return shiftTimingsDao.getStId(startTime,endTime);
	}
	
	

}
