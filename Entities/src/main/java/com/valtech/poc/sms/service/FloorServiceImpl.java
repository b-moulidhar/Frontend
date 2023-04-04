package com.valtech.poc.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.valtech.poc.sms.dao.FloorDao;
import com.valtech.poc.sms.entities.Floors;

@Service
public class FloorServiceImpl implements FloorService {

//	@Autowired
//	private FloorDao floorDAO;
//
//	@Transactional
//	@Override
//	public void addFloorSeats(Floors floor) {
//		floorDAO.addFloorSeats(floor);
//	}
//
//	@Transactional
//	@Override
//	public void updateFloorSeats(Floors floor) {
//		floorDAO.updateFloorSeats(floor);
//	}
//
//	@Transactional
//	@Override
//	public void deleteFloorSeats(int floorId) {
//		floorDAO.deleteFloorSeats(floorId);
//	}

//	---------------------------------------------------------------------------------------------------------

	@Autowired
	private FloorDao floorDao;

	@Override
	public List<Floors> getAllFloors() {
		return floorDao.getAllFloors();
	}

	@Override
	public Floors getFloorById(int f_id) {
		return floorDao.getFloorById(f_id);
	}

	@Override
	public void addFloorSeats(int f_id, int seatsToAdd) {
		Floors floor = floorDao.getFloorById(f_id);
		if (floor == null) {
			throw new RuntimeException("Floor not found");
		}
		floor.setfSeats(floor.getfSeats() + seatsToAdd);
		floorDao.updateFloor(floor);
	}

	@Override
	public void deleteFloorSeats(int f_id, int seatsToDelete) {
		Floors floor = floorDao.getFloorById(f_id);
		if (floor == null) {
			throw new RuntimeException("Floor not found");
		}
		if (floor.getfSeats() < seatsToDelete) {
			throw new RuntimeException("Insufficient seats to delete");
		}
		floor.setfSeats(floor.getfSeats() - seatsToDelete);
		floorDao.updateFloor(floor);
	}

	@Override
	public void updateFloorSeats(int f_id, int updatedNumberOfSeats) {
		Floors floor = floorDao.getFloorById(f_id);
		if (floor == null) {
			throw new RuntimeException("Floor not found");
		}
		floor.setfSeats(updatedNumberOfSeats);
		floorDao.updateFloor(floor);
	}

}
