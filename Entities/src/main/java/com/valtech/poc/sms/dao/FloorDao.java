package com.valtech.poc.sms.dao;

import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorDao {

	public List<Floors> getAllFloors();

	public Floors getFloorById(int f_id);

	public void updateFloor(Floors floor);

	void addFloor(Floors floor);

	void deleteFloor(int fId);

}