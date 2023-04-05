package com.valtech.poc.sms.service;

import java.time.LocalDate;

import com.valtech.poc.sms.entities.Holidays;

public interface HolidayService {

	boolean isHoliday(LocalDate date);

	void createHoliday(Holidays holiday);

	Holidays getHolidayById(int id);

	void updateHoliday(Holidays holiday);

}