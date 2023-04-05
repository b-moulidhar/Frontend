package com.valtech.poc.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valtech.poc.sms.entities.ShiftTimings;

public interface ShiftTimingsRepo extends JpaRepository<ShiftTimings, Integer>{

}
