package com.valtech.poc.sms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valtech.poc.sms.component.ScheduledTask;
import com.valtech.poc.sms.dao.SeatBookingDao;
import com.valtech.poc.sms.entities.CalendarUtil;
import com.valtech.poc.sms.entities.DateUtil;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.entities.ShiftTimings;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.repo.ShiftTimingsRepo;

@Service

public class SeatBookingServiceImpl implements SeatBookingService {

	@Autowired
	private SeatBookingDao seatBookingDao;

	@Autowired
	SeatsBookedRepo seatsBookedRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	ShiftTimingsRepo shiftTimingsRepo;
	
	@Autowired
	AdminService adminService;

	@Autowired
	SeatRepo seatRepo;
	
	@Autowired
	ScheduledTask scheduledTask;
	
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	MailContent mailContent;

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
		seatBookingDao.notifStatus(sbId);
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

//	@Override
//	public boolean canEmployeeBookSeat(int eId,int sId, LocalDateTime fromDateTime) throws ServiceException {
//	    try {
//	        return seatBookingDao.canEmployeeBookSeat(eId, sId,fromDateTime);
//	    } catch (DataAccessException e) {
//	        throw new ServiceException("Error checking if employee can book seat", e);
//	    }
//	}

	@Override
	public Seat getSeatById(int sId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

    @Override
    public List<SeatsBooked> getSeatsBookedByEmployeeAndDate(int empId, LocalDateTime startDate, LocalDateTime endDate) {
        return seatBookingDao.getSeatsBookedByEmployeeAndDate(empId, startDate, endDate);
    }

    @Override
    public List<SeatsBooked> getSeatsBookedByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return seatBookingDao.getSeatsBookedByDate(startDate, endDate);
    }

	

	@Override
	public boolean checkIftheSeatIsCurrentlyBooked(int eId, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		// TODO Auto-generated method stub
		return seatBookingDao.checkIfEmployeeAlredyBookTheSeat(eId, fromDateTime, toDateTime);
	}
	
	@Override
	public boolean checkIftheSeatIsCurrentlyBookedDaily(int eId, LocalDateTime fromDateTime) {
		// TODO Auto-generated method stub
		return seatBookingDao.checkIfEmployeeAlredyBookTheSeatDaily(eId, fromDateTime);
	}


	@Override
	public String createSeatsBookedDaily(int eId, int sId,int stId, String from, String to) {
		Employee emp = employeeRepo.findById(eId).get();
		Seat seat = seatRepo.findById(sId).get();
		ShiftTimings st=shiftTimingsRepo.findById(stId).get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
		LocalDate fromDate = fromDateTime.toLocalDate();

		// check if the seat is already booked
		if (checkIftheSeatIsCurrentlyBookedDaily(eId, fromDateTime)) {
			return "This employee has already booked the seat.";
		} else {
			if(CalendarUtil.isDateDisabled(fromDate)) {
			System.out.println("The date falls on sunday or saturday");
			}
			else if (holidayService.isHoliday(fromDate)) {
		    System.out.println("Seat Booking not allowed on holidays");
		}
			else {
			String code = adminService.generateQrCode(eId);
			LocalDateTime localDateTime = fromDate.atStartOfDay();
			//LocalDateTime dateTime = LocalDateTime.parse(formatter.format(localDateTime), formatter);
			long limit = 60000*240;
			// check for recurring seats
			if (CheckIfTheSameSeatBookingRecurring(eId)) {
				Seat recSeat = getSeatById(sId);

				//SeatsBooked sb = new SeatsBooked( localDateTime, null, null, true, code, recSeat, emp, false, false, false, null);

				SeatsBooked sb = new SeatsBooked(localDateTime, null, null, true, code, recSeat, emp, false, false,false,st);

				SeatsBooked savedSeatsBooked = saveSeatsBookedDetails(sb);
				scheduledTask.scheduleTask(limit, savedSeatsBooked);
				//mailContent.dailyNotification(emp);
				return "The Same Seat is booked successfully because you are selecting this seat more than 3 times with ID: "
						+ savedSeatsBooked.getSbId();
			} else {

				//SeatsBooked sb = new SeatsBooked( localDateTime, null, null, true, code, seat, emp, false, false, false, null);

				SeatsBooked sb = new SeatsBooked(localDateTime, null, null, true, code, seat, emp, false, false,false,st);

				SeatsBooked savedSeatsBooked = saveSeatsBookedDetails(sb);
				scheduledTask.scheduleTask(limit, savedSeatsBooked);
//				mailContent.dailyNotification(emp);
				// check if employee is booking a seat again on the same day
//				if (canEmployeeBookSeat(eId, sId,fromDateTime)) {
//			        return ResponseEntity.ok("This employee has already booked a seat today. Please try again tomorrow.";
//			    }
				return "Seats booked created successfully with ID: " + savedSeatsBooked.getSbId();
			}
			}
			return "Seat Booked Succesfully";

		}

	}

	@Override
	public String createSeatsBookedWeekly(int eId, int sId,int stId, String from, String to) {
		Employee emp = employeeRepo.findById(eId).get();
		Seat seat = seatRepo.findById(sId).get();
		ShiftTimings st=shiftTimingsRepo.findById(stId).get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
		LocalDateTime toDateTime = LocalDateTime.parse(to, formatter);
		if (checkIftheSeatIsCurrentlyBooked(eId, fromDateTime, toDateTime)) {
			return "This employee has already booked.";
		} else {
			LocalDate fromDate = fromDateTime.toLocalDate();
			LocalDate toDate = toDateTime.toLocalDate();
			List<LocalDate> dates = DateUtil.getDatesBetween(fromDate, toDate);
		for (LocalDate date : dates) {
					if(CalendarUtil.isDateDisabled(date)) {
					System.out.println("The date falls on sunday or saturday");
					}
					else if (holidayService.isHoliday(date)) {
				    System.out.println("Seat Booking not allowed on holidays");
				}
					else {
			LocalDateTime localDateTime = date.atStartOfDay();
			String code = adminService.generateQrCode(eId);

			//SeatsBooked sb = new SeatsBooked( localDateTime, null, null, true, code, seat, emp, false, false, false, null);

			SeatsBooked sb = new SeatsBooked(localDateTime, null, null, true, code, seat, emp, false, false,false,st);

			seatsBookedRepo.save(sb);
		}
		}
		return "Seats booked successfully ";
	}
	}
	@Override
	public byte[] generateSeatsBookedReportPDF(LocalDateTime startDate, LocalDateTime endDate) throws Exception {
	    List<SeatsBooked> seatsBooked = getSeatsBookedByDate(startDate, endDate);
	    
	    byte[] pdf=seatBookingDao.generateSeatsBookedPDF(seatsBooked);
	    return pdf;
	}
	
	 @Override
	   public List<Seat> getTopFivePopularSeats() {
	        return seatBookingDao.getTopFivePopularSeats();
	    }
	
//    @Override
//    public List<Object[]> getTopFivePopularSeats() {
//        return seatRepo.findTopFivePopularSeats();
//
//}
}

