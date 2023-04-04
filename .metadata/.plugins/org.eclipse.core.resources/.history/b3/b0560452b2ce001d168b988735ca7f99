package com.valtech.poc.sms.repo;

import java.time.LocalDateTime;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Food;
import com.valtech.poc.sms.entities.SeatsBooked;

@Repository
public interface AdminRepository extends JpaRepository<Food, Integer> {

	Food getFoodByFtDate(LocalDateTime ftDate);
	

}
