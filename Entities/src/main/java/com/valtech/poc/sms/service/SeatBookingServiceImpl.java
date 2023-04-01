package com.valtech.poc.sms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.dao.SeatBookingDao;
import com.valtech.poc.sms.dao.SeatBookingDaoImpl;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;

@Service


public  class SeatBookingServiceImpl implements SeatBookingService {

	@Autowired
	private SeatBookingDao seatBookingDao;
	
	@Autowired
	SeatsBookedRepo seatsBookedRepo;
	
	@Autowired
	private SeatRepo seatRepo;
	
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
	 
//	@Override
//	public void createSeatsBooked() {
//	     SeatsBooked seatsBooked = new SeatsBooked();
//	     seatsBooked.setSbEndDate(LocalDate.now().plusDays(7));
//	     seatsBooked.setStartDate(LocalDate.now());
//	     seatsBooked.setPunchIn(LocalDateTime.now());
//	     seatsBooked.setPunchOut(LocalDateTime.now().plusHours(8));
//	     seatsBooked.setCurrent(true);
//	        seatsBooked.setCode("ABC123");
//	        seatsBooked.setSId(1L);
//	        seatsBooked.setEId(2L);
//	        seatsBookedRepository.save(seatsBooked);
//	    }

	@Override
	public void bookSeat(SeatsBooked seatsBooked) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkIftheEmployeeAlreadyBookTheseat(int eId) {
		
		return seatBookingDao.checkIfEmployeeAlredyBookTheSeat(eId);
	}

	@Override
	public boolean CheckIfTheSameSeatBookingRecurring(int eId) {
		
		return seatBookingDao.checkIfTheSameSeatBookingRecurring(eId);
	}

	@Override
	public Seat getSeatById(int sId) {
		System.out.println(seatRepo.findById(sId).get());
		return seatRepo.findById(sId).get();
	}
	
//	@Override
//	public void updateNotifStatus(int sbId, Connection connection) {
//	    try (Connection connection = dataSource.getConnection()) {
//	        SeatBookingDao seatBookingDao = new SeatBookingDaoImpl();
//	        seatBookingDao.updateNotifStatus(sbId, connection);
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	}

	}



	
	
	
	
	

