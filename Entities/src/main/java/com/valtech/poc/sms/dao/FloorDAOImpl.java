package com.valtech.poc.sms.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Floors;

@Repository
public class FloorDAOImpl implements FloorDao {

	private static final Logger logger = LoggerFactory.getLogger(FloorDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Floors> getAllFloors() throws SQLException {
		try {
			logger.info("Retrieving all floors' details from database...");
			String sql = "SELECT * FROM Floors";
			List<Floors> floors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Floors.class));
			return floors;
		} catch (DataAccessException e) {
			logger.error("Error in fetching all floors: {}", e.getMessage());
			throw new SQLException("Error in fetching all floors: " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Floors getFloorById(int f_id) throws SQLException {
		try {
			logger.info("Retrieving floor details with Id '{}' from database...", f_id);
			String sql = "SELECT * FROM Floors WHERE f_id = ?";
			Floors floor = jdbcTemplate.queryForObject(sql, new Object[] { f_id },
					new BeanPropertyRowMapper<>(Floors.class));
			return floor;
		} catch (DataAccessException e) {
			logger.error("Error in fetching floor with id {}: {}", f_id, e.getMessage());
			throw new SQLException("Error in fetching floor with id " + f_id + ": " + e.getMessage());
		}
	}

	@Override
	public void addFloor(Floors floor) throws SQLException {
		try {
			logger.info("Adding floor with ID '{}' to database...", floor.getfId());
			String sql = "INSERT INTO Floors (f_id, f_name, f_seats) VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, floor.getfId(), floor.getfName(), floor.getfSeats());
		} catch (DataAccessException e) {
			logger.error("Error in adding floor with id {}: {}", floor.getfId(), e.getMessage());
			throw new SQLException("Error in adding floor with id " + floor.getfId() + ": " + e.getMessage());
		}
	}

	public void deleteFloor(int fId) throws SQLException {
		try {
			logger.info("Deleting floor with Id '{}' from database...", fId);
			String sql = "DELETE FROM Floors WHERE f_id = ?";
			int rowsAffected = jdbcTemplate.update(sql, fId);
			if (rowsAffected == 0) {
				throw new SQLException("Floor with id " + fId + " does not exist in the database.");
			}
		} catch (DataAccessException e) {
			logger.error("Error in deleting floor with id {}: {}", fId, e.getMessage());
			throw new SQLException("Error in deleting floor with id " + fId + ": " + e.getMessage());
		}
	}

	@Override
	public void updateFloor(Floors floor) throws SQLException {
		try {
			logger.info("Updating floor with Id '{}' in database...", floor.getfId());
			String sql = "UPDATE Floors SET f_name = ?, f_seats = ? WHERE f_id = ?";
			jdbcTemplate.update(sql, floor.getfName(), floor.getfSeats(), floor.getfId());
		} catch (DataAccessException e) {
			logger.error("Error in updating floor with id {}: {}", floor.getfId(), e.getMessage());
			throw new SQLException("Error in updating floor with id " + floor.getfId() + ": " + e.getMessage());
		}
	}

}
