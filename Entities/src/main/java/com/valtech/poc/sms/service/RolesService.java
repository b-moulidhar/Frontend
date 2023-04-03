package com.valtech.poc.sms.service;

import com.valtech.poc.sms.entities.Roles;

public interface RolesService {
	
	Roles findByRole(String role);

}