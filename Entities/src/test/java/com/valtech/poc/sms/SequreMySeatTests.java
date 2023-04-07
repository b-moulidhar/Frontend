package com.valtech.poc.sms;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.valtech.poc.sms.controller.SeatBookingController;
import com.valtech.poc.sms.dao.UserDAO;
import com.valtech.poc.sms.entities.Roles;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.ManagerRepo;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.repo.UserRepo;
import com.valtech.poc.sms.security.JwtUtil;
import com.valtech.poc.sms.service.AdminService;
import com.valtech.poc.sms.service.SeatBookingService;
import com.valtech.poc.sms.service.UserServiceImpl;

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
	    
	    @Mock
	    private SeatsBookedRepo seatsBookedRepo;
	  


	    @InjectMocks
	    private SeatBookingController seatBookingController;
	    
	    @InjectMocks
	    private UserServiceImpl userService;

	    @Mock
	    private UserRepo userRepo;

	    @Mock
	    private UserDAO userDao;


	    @Mock
	    private ManagerRepo managerRepo;

	    @Mock
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Mock
	    private JwtUtil jwtUtil;
	   
	    
	    @Mock
	    private UserDetails userDetails;
	    
	    
//	    @InjectMocks
//	    private UserService userService;
	    
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
	    public void testLoadUserByUsername() {
	        // Create a User object to return from the mocked userRepo
	        User user = new User();
	        user.setEmpId(123);
	        user.setPass("password");

	        Set<Roles> roles = new HashSet<>();
	        Roles role = new Roles();
	        role.setRole("Admin");
	        roles.add(role);;


	        user.setRoles(roles);

	        // Mock the userRepo to return the User object for empId 123
	        when(userRepo.findByEmpId(123)).thenReturn(user);

	        // Call the loadUserByUsername method
	        UserDetails userDetails = userService.loadUserByUsername("123");

	        // Verify that the mocked userRepo was called with the correct empId
	        verify(userRepo).findByEmpId(123);

	        // Verify that the UserDetails object was constructed correctly
	        assertEquals("123", userDetails.getUsername());
	        assertEquals("password", userDetails.getPassword());
	        assertEquals("ROLE_Admin", userDetails.getAuthorities().iterator().next().getAuthority());
	    }
	    @Test
	    public void testValidLogin() {
	        // given
	        int empId = 123;
	        String pass="hello";
	        User user = new User();
	        user.setEmpId(empId);
	        user.setPass(pass);
	        user.setApproval(true);
	        Roles role = new Roles();
	        role.setRole("USER");
	        user.getRoles().add(role);
	        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
	    	        .username(String.valueOf(user.getEmpId()))
	    	        .password(user.getPass())
	    	        .roles(role.getRole())
	    	        .build();
	        String expectedToken = "jwt.token";
	        when(userRepo.findByEmpId(empId)).thenReturn(user);
	        when(bCryptPasswordEncoder.matches(pass, user.getPass())).thenReturn(true);
	        when(jwtUtil.generateToken(userDetails)).thenReturn(expectedToken);
	        
	        // when
	        String actualToken = userService.login(empId, pass);
	        
	        // then
	        assertEquals(expectedToken, actualToken);
	    }

	    @Test
	    public void testLoginWithNonApprovedUser() {
	        int empId = 123;
	        String pass = "password";

	        User user = new User();
	        user.setEmpId(empId);
	        user.setPass("password");
	        user.setApproval(false);

	        when(userRepo.findByEmpId(empId)).thenReturn(user);
	        when(bCryptPasswordEncoder.matches(pass, user.getPass())).thenReturn(true);

	        assertThrows(RuntimeException.class, () ->userService.login(empId, pass));
	    }



	    


	@Test
	void contextLoads() {
	}
	


}
