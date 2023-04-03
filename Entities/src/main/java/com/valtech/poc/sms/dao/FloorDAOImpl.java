package com.valtech.poc.sms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.valtech.poc.sms.entities.Floors;

public class FloorDAOImpl implements FloorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Floors> getAllFloors() {
		String sql = "SELECT * FROM Floors";
		List<Floors> floors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Floors.class));
		return floors;
	}

	@Override
	public Floors getFloorById(int fId) {
		String sql = "SELECT * FROM Floors WHERE fId = ?";
		@SuppressWarnings("deprecation")
		Floors floor = jdbcTemplate.queryForObject(sql, new Object[] { fId },
				new BeanPropertyRowMapper<>(Floors.class));
		return floor;
	}

	@Override
	public void updateFloor(Floors floor) {
		String sql = "UPDATE Floors SET fName = ?, fSeats = ? WHERE fId = ?";
		jdbcTemplate.update(sql, floor.getfName(), floor.getfSeats(), floor.getfId());
	}
}
