package com.valtech.poc.sms.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.SeatBookingDao;
import com.valtech.poc.sms.dao.SeatBookingDaoImpl;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.SeatsBookedRepo;

@Service


public  class SeatBookingServiceImpl implements SeatBookingService {

	@Autowired
	private SeatBookingDao seatBookingDao;
	
	@Autowired
	SeatsBookedRepo seatsBookedRepo;
	
//	@Override
//	public String getQrCodeKeyForEmpId(int empId)

	@Override
	public List<Integer> getAllSeats() {
		return seatBookingDao.getAllSeats();
	}

	@Override
	public List<Integer> availableSeats() {
		return seatBookingDao.availableSeats();
	}
		
	@Override
	public List<Seat> findAvailableSeats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Integer> countTotalSeats() {
        return seatBookingDao.countTotalSeats();
    }
	
	
//	@Override
//	public List<SeatsBooked> findEmployeeWiseSeatsBooked(Employee emp)	{
//		return seatBookingDao.findAllByEId(emp);
//	}
	
	@Override
	public SeatsBooked findCurrentSeatBookingDetails(Employee emp) {
		return seatBookingDao.findCurrentSeat(emp);
	}

	@Override
	public List<SeatsBooked> findAllByEId(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Seat> findAvailableSeatsByDate(LocalDate date) {
	    return seatBookingDao.findAvailableSeatsByDate(date);
	}

	 @Override
	 public SeatsBooked saveSeatsBookedDetails(SeatsBooked seatsBooked) {
	      return seatsBookedRepo.save(seatsBooked);
	 }
	 
	
	 
	 @Override
	 public void notifStatus(int sbId) {
		 seatBookingDao.notifStatus( sbId);
	 }
	 
    @Override
	public void bookSeat(SeatsBooked seatsBooked) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean CheckIfTheSameSeatBookingRecurring(int eId) {
		// TODO Auto-generated method stub
		return false;
	}
    
	@Override
	public boolean canEmployeeBookSeat(int eId,int sId, LocalDate sbDate) throws ServiceException {
	    try {
	        return seatBookingDao.canEmployeeBookSeat(eId, sId,sbDate);
	    } catch (DataAccessException e) {
	        throw new ServiceException("Error checking if employee can book seat", e);
	    }
	}

	
	@Override
	public Seat getSeatById(int sId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIftheEmployeeAlreadyBookTheseat(int eId, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		// TODO Auto-generated method stub
		return seatBookingDao.checkIfEmployeeAlredyBookTheSeat(eId,fromDateTime,toDateTime);
	}
	
  }



	
	
	
	
	

