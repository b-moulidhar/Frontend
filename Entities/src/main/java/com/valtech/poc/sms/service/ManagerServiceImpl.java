package com.valtech.poc.sms.service;


import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.valtech.poc.sms.dao.ManagerDAO;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;
import com.valtech.poc.sms.repo.ManagerRepo;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDAO managerDAO;
	
	@Autowired
	private ManagerRepo managerRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);
	
	@Override
	public Employee getManagerByEmpId(int empId) throws SQLException {
		
		try {
			logger.info("Fetching the manager data using the empId of manager");
			return managerDAO.getManagerByEmpId(empId);
		}
		catch(Exception e) {
			logger.info("No manager found");
//			System.out.println("No manager found");
		}
		return null; 
	}

	@Override
	public List<String> getAllManagerNames() {
		// TODO Auto-generated method stub
		logger.info("getting All the Managers");
		return managerDAO.getAllManagerNames();
	}

	@Override
	public Manager saveManager(Manager manager) {
		// TODO Auto-generated method stub
		return managerRepo.save(manager);
	}

	@Override
	public Manager getManagerByEId(Employee eId) {
		// TODO Auto-generated method stub
		return managerRepo.getByManagerDetails(eId);
	}


}
