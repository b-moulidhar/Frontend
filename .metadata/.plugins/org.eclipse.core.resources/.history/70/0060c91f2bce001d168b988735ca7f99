package com.valtech.poc.sms.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.valtech.poc.sms.dao.AdminDao;
import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Food;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.exception.ResourceNotFoundException;
import com.valtech.poc.sms.repo.AdminRepository;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService{
	
	@Autowired 
	private AdminDao adminDao;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired 
	SeatsBookedRepo seatsBookedRepo;
	
	@Autowired
	ResetPassword resetPassword;
	
	@Override
	public String generateQrCode(int empId) {
		String code ="" + empId + resetPassword.getRandomNumberString();
		return code;
	}
	
	@Override
	public int getFoodCount(String ftDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		LocalDateTime dateTime = LocalDateTime.parse(ftDate, formatter);
		System.out.println(ftDate);
		return adminDao.getFoodCount(dateTime);
		
	}

	@Override
	public int getSeatBookedCount(String sbStartDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		LocalDateTime dateTime = LocalDateTime.parse(sbStartDate, formatter);
		System.out.println(sbStartDate);
		return adminDao.getSeatBookedCount(dateTime);
	}

	@Override
	public int getCount(String ftDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		LocalDateTime dateTime = LocalDateTime.parse(ftDate, formatter);
		Food f= adminRepository.getFoodByFtDate(dateTime);
		return f.getCount();
	}
	
	@Override
	public List<String> findShiftStartTimings() {
		return adminDao.findShiftStartTimings();
	}

	@Override
	public List<String> findShiftEndTimings() {
		return adminDao.findShiftEndTimings();
	}

	
	@Override
	public void updateAttendance(int atId) {
		adminDao.approveAttendance(atId);
	}

	@Override
	public List<String> findRoles() {
		return adminDao.findRoles();
	}

	@Override
	public void automaticRegularization(int sbId, AttendanceTable attendance) {
		SeatsBooked sb=seatsBookedRepo.findById(sbId).orElseThrow(() -> new ResourceNotFoundException("SeatBooked not found" ));
        attendance.setStartDate(""+sb.getSbStartDate());
        attendance.setEndDate(""+sb.getSbEndDate());
        attendance.setShiftStart(""+sb.getPunchIn());
        attendance.setShiftEnd(""+sb.getPunchOut());
        attendance.seteId(sb.geteId());
	}


	@Override
	public Employee getSpecificEmploye(AttendanceTable attendance) {
		return employeeRepo.findById(attendance.geteId().geteId())
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found" ));
	}

	@Override
	public AttendanceTable getList(int atId) {
		return adminDao.getList(atId);
	}

	@Override
	public List<Map<String, Object>> getCompleteAttendanceList() {
		return adminDao.getCompleteAttendanceList();
		
	}

	@Override
	public Map<String, Object> getAttendanceListForEachEmployee(int atId) {
		return adminDao.getAttendanceListForEachEmployee(atId);
		
	}

	@Override
	public List<Map<String, Object>> getAttendanceForEmployeeBasedOnEmployeeId(int eId) {
		return adminDao.getAttendanceForEmployeeBasedOnEmployeeId(eId);
	}

	@Override
	public List<Map<String, Object>> getAttendanceListForApproval(int eId) {
		return adminDao.getAttendanceListForApproval(eId);
	}

	@Override
	public List<Map<String, Object>> getRegistrationListForApproval() {
		return adminDao.getRegistrationListForApproval();
	}

	@Override
	public void deleteAttendanceRequest(int atId) {
		adminDao.deleteAttendanceRequest(atId);
		
	}

	
}