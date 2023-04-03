package com.valtech.poc.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

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
}
