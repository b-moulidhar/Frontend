package com.valtech.poc.sms.dao;

import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorDao {
	
//	public void addFloorSeats(Floors floor);
//
//	public void updateFloorSeats(Floors floor);
//
//	public void deleteFloorSeats(int floorId);
	
//	---------------------------------------------------------------------------------------------------------


	public List<Floors> getAllFloors();

	public Floors getFloorById(int f_id);

	public void updateFloor(Floors floor);

}