package com.valtech.poc.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.valtech.poc.sms.dao.FloorDao;
import com.valtech.poc.sms.entities.Floors;

public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorDao floorDao;

	@Override
	public List<Floors> getAllFloors() {
		return floorDao.getAllFloors();
	}

	@Override
    public Floors getFloorById(int fId) {
        return floorDao.getFloorById(fId);
    }

    @Override
	public void addFloorSeats(int fId, int seatsToAdd) {
		Floors floor = floorDao.getFloorById(fId);
		if (floor == null) {
			throw new RuntimeException("Floor not found");
		}
		floor.setfSeats(floor.getfSeats() + seatsToAdd);
		floorDao.updateFloor(floor);
	}

    @Override
    public void deleteFloorSeats(int fId, int seatsToDelete) {
        Floors floor = floorDao.getFloorById(fId);
        if(floor == null) {
            throw new RuntimeException("Floor not found");
        }
        if(floor.getfSeats() < seatsToDelete) {
            throw new RuntimeException("Insufficient seats to delete");
        }
        floor.setfSeats(floor.getfSeats() - seatsToDelete);
        floorDao.updateFloor(floor);
    }

    @Override
    public void updateFloorSeats(int fId, int newSeats) {
        Floors floor = floorDao.getFloorById(fId);
        if(floor == null) {
            throw new RuntimeException("Floor not found");
        }
        floor.setfSeats(newSeats);
        floorDao.updateFloor(floor);
    }
}
