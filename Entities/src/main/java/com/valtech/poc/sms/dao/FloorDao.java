package com.valtech.poc.sms.dao;

import java.sql.SQLException;
import java.util.List;

import com.valtech.poc.sms.entities.Floors;

public interface FloorDao {

	public List<Floors> getAllFloors() throws SQLException;

	public Floors getFloorById(int f_id) throws SQLException;

	public void updateFloor(Floors floor) throws SQLException;

	void addFloor(Floors floor) throws SQLException;

	void deleteFloor(int fId) throws SQLException;

}