package com.valtech.poc.sms;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.valtech.poc.sms.controller.SeatBookingController;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.service.AdminService;
import com.valtech.poc.sms.service.SeatBookingService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SequreMySeatTests {


		@Mock
	    private EmployeeRepo employeeRepo;

	    @Mock
	    private SeatRepo seatRepo;

	    @Mock
	    private AdminService adminService;

	    @Mock
	    private SeatBookingService seatBookingService;

	    @InjectMocks
	    private SeatBookingController seatBookingController;
	    
//	    @Test
//	    public void testCreateSeatsBooked() {
//	        // Mock data
//	        int eId = 1;
//	        int sId = 2;
//	        Employee emp = new Employee();
//	        emp.seteId(eId);
//	        Seat seat = new Seat();
//	        seat.setsId(sId);
//	        String code = "QR Code";
//	        LocalDateTime now = LocalDateTime.now();
//	        SeatsBooked sb = new SeatsBooked(now, now, now, true, code, seat, emp, false,false);
////	        SeatsBooked savedSeatsBooked = new SeatsBooked();
//	        sb.setSbId(10);
//
//	        // Mock behavior
//	        Mockito.when(employeeRepo.findById(eId)).thenReturn(Optional.of(emp));
//	        Mockito.when(seatRepo.findById(sId)).thenReturn(Optional.of(seat));
//	        Mockito.when(adminService.generateQrCode(eId)).thenReturn(code);
//	        Mockito.when(seatBookingService.saveSeatsBookedDetails(sb)).thenReturn(sb);
//
//	        // Call API
////	        ResponseEntity<String> response = seatBookingController.createSeatsBooked(eId, sId);
//
//	        // Verify behavior
//	        Mockito.verify(employeeRepo).findById(eId);
//	        Mockito.verify(seatRepo).findById(sId);
//	        Mockito.verify(adminService).generateQrCode(eId);
//	        Mockito.verify(seatBookingService).saveSeatsBookedDetails(sb);
//
//	        // Assert response
////	        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
////	        Assert.assertEquals("Seats booked created successfully with ID: 1", response.getBody());
//	    }
	@Test
	void contextLoads() {
	}

}
