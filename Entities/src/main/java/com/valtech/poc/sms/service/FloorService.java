package com.valtech.poc.sms.service;

import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorService {

//	public void addFloorSeats(Floors floor);
//
//	public void updateFloorSeats(Floors floor);
//
//	public void deleteFloorSeats(int floorId);

//	---------------------------------------------------------------------------------------------------------

	public List<Floors> getAllFloors();

	public Floors getFloorById(int f_id);

	public void addFloor(Floors floor);

	public void deleteFloor(int fId);

	public void addFloorSeats(int f_id, int seatsToAdd);

	public void deleteFloorSeats(int f_id, int seatsToDelete);

//	public void updateFloorSeats(int f_id, int updatedNumberOfSeats);

	public void updateFloorAndSeats(int f_id, String f_name, int updatedNumberOfSeats);

}