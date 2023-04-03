package com.valtech.poc.sms.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Food;

@Repository
public interface AdminRepository extends JpaRepository<Food, Integer> {

	Food getFoodByFtDate(LocalDateTime ftDate);
	

}
