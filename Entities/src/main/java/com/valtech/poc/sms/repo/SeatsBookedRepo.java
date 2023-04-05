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
    
	List<SeatsBooked> findAllByeId(Employee emp);

	   // @Query("UPDATE seats_booked sb SET sb.notifStatus = ? WHERE sb.sbId = ?")
	    //void notifStatus( @PathVariable("sbId") int sbId);
	 @Query(value = "SELECT s.s_name AS seat_name, COUNT(*) AS popular_seats " +
             "FROM seats_booked sb " +
             "INNER JOIN seat s ON s.s_id = sb.s_id " +
             "GROUP BY s.s_id " +
             "ORDER BY popular_seats DESC " +
             "LIMIT 5")
         List<Object[]> findTopFivePopularSeats();
	     }

	
	
	
	

	

	



