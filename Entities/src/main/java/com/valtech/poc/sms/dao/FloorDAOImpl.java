package com.valtech.poc.sms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Floors;

@Repository
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
	public Floors getFloorById(int f_id) {
		String sql = "SELECT * FROM Floors WHERE f_id = ?";
		@SuppressWarnings("deprecation")
		Floors floor = jdbcTemplate.queryForObject(sql, new Object[] { f_id },
				new BeanPropertyRowMapper<>(Floors.class));
		return floor;
	}

	@Override
	public void addFloor(Floors floor) {
		String sql = "INSERT INTO Floors (f_id, f_name, f_seats) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, floor.getfId(), floor.getfName(), floor.getfSeats());
	}

	@Override
	public void deleteFloor(int fId) {
		String sql = "DELETE FROM Floors WHERE f_id = ?";
		jdbcTemplate.update(sql, fId);
	}

	@Override
	public void updateFloor(Floors floor) {
		String sql = "UPDATE Floors SET f_name = ?, f_seats = ? WHERE f_id = ?";
		jdbcTemplate.update(sql, floor.getfName(), floor.getfSeats(), floor.getfId());
	}

}