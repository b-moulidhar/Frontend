package com.valtech.poc.sms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.UserDAO;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.ManagerRepo;
import com.valtech.poc.sms.repo.UserRepo;
import com.valtech.poc.sms.security.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	@Autowired
	private UserRepo userepo;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private EmployeeRepo employeeRepo;
//	private UserDAO userDAO;

	@Autowired
//	EmployeeRepo employeeRepo;
	EmployeeRepo empRepo;

	@Autowired
	ManagerRepo managerRepo;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User findByEmail(String email) {
		Employee emp = employeeRepo.findByMailId(email);
		User usr = userepo.findByEmpDetails(emp);
		System.out.println("usr= " + usr);
		return usr;
	}

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Override
//	public User findByEmpId(int empId) {
//		return userDao.findByEmpId(empId);
//		
//	}


	@Override
	public void register(int empId, String pass, Employee empDetails) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
        User user = userRepo.findByEmpId(Integer.parseInt(empId));
        if (user == null) {
            throw new UsernameNotFoundException("User not found with empId: " + empId);
        }
        return org.springframework.security.core.userdetails.User.builder()
            .username(String.valueOf(user.getEmpId()))
            .password(user.getPass())
            .roles("USER")
            .build();
    }

	@Override
	public Manager getManagerByMname(String managerName, Employee emp) throws EmptyResultDataAccessException {
		try {
			logger.info("fetching manager ");

			Manager mng = userDao.getMidByMname(managerName, emp);
//		logger.info("" + mng.getmId());
//			if (mng != null)
				return mng;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Manager Not Found");
			
		}
		return null;
		
	}

	@Override
	public void saveEmployee(Employee employee, Manager manager) {
		logger.info("Saving Employee info");
//		userDAO.saveEmployee(employee,mId);
		if (manager != null) {
			employee.setManagerDetails(manager);
			logger.info("assigning manger ");
		} else {
			employee.setManagerDetails(null);
		}
		empRepo.save(employee);
		logger.info("saved: " + employee);
	}

	@Override
	public User saveUser(User user, Employee emp) throws EmptyResultDataAccessException {
		try {
			logger.info("Saving User Info");

			if (checkIfEmpIdExist(user.getEmpId())) {
				User u = userepo.save(user);
				u.setEmpDetails(emp);
				return u;
			}
		} catch (Exception e) {
			logger.info("The EmpId is already exist");
		}

		return null;

	}

	public boolean checkIfEmpIdExist(int empId) throws EmptyResultDataAccessException{
		try {
		logger.info("checking if EmpId Is already Exist or not");
		int rows = userDao.checkIfEmpIdExist(empId);
		if (rows != 0)
			return false;
		
	}catch (Exception e) {
		logger.info("No empId");
	}
		return true;
	}

	@Override
	public int getRidByRoleName(String role) {
		logger.info("Getting role_id by using name of the role");
		return userDao.getRidByRoleName(role);
	}

	@Override
	public void saveUserRoles(int uId, int rId) {
		logger.info("Saving the User_roles");
		userDao.saveUserRole(uId, rId);
	}

	@Override
	public void saveManager(Manager mng) {
		logger.info("Saving Manager");
		managerRepo.save(mng);
	}
//
//	@Override
//	public int getMidByName(String managerName) {
//		int mId = userDAO.getMidByMname(managerName);
//		return mId;
//	}

	@Override
	public void deleteEmployee(Employee emp) {
		userDao.deleteEmployee(emp);
	}


	@Override
	public User findByEmpId(int empId) {
		User usr = userRepo.findByEmpId(empId);
//		User usr = userRepo.findById(empId);
//		System.out.println(usr);
		return usr;
	}
	
	
	@Override
	public User findByEId(int eId) {
		Employee emp = employeeRepo.findById(eId).get();
		User usr = userRepo.findByEmpDetails(emp);
		return usr;
	}


	@Override
	public User save(User user) {
		logger.info("Saving User Info");
		return userepo.save(user);
		
	}


	@Override
	public int getMidByName(String managerName) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<String> getManagerNames() {
		// TODO Auto-generated method stub
		return userDao.getMangerNames();
	}
	
	@Override
	public String login(int empId, String pass) {
	    UserDetails userDetails;
	    try {
	        userDetails = loadUserByUsername(String.valueOf(empId));
	    } catch (UsernameNotFoundException ex) {
	        throw new RuntimeException("User not found with empId: " + empId);
	    }
	    User user = userRepo.findByEmpId(empId);
	    if (user != null && bCryptPasswordEncoder.matches(pass, user.getPass())) {
	        if (user.isApproval()) {
	        	String token=jwtUtil.generateToken(userDetails);
	            return token;
	        } else {
	            throw new RuntimeException("User is not approved");
	        }
	    } else {
	        throw new RuntimeException("Invalid username or password");
	    }
	}
	

}
