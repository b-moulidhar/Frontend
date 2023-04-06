package com.valtech.poc.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Configurations;

@Repository
public interface ConfigurationsRepo extends JpaRepository<Configurations, Integer>{
	
	Configurations findByName(String name);
	
	

}
