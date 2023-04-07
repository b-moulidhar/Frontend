package com.valtech.poc.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.FloorDao;
import com.valtech.poc.sms.entities.Floors;

@Service
public class FloorServiceImpl implements FloorService {

	private static final Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

	@Autowired
	private FloorDao floorDao;

	@Override
	public List<Floors> getAllFloors() {
		try {
			List<Floors> floors = floorDao.getAllFloors();
			logger.info("Found all floors' details successfully");
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
			logger.info("Floor details with id {} found successfully", f_id);
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
			logger.info("Adding floor {}...", floor.getfName());
		} catch (Exception ex) {
			logger.error("Error occurred while adding floor {}", floor.getfName(), ex);
			throw new RuntimeException("Error occurred while adding floor " + floor.getfName(), ex);
		}
	}

	@Override
	public void deleteFloor(int fId, String f_name) {
		try {
			floorDao.deleteFloor(fId);
			logger.info("Deleting floor with name '{}'...", f_name);
		} catch (Exception ex) {
			logger.error("Error occurred while deleting floor with id {}", fId, ex);
			throw new RuntimeException("Error occurred while deleting floor with id " + fId, ex);
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
			logger.info("Adding {} seats to floor with id {}", seatsToAdd, f_id);
		} catch (Exception ex) {
			logger.error("Error occurred while adding seats to floor with id {}", f_id, ex);
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
			logger.info("Deleting {} seats from floor with ID {}", seatsToDelete, f_id);
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
			logger.info("Updating floor with ID {} with new name '{}' or/and {} seats", f_id, f_name, updatedNumberOfSeats);
		} catch (Exception ex) {
			logger.error("Error occurred while updating floor with ID {}", f_id, ex);
			throw new RuntimeException("Error occurred while updating floor with id " + f_id, ex);
		}
	}
}