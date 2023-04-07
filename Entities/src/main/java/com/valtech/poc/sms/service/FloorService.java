package com.valtech.poc.sms.service;

import java.sql.SQLException;
import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorService {

	public List<Floors> getAllFloors();

	public Floors getFloorById(int f_id);

	public void addFloor(Floors floor);

	void deleteFloor(int fId) throws SQLException;

	public void addFloorSeats(int f_id, int seatsToAdd);

	public void deleteFloorSeats(int f_id, int seatsToDelete);

	public void updateFloorAndSeats(int f_id, String f_name, int updatedNumberOfSeats);

}