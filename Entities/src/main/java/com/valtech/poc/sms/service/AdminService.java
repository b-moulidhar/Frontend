package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	int getFoodCount(String ftDate);

	int getSeatBookedCount(String sbDate);

	int getCount(String ftDate);

	List<String> findRoles();

	String generateQrCode(int empId);

	void ApproveRegistration(int empId);

	void deleteUser(int empId);
	
	List<Map<String, Object>> getRegistrationListForApproval();

	List<String> findShiftStartTimings();

	List<String> findShiftEndTimings();

	boolean verifyQr(int eId,String code);




}
