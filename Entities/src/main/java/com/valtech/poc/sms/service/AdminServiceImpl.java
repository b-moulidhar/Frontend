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
import com.valtech.poc.sms.dao.UserDAO;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Food;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.AdminRepository;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.repo.UserRepo;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService{
	
	@Autowired 
	private AdminDao adminDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired 
	SeatsBookedRepo seatsBookedRepo;
	
	@Autowired
	ResetPassword resetPassword;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SeatBookingService seatBookingService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	@Override
	public String generateQrCode(int eId) {
		Employee emp = employeeRepo.findById(eId).get();
		User usr = userRepo.findByEmpDetails(emp);
		  if (usr == null) {
		   return "000";
		    }
		int empId = usr.getEmpId();
		String code ="" + empId + resetPassword.getRandomNumberString();
		return code;
	}
	
	@Override
	public int getFoodCount(String ftDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(ftDate, formatter);
		System.out.println(ftDate);
		return adminDao.getFoodCount(dateTime);
		
	}

	@Override
	public int getSeatBookedCount(String sbDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(sbDate, formatter);
		System.out.println(sbDate);
		return adminDao.getSeatBookedCount(dateTime);
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
	public List<String> findRoles() {
		return adminDao.findRoles();
	}

	@Override
	public void ApproveRegistration(int uId) {
		// TODO Auto-generated method stub
		
		adminDao.approroveRegistration(uId);
		
	}

	@Override
	public void deleteUser(int empId) {
		// TODO Auto-generated method stub
		User u=userRepo.findByEmpId(empId);
		
		userDao.deleteUserRoles(u.getuId());
		adminDao.deleteUser(u.getuId());
//		employeeRepo.deleteById(u.getEmpDetails().geteId());
		userDao.deleteEmployee(u.getEmpDetails());
	
	}
	public List<Map<String, Object>> getRegistrationListForApproval() {
		return adminDao.getRegistrationListForApproval();
	}

	@Override
	public boolean verifyQr(int eId, String code) {
		Employee emp = employeeService.findById(eId);
		System.out.println(emp.getEmpName());
		SeatsBooked sb = seatBookingService.findCurrentSeatBookingDetails(emp);
		String key = sb.getCode();
		System.out.println(key);
		System.out.println(code);
		if(key.equals(code)) {
			sb.setVerified(true);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(formatter.format(now), formatter);
			sb.setPunchIn(dateTime);
			seatsBookedRepo.save(sb);
			return true;
		}
		return false;
	}

	@Override
	public int getCountOfFoodOpt(String seatDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(seatDate, formatter);
		return adminDao.getCountOfFoodOpt(dateTime);
	}

}