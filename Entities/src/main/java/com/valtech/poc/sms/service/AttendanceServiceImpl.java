package com.valtech.poc.sms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.valtech.poc.sms.entities.CalendarUtil;
import com.valtech.poc.sms.entities.DateUtil;
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
	private HolidayService holidayService;

	@Autowired
	private MailContent mailContent;

	@Autowired
	private AttendanceRepository attendanceRepository;

	private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	/*Approve the attendance regularization request*/
	@Override
	public void updateAttendance(int atId, String mail) {
		attendanceDao.approveAttendance(atId);
		mailContent.attendanceApproved(mail);
	}

	/*Automates the attendance regularization */
	@Override
	public void automaticRegularization(int sbId) {
		AttendanceTable attendance = new AttendanceTable();
		SeatsBooked sb = seatsBookedRepo.findById(sbId)
				.orElseThrow(() -> new ResourceNotFoundException("SeatBooked not found"));
		sb.getSt().getStEnd();
		attendance.setStartDate("" + sb.getSbDate());
		attendance.setEndDate("" + sb.getSbDate());
		attendance.setShiftStart("" + sb.getSt().getStStart());
		attendance.setShiftEnd("" + sb.getSt().getStEnd());
		attendance.seteId(sb.geteId());
		attendanceRepository.save(attendance);
		mailContent.attendanceApprovalRequest(attendance);
	}

	/*Save the attendance details of the employees*/
	@Override
	public String saveAttendance(int eId, String startDate, String endDate, String shiftStart, String shiftEnd) {
		logger.info("Fetching employee by id");
		logger.debug("Fetching employee by id" + eId);
		Employee emp = employeeRepo.findById(eId).get();
		logger.info("setting the values for attendance");
		String StDate = startDate + " 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime fromDateTime = LocalDateTime.parse(StDate, formatter);
		LocalDate fromDate = fromDateTime.toLocalDate();
		logger.info("Check if the attendance for a particular date is aleady regularized");
		if (attendanceDao.checkIfTheAttendanceIsRegularized(eId, startDate)) {
			return "Already Regularized";
		}  
		else if (CalendarUtil.isDateDisabled(fromDate)) {
			logger.info("check if the date falls on saturday or sunday");
			logger.debug("checking the date if it falls on weekends"+fromDate);
			return "The date falls on sunday or saturday";
		} else if (holidayService.isHoliday(fromDate)) {
			logger.info("check if the date falls on holidays");
			return "Seat Booking not allowed on holidays";
		} else if (verifyDates(startDate, endDate)) {
			logger.info("check if the date in the future");
			return "Start or end date is outside allowable range or in future";
		} else {
			AttendanceTable attendance = new AttendanceTable();
			logger.info("saving the attendance details");
			saveAtt(attendance, emp, fromDate, shiftStart, shiftEnd);
			System.out.println("saved");
			logger.info("Sending the mail");
			mailContent.attendanceApprovalRequest(attendance);
			logger.debug(attendance.geteId().getManagerDetails().getManagerDetails().getMailId());
			return "saved";
		}
	}

	/*Save the details*/
	private void saveAtt(AttendanceTable attendance, Employee emp, LocalDate fromDate, String shiftStart,
			String shiftEnd) {
		attendance.seteId(emp);
		attendance.setStartDate("" + fromDate);
		attendance.setEndDate("" + fromDate);
		attendance.setShiftStart("" + shiftStart);
		attendance.setShiftEnd("" + shiftEnd);
		attendance.setApproval(false);
		attendanceRepository.save(attendance);

	}

	/*Verify the start dates and end dates*/
	private boolean verifyDates(String startDate, String endDate) {
		LocalDate currentDate = LocalDate.now();
		LocalDate minDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth().minus(1), 15);
		LocalDate maxDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth().plus(1), 15);
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);

		if (startLocalDate.isBefore(minDate) || endLocalDate.isAfter(maxDate) || startLocalDate.isAfter(currentDate)
				|| endLocalDate.isAfter(currentDate)) {
			System.out.println("Start or end date is outside allowable range or in future");
			return true;
		}
		return false;

	}
	
	/*Regularizing the attendance for multiple days*/
	@Override
	public String saveAttendanceForMultipleDays(int eId, String startDate, String endDate, String shiftStart, String shiftEnd) {
	    Employee emp = employeeRepo.findById(eId).get();
	    AttendanceTable attendance1 = new AttendanceTable();
	    attendance1.seteId(emp);
	    String StDate = startDate + " 00:00:00";
	    String edDate = endDate + " 00:00:00";
	    if (attendanceDao.checkIfTheAttendanceIsRegularised(eId, StDate, edDate)) {
	        return "Already Regularized";
	    } else {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime fromDateTime = LocalDateTime.parse(StDate, formatter);
	        LocalDateTime toDateTime = LocalDateTime.parse(edDate, formatter);
	        LocalDate fromDate = fromDateTime.toLocalDate();
	        LocalDate toDate = toDateTime.toLocalDate();
	        List<LocalDate> dates = DateUtil.getDatesBetween(fromDate, toDate);
	        if (dates.size() > 7) {
	            return "Cannot book seats for more than 7 days";
	        }
	        else {
	        for (LocalDate date : dates) {
	            if (CalendarUtil.isDateDisabled(date)) {
	                System.out.println("The date falls on sunday or saturday");
	            } else if (holidayService.isHoliday(date)) {
	                System.out.println("Seat Booking not allowed on holidays");
	            } else if (verifyDates(startDate, endDate)) {
	                System.out.println("Start or end date is outside allowable range or in future");
	            } else {
	                AttendanceTable attendance = new AttendanceTable();
	                saveAtt(attendance, emp, date, shiftStart, shiftEnd);
	            }
	        }
	      
	            mailContent.attendanceApprovalRequest(attendance1);
	            return "saved";
	        }
	    }
	    }
	



	/*Fetching the specific employee*/
	@Override
	public Employee getSpecificEmployee(AttendanceTable attendance) {
		return employeeRepo.findById(attendance.geteId().geteId())
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	}

	/*Getting the attendance information of particular employee using atid*/
	@Override
	public AttendanceTable getList(int atId) {
		return attendanceDao.getList(atId);
	}

	/*Get complete attendance list*/
	@Override
	public List<Map<String, Object>> getCompleteAttendanceList() {
		return attendanceDao.getCompleteAttendanceList();

	}

	/*Get attendance details of each employee by atId */
	@Override
	public Map<String, Object> getAttendanceListForEachEmployee(int atId) {
		return attendanceDao.getAttendanceListForEachEmployee(atId);

	}
	
	/*Get attendance details of each employee by eId*/
	@Override
	public List<Map<String, Object>> getAttendanceForEmployeeBasedOnEmployeeId(int eId) {
		return attendanceDao.getAttendanceForEmployeeBasedOnEmployeeId(eId);
	}

	/*Get attendance details of each employee by eId for approval*/
	@Override
	public List<Map<String, Object>> getAttendanceListForApproval(int eId) {
//		Employee e = employeeRepo.findById(eId).get();
//		int employeeId = e.getManagerDetails().getManagerDetails().geteId();
		return attendanceDao.getAttendanceListForApproval(eId);
	}

	/*Disapprove the attendance request of an employee */
	@Override
	public void deleteAttendanceRequest(int atId, String mail) {
		attendanceDao.deleteAttendanceRequest(atId);
		mailContent.attendanceDisApproved(mail);

	}

	/*Fetching the mail using atId*/
	@Override
	public String getMailIdByAtId(int atId) {
		return attendanceDao.getMailIdByAtId(atId);
	}

}
