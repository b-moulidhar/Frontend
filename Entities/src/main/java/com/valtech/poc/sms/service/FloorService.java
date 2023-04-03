package com.valtech.poc.sms.service;

import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorService {
	
	public List<Floors> getAllFloors();

	public Floors getFloorById(int fId);

	public void addFloorSeats(int fId, int seatsToAdd);

	public void deleteFloorSeats(int fId, int seatsToDelete);

	public void updateFloorSeats(int fId, int newSeats);

}
