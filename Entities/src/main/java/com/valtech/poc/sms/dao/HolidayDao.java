package com.valtech.poc.sms.dao;

import java.time.LocalDate;

import com.valtech.poc.sms.entities.Holidays;

public interface HolidayDao {

	int checkHoliday(LocalDate date);

	void updateHoliday(Holidays holiday);

	void createHoliday(Holidays holiday);

	Holidays getHolidayById(int id);

}