package com.valtech.poc.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.ShiftTimings;
@Repository
public interface ShiftTimingsRepo extends JpaRepository<ShiftTimings, Integer>{

}
