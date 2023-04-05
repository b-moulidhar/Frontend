package com.valtech.poc.sms.dao;

import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorDao {

	public List<Floors> getAllFloors();

	public Floors getFloorById(int fId);

	public void updateFloor(Floors floor);

}
