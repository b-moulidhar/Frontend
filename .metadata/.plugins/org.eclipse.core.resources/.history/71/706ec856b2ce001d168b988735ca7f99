package com.valtech.poc.sms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.AttendanceTable;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceTable, Integer>{
     
	AttendanceTable getAttendanceTableByshiftStart(String shiftStart);

}
