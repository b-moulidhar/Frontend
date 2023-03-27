package com.valtech.poc.sms.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	
	@Autowired SeatsBookedRepo seatsBookedRepo;
	
	@Override
	public int getFoodCount(String ftDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		LocalDateTime dateTime = LocalDateTime.parse(ftDate, formatter);
		System.out.println(ftDate);
		return adminDao.getFoodCount(dateTime);
		
	}

	@Override
	public int getSeatBookedCount(String sbDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		LocalDateTime dateTime = LocalDateTime.parse(sbDate, formatter);
		System.out.println(sbDate);
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
	public void updateAttendance(int atId) {
		adminDao.approveAttendance(atId);
	}

	@Override
	public List<AttendanceTable> listAttendance() {
		return adminDao.listAttendance();
	}

	@Override
	public List<String> findRoles() {
		return adminDao.findRoles();
	}

	@Override
	public void automaticRegularization(int sbId, AttendanceTable attendance) {
		SeatsBooked sb=seatsBookedRepo.findById(sbId).orElseThrow(() -> new ResourceNotFoundException("SeatBooked not found" ));
        attendance.setStartDate(""+sb.getSbDate());
        attendance.setEndDate(""+sb.getSbDate());
        attendance.setShiftStart(""+sb.getPunchIn());
        attendance.setShiftEnd(""+sb.getPunchOut());
        attendance.seteId(sb.geteId());
	}


	@Override
	public Employee getSpecificEmploye(AttendanceTable attendance) {
		return employeeRepo.findById(attendance.geteId().geteId())
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found" ));
	}

	
}
