package com.valtech.poc.sms.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.valtech.poc.sms.dao.AttendanceDao;
import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.exception.ResourceNotFoundException;
import com.valtech.poc.sms.repo.AttendanceRepository;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	private AttendanceDao attendanceDao;
	
	@Autowired
	private SeatsBookedRepo seatsBookedRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private MailContent mailContent;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	
	@Override
	public void updateAttendance(int atId,String mail) {
		attendanceDao.approveAttendance(atId);
		mailContent.attendanceApproved(mail);
	}
	
	@Override
	public void automaticRegularization(int sbId) {
		AttendanceTable attendance = new AttendanceTable();
		SeatsBooked sb=seatsBookedRepo.findById(sbId).orElseThrow(() -> new ResourceNotFoundException("SeatBooked not found" ));
        attendance.setStartDate(""+sb.getSbDate());
        attendance.setEndDate(""+sb.getSbDate());
        attendance.setShiftStart(""+sb.getPunchIn());
        attendance.setShiftEnd(""+sb.getPunchOut());
        attendance.seteId(sb.geteId());
		attendanceRepository.save(attendance);
		mailContent.attendanceApprovalRequest(attendance);
	}
	
	@Override
	public void saveAttendance(int eId) {
		logger.info("Fetching employee by id");
		logger.debug("Fetching employee by id"+eId);
		Employee emp = employeeRepo.findById(eId).get();
		AttendanceTable attendance =new AttendanceTable();
		logger.info("setting the values for attendance");
		attendance.seteId(emp);
		attendance.setStartDate(""+attendance.getStartDate());
		attendance.setEndDate(""+attendance.getEndDate());
		attendance.setShiftStart(""+attendance.getShiftStart());
		attendance.setShiftEnd("" + attendance.getShiftEnd());
		attendance.setApproval(false);
		attendanceRepository.save(attendance);
		mailContent.attendanceApprovalRequest(attendance);
		
	}

	@Override
	public Employee getSpecificEmployee(AttendanceTable attendance) {
		return employeeRepo.findById(attendance.geteId().geteId())
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found" ));
	}

	@Override
	public AttendanceTable getList(int atId) {
		return attendanceDao.getList(atId);
	}

	@Override
	public List<Map<String, Object>> getCompleteAttendanceList() {
		return attendanceDao.getCompleteAttendanceList();
		
	}

	@Override
	public Map<String, Object> getAttendanceListForEachEmployee(int atId) {
		return attendanceDao.getAttendanceListForEachEmployee(atId);
		
	}

	@Override
	public List<Map<String, Object>> getAttendanceForEmployeeBasedOnEmployeeId(int eId) {
		return attendanceDao.getAttendanceForEmployeeBasedOnEmployeeId(eId);
	}

	@Override
	public List<Map<String, Object>> getAttendanceListForApproval(int eId) {
		return attendanceDao.getAttendanceListForApproval(eId);
	}


	@Override
	public void deleteAttendanceRequest(int atId,String mail) {
		attendanceDao.deleteAttendanceRequest(atId);
		mailContent.attendanceDisApproved(mail);
		
	}

	@Override
	public String getMailIdByAtId(int atId) {
		return attendanceDao.getMailIdByAtId(atId);
	}

	
}
