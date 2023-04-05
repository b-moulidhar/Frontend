package com.valtech.poc.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Seat;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer>{

	int findIdBysName(String sName);

	
}
