package com.valtech.poc.sms.dao;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Holidays;

@Component
public class HolidaysDaoImpl implements HolidayDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int checkHoliday(LocalDate date) {
		int flag = 0;
		String sql = "SELECT COUNT(*) FROM holidays WHERE date = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[] { Date.valueOf(date) }, Integer.class);
        if (count > 0) {
            flag = 1;
        }
         return flag;
    }

	@Override
	public void updateHoliday(Holidays holiday) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createHoliday(Holidays holiday) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Holidays getHolidayById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}