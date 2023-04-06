package com.valtech.poc.sms.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataAccessException;

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



	boolean checkIfTheSameSeatBookingRecurring(int eId);

	boolean canEmployeeBookSeat(int eId,int sId, LocalDate sbDate);

	boolean checkIfEmployeeAlreadyBookTheSeat(int eId,LocalDateTime from, LocalDateTime to) throws DataAccessException;

	boolean checkIfEmployeeAlreadyBookTheSeatDaily(int eId,  LocalDateTime from) throws DataAccessException;


	List<SeatsBooked> getSeatsBookedByEmployeeAndDate(int empId, LocalDateTime startDate, LocalDateTime endDate);


	List<SeatsBooked> getSeatsBookedByDate(LocalDateTime startDate, LocalDateTime endDate);

	byte[] generateSeatsBookedPDF(List<SeatsBooked> seatsBooked) throws Exception;

	void updatFoodCount(LocalDateTime sbDate);




	//void updateNotifStatus(int sbId, Connection connection);

	

//List<Integer> getSeatById();


}