package com.valtech.poc.sms.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.SeatsBooked;

import jakarta.transaction.Transactional;

public interface SeatsBookedRepo extends JpaRepository<SeatsBooked, Integer>{
	
	@Transactional
    @Modifying
    
	List<SeatsBooked> findAllByeIdAndCurrentTrue(Employee emp);

	   // @Query("UPDATE seats_booked sb SET sb.notifStatus = ? WHERE sb.sbId = ?")
	    //void notifStatus( @PathVariable("sbId") int sbId);
	
	SeatsBooked findByeId(Employee eId);
	

	}

	



