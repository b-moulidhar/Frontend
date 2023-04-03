package com.valtech.poc.sms.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface AdminDao {

	int getFoodCount(LocalDateTime dateTime);

	int getSeatBookedCount(LocalDateTime dateTime);
	
	List<String> findRoles();

	void approroveRegistration(int uId);

	void deleteUser(int getuId);
	
	List<Map<String, Object>> getRegistrationListForApproval();

	List<String> findShiftStartTimings();

	List<String> findShiftEndTimings();

}