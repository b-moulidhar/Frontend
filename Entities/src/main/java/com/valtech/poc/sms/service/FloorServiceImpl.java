package com.valtech.poc.sms.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.FloorDao;
import com.valtech.poc.sms.entities.Floors;

@Service
public class FloorServiceImpl implements FloorService {

	private static final Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

	@Autowired
	private FloorDao floorDao;
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Floors> getAllFloors() {
		try {
			List<Floors> floors = floorDao.getAllFloors();
			logger.info("Found all floors' details successfully.");
			return floors;
		} catch (Exception ex) {
			logger.error("Error occurred while getting all floors", ex);
			throw new RuntimeException("Error occurred while getting all floors", ex);
		}
	}

	@Override
	public Floors getFloorById(int f_id) {
		try {
			Floors floor = floorDao.getFloorById(f_id);
			logger.info("Found floor details with Id '{}' successfully.", f_id);
			return floor;
		} catch (Exception ex) {
			logger.error("Error occurred while getting floor with id {}", f_id, ex);
			throw new RuntimeException("Error occurred while getting floor with id " + f_id, ex);
		}
	}

	@Override
	public void addFloor(Floors floor) {
		try {
			floorDao.addFloor(floor);
			logger.info("Adding floor with name '{}'...", floor.getfName());
		} catch (Exception ex) {
			logger.error("Error occurred while adding floor {}", floor.getfName(), ex);
			throw new RuntimeException("Error occurred while adding floor " + floor.getfName(), ex);
		}
	}

	@Override
	public void deleteFloor(int fId) throws SQLException {
	    try {
	        logger.info("Deleting floor with Id '{}' from database...", fId);
	        String sql = "DELETE FROM Floors WHERE f_id = ?";
	        jdbcTemplate.update(sql, fId);
	    } catch (DataAccessException ex) {
	        if (ex.getCause() instanceof CannotGetJdbcConnectionException) {
	            logger.error("Unable to connect to the database: {}", ex.getMessage());
	            throw new SQLException("Unable to connect to the database: " + ex.getMessage());
	        } else {
	            logger.error("Error in deleting floor with id {}: {}", fId, ex.getMessage());
	            throw new SQLException("Error in deleting floor with id " + fId + ": " + ex.getMessage());
	        }
	    }
	}

	@Override
	public void addFloorSeats(int f_id, int seatsToAdd) {
		try {
			Floors floor = floorDao.getFloorById(f_id);
			if (floor == null) {
				logger.error("Floor with id {} not found", f_id);
				throw new RuntimeException("Floor not found");
			}
			floor.setfSeats(floor.getfSeats() + seatsToAdd);
			floorDao.updateFloor(floor);
			logger.info("Adding {} seats to floor with Id '{}'...", seatsToAdd, f_id);
		} catch (Exception ex) {
			logger.error("Error occurred while adding seats to floor with Id '{}'.", f_id, ex);
			throw new RuntimeException("Error occurred while adding seats to floor with id " + f_id, ex);
		}
	}

	@Override
	public void deleteFloorSeats(int f_id, int seatsToDelete) {
		try {
			Floors floor = floorDao.getFloorById(f_id);
			if (floor == null) {
				logger.error("Floor not found for ID {}", f_id);
				throw new RuntimeException("Floor not found");
			}
			if (floor.getfSeats() < seatsToDelete) {
				logger.error("Insufficient seats to delete for floor with ID {}", f_id);
				throw new RuntimeException("Insufficient seats to delete");
			}
			floor.setfSeats(floor.getfSeats() - seatsToDelete);
			floorDao.updateFloor(floor);
			logger.info("Deleting {} seats from floor with ID '{}'...", seatsToDelete, f_id);
		} catch (Exception ex) {
			logger.error("Error occurred while deleting seats from floor with ID {}", f_id, ex);
			throw new RuntimeException("Error occurred while deleting seats from floor with id " + f_id, ex);
		}
	}

	@Override
	public void updateFloorAndSeats(int f_id, String f_name, int updatedNumberOfSeats) {
		try {
			Floors floor = floorDao.getFloorById(f_id);
			if (floor == null) {
				logger.error("Floor not found for ID {}", f_id);
				throw new RuntimeException("Floor not found");
			}
			floor.setfSeats(updatedNumberOfSeats);
			floor.setfName(f_name);
			floorDao.updateFloor(floor);
			logger.info("Updated floor with ID '{}' with name '{}' and '{}' seats.", f_id, f_name, updatedNumberOfSeats);
		} catch (Exception ex) {
			logger.error("Error occurred while updating floor with ID {}", f_id, ex);
			throw new RuntimeException("Error occurred while updating floor with id " + f_id, ex);
		}
	}
}