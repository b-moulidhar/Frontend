package com.valtech.poc.sms.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.service.AdminService;
import com.valtech.poc.sms.service.EmployeeService;
import com.valtech.poc.sms.service.SeatBookingService;

@RestController
@CrossOrigin(origins = "http://10.191.80.103/:3000")
@RequestMapping("/seats")
public class SeatBookingController {

	@Autowired
	private SeatBookingService seatService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	SeatRepo seatRepo;

	@Autowired
	AdminService adminService;

	@GetMapping("/total")
	public ResponseEntity<List<Integer>> getAllSeats() {
		List<Integer> allSeats = seatService.getAllSeats();
		return ResponseEntity.ok().body(allSeats);
	}

//        
	@GetMapping("/bookedSeatDetails")
	public ResponseEntity<List<Integer>> availableSeats() {
		List<Integer> availableSeats = seatService.availableSeats();
		return ResponseEntity.ok().body(availableSeats);

	}

	@GetMapping("/count")
	public ResponseEntity<List<Integer>> getTotalSeatsCount() {
		List<Integer> totalSeats = seatService.countTotalSeats();
		return ResponseEntity.ok().body(totalSeats);
     }

	@GetMapping("/available/{date}")
	public ResponseEntity<List<Seat>> getAvailableSeatsByDate(
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Seat> availableSeats = seatService.findAvailableSeatsByDate(date);
		if (availableSeats.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(availableSeats);
	}

	@PostMapping("/create/{eId}")
	public synchronized ResponseEntity<String> createSeatsBooked(@PathVariable("eId") int eId,
			@RequestParam("sId") int sId) {
		Employee emp = employeeRepo.findById(eId).get();
		Seat seat = seatRepo.findById(sId).get();
		LocalDate sbDate = LocalDate.now();
		
//		//check if employee can book seat
//		if (!seatService.canEmployeeBookSeat(eId, sbDate)) {
//	        System.out.println("This employee has already booked a seat today. Please try again tomorrow.");
//	        return ResponseEntity.ok("This employee has already booked a seat today. Please try again tomorrow.");
//	    }
//		
		//check if the seat is aldready booked
		if(seatService.checkIftheEmployeeAlreadyBookTheseat(eId)) {
			System.out.println("This seat is aldready booked. Please Book another seat");
			return ResponseEntity.ok("This seat is aldready booked. Please Book another seat " );
		}
		else {
		String code = adminService.generateQrCode(eId);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(formatter.format(now), formatter);
		
		//check for recurring seats
		if(seatService.CheckIfTheSameSeatBookingRecurring(eId)) {
			Seat recSeat=seatService.getSeatById(sId);
			SeatsBooked sb = new SeatsBooked(dateTime, dateTime, dateTime,  true, code, recSeat, emp, false);
			SeatsBooked savedSeatsBooked = seatService.saveSeatsBookedDetails(sb);
			return ResponseEntity.ok("The Same Seat is booked successfully because you are selecting this seat more than 3 times with ID: " + savedSeatsBooked.getSbId());
		}
		else {
//		SeatsBooked sb = new SeatsBooked(dateTime, dateTime, dateTime, dateTime, true, code, seat, emp, false);
		SeatsBooked sb = new SeatsBooked(dateTime, dateTime, dateTime, true, code, seat, emp, false);
		SeatsBooked savedSeatsBooked = seatService.saveSeatsBookedDetails(sb);
		//check if employee is booking a seat again on the same day
				if (seatService.canEmployeeBookSeat(eId, sId,sbDate)) {
			        System.out.println("This employee has already booked a seat today. Please try again tomorrow.");
			        return ResponseEntity.ok("This employee has already booked a seat today. Please try again tomorrow.");
			    }
				return ResponseEntity.ok("Seats booked created successfully with ID: " + savedSeatsBooked.getSbId());
		 }
		 
		   }
	    }
	

	@PutMapping("/notification/{sbId}")
	public String notifStatus(@PathVariable int sbId) {
		seatService.notifStatus(sbId);
		return "Notification Sent";
	}

//	@GetMapping("/recurring/{eId}")
//	public ResponseEntity<List<SeatsBooked>>  getSeatBookingsByEId(@PathVariable ("eId") int eId) {
//			List<SeatsBooked> booking = seatService.getSeatBookingsByEId(eId);
//	        return ResponseEntity.ok().body(recurringSeats);
//	}    
//	        
	        
//	@PutMapping("/notification/{sbId}")
//	public void notifStatus(@PathVariable int sbId) {
//       seatService.notifStatus(sbId);
//	}
//

//   
//     @GetMapping("/current-booking")
//     public ResponseEntity<SeatsBooked> getCurrentSeatBookingDetails(@RequestParam("eId") Long employeeId) {
//        Employee employee = new Employee(eId);
//        SeatsBooked currentSeatBooking = seatService.findCurrentSeatBookingDetails(employee);
//        if (currentSeatBooking != null) {
//          return ResponseEntity.ok(currentSeatBooking);
//        } else {
//          return ResponseEntity.notFound().build();
//        }
//      }

}

//    @GetMapping("/{eId}")
//    public Employee getEmployeeById(@PathVariable int eId) {
//        return employeeService.getEmployeeByeId(eId);
//    }

//    @PostMapping("/seatsBooked/{eId}/{sId}")
//    public ResponseEntity<SeatsBooked> createSeatsBooked(@PathVariable("eId") Long eId, @PathVariable("sId") Long sId) {
//        Employee employee = null;
//		try {
//			employee = EmployeeService.getEmployeeByeId(eId);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        Seat seat = seatService.getSeatById(sId);
//        if (employee == null || seat == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            SeatsBooked seatsBooked = new SeatsBooked();
//            seatsBooked.setEmployee(employee);
//            seatsBooked.setSeat(seat);
//            seatsBooked.setsbStartDate(LocalDate.now());
//            seatsBooked.setsbEndDate(LocalDate.now().plusDays(7));
//            seatsBooked.setPunchIn(LocalDateTime.now());
//            seatsBooked.setPunchOut(LocalDateTime.now().plusHours(8));
//            seatsBooked.setCurrent(true);
//            seatsBooked.setCode("ABC123");
//            SeatsBooked savedSeatsBooked = seatService.saveSeatsBookedDetails(seatsBooked);
//            return ResponseEntity.ok(savedSeatsBooked);
//        }
//    }

//    @PostMapping("/book")
//       public ResponseEntity<String> bookSeat(@RequestBody SeatsBooked seatsBooked) {
//        try {
//        	seatService.bookSeat(seatsBooked);
//            return new ResponseEntity<>("Seat booked successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to book seat", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    

//@GetMapping("/seats/count")
//public ResponseEntity<Integer> getTotalSeatsCount() {
//    int totalSeatsCount = seatBookingService.countTotalSeats();
//    return ResponseEntity.ok(totalSeatsCount);
//}