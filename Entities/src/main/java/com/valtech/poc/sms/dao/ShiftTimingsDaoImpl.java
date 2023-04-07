package com.valtech.poc.sms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiftTimingsDaoImpl implements ShiftTimingsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	@Override
	public int getStId(String startTime, String endTime) {
	    String sql = "SELECT st_id FROM shift_timings WHERE st_start = ? AND st_end = ?";
	    try {
	        Integer stid = jdbcTemplate.queryForObject(sql, new Object[] { startTime, endTime }, Integer.class);
	        return stid != null ? stid : 0;
	    } catch (EmptyResultDataAccessException e) {
	        System.out.println("No stid found for the given start time and end time.");
	        return 0;
	    }
	}


}
