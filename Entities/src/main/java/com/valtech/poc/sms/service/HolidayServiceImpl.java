package com.valtech.poc.sms.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.HolidayDao;
import com.valtech.poc.sms.dao.HolidaysDaoImpl;
import com.valtech.poc.sms.dao.SeatBookingDao;
import com.valtech.poc.sms.entities.Holidays;


@Service
public class HolidayServiceImpl implements HolidayService {
	

    @Autowired
   private HolidayDao holidayDao;
    
    public boolean isHoliday(LocalDate date) {
        int count = holidayDao.checkHoliday(date);
        return count > 0;
     }
    
    @Override
    public void createHoliday(Holidays holiday) {
        holidayDao.createHoliday(holiday);
    }
    
    @Override
    public Holidays getHolidayById(int id) {
        return holidayDao.getHolidayById(id);
    }

    @Override
    public void updateHoliday(Holidays holiday) {
        holidayDao.updateHoliday(holiday);
    }
  }