package com.valtech.poc.sms.service;

import java.util.List;

import com.valtech.poc.sms.entities.AttendanceTable;

public interface AdminService {

	int getFoodCount(String ftDate);

	int getSeatBookedCount(String sbDate);

	int getCount(String ftDate);
	
	void updateAttendance(int atId);

	List<AttendanceTable> listAttendance();



}