package com.valtech.poc.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.entities.Roles;
import com.valtech.poc.sms.repo.RolesRepo;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public Roles findByRole(String role) {
		// TODO Auto-generated method stub
		return rolesRepo.getByRole(role);
	}
	
	

}
