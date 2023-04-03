package com.valtech.poc.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer>{

	Roles getByRole(String role);

}
