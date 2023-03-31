package com.valtech.poc.sms.dao;

import java.sql.Connection;
import java.time.LocalDate;

import java.util.List;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;

public interface SeatBookingDao {

//	List<Integer> getAllSeats();

	List<Integer> availableSeats();

//	List<SeatsBooked> findAllByEId(Employee emp);

	SeatsBooked findCurrentSeat(Employee emp);

	List<Integer> countTotalSeats();

	List<Integer> getAllSeats();

	List<Seat> findAvailableSeatsByDate(LocalDate date);

    void bookSeat(SeatsBooked seatsBooked);

	//void notifStatus(boolean notifStatus, int sbId);

	void notifStatus(int sbId);


	//List<SeatsBooked> getSeatBookingsByEId(int eId) ;

	//List<RecurringSeats> countRecurringSeats();

	boolean checkIfEmployeeAlredyBookTheSeat(int eId);


	//void updateNotifStatus(int sbId, Connection connection);

	

//List<Integer> getSeatById();


}