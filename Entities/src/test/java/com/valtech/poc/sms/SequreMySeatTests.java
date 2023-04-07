package com.valtech.poc.sms;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import com.valtech.poc.sms.controller.SeatBookingController;
import com.valtech.poc.sms.dao.AdminDao;
import com.valtech.poc.sms.dao.EmployeeDAO;
import com.valtech.poc.sms.dao.HolidayDao;
import com.valtech.poc.sms.dao.ShiftTimingsDao;
import com.valtech.poc.sms.dao.UserDAO;
import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Roles;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.AttendanceRepository;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.ManagerRepo;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.repo.UserRepo;
import com.valtech.poc.sms.security.JwtUtil;
import com.valtech.poc.sms.service.AdminService;
import com.valtech.poc.sms.service.AttendanceService;
import com.valtech.poc.sms.service.EmployeeServiceImpl;
import com.valtech.poc.sms.service.SeatBookingService;
import com.valtech.poc.sms.service.ShiftTimingsService;
import com.valtech.poc.sms.service.UserServiceImpl;

import io.swagger.v3.oas.models.examples.Example;

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
	private ShiftTimingsService shiftTimingsService;
	@Mock
	private ShiftTimingsDao shiftTimingsDao;
	@Mock
	private ManagerRepo managerRepo;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private EmployeeDAO employeeDAO;

	@Mock
	private UserDetails userDetails;

	@Mock
	private AdminDao adminDao;

	@InjectMocks
	private Example example;

	private String ftDate;
	private LocalDateTime dateTime;

	private int eId;
	private List<Map<String, Object>> employees;

	@Test
	public void testLoadUserByUsername() {
		// Create a User object to return from the mocked userRepo
		User user = new User();
		user.setEmpId(123);
		user.setPass("password");

		Set<Roles> roles = new HashSet<>();
		Roles role = new Roles();
		role.setRole("Admin");
		roles.add(role);
		;

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
		String pass = "hello";
		User user = new User();
		user.setEmpId(empId);
		user.setPass(pass);
		user.setApproval(true);
		Roles role = new Roles();
		role.setRole("USER");
		user.getRoles().add(role);
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
				.username(String.valueOf(user.getEmpId())).password(user.getPass()).roles(role.getRole()).build();
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

		assertThrows(RuntimeException.class, () -> userService.login(empId, pass));
	}

	@Before
	public void setUp() {
		ftDate = "2022-12-31 23:59:59";
		dateTime = LocalDateTime.parse(ftDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		eId = 1234;

		employees = new ArrayList<>();

		Map<String, Object> employee1 = new HashMap<>();
		employee1.put("id", 1);
		employee1.put("name", "John Doe");
		employee1.put("title", "Developer");

		Map<String, Object> employee2 = new HashMap<>();
		employee2.put("id", 2);
		employee2.put("name", "Jane Smith");
		employee2.put("title", "Designer");

		employees.add(employee1);
		employees.add(employee2);
	}

	@Test
	public void testGetFoodCount() {
		when(adminDao.getFoodCount(any(LocalDateTime.class))).thenReturn(0);

		int result = adminDao.getFoodCount(dateTime);

		Assert.assertEquals(0, result);
	}

	@Test
	public void testGetSeatBookedCount() {
		when(adminDao.getSeatBookedCount(any(LocalDateTime.class))).thenReturn(0);

		int result = adminDao.getSeatBookedCount(dateTime);

		Assert.assertEquals(0, result);
	}

	@Test
	public void testFindeIdByMailId() {
		// Create a mock EmployeeRepository object
		EmployeeRepo empRepoMock = mock(EmployeeRepo.class);

		// Create a sample Employee object
		Employee emp = new Employee();
		emp.seteId(123);
		emp.setMailId("test@example.com");

		// Configure the mock to return the sample Employee object when findByMailId is
		// called
		when(empRepoMock.findByMailId("test@example.com")).thenReturn(emp);

		// Call the method being tested
		int result = findeIdByMailId("test@example.com", empRepoMock);

		// Verify that the expected result is returned
		assertEquals(123, result);

		// Verify that findByMailId was called exactly once with the correct argument
		verify(empRepoMock, times(1)).findByMailId("test@example.com");
	}

	private int findeIdByMailId(String email, EmployeeRepo empRepo) {
		return empRepo.findByMailId(email).geteId();
	}

	@Test
	public void testIsHoliday() {
		// Create a mock HolidayDao object
		HolidayDao holidayDaoMock = mock(HolidayDao.class);

		// Configure the mock to return 1 when checkHoliday is called with a specific
		// date
		when(holidayDaoMock.checkHoliday(LocalDate.of(2023, 4, 25))).thenReturn(1);

		// Call the method being tested with the mocked HolidayDao object
		boolean result = isHoliday(LocalDate.of(2023, 4, 25), holidayDaoMock);

		// Verify that the expected result is returned
		assertTrue(result);

		// Verify that checkHoliday was called exactly once with the correct argument
		verify(holidayDaoMock, times(1)).checkHoliday(LocalDate.of(2023, 4, 25));
	}

	private boolean isHoliday(LocalDate date, HolidayDao holidayDao) {
		int count = holidayDao.checkHoliday(date);
		return count > 0;
	}

//	@Test
//	public void testGetCountOfFoodOpt() {
//		// Arrange
//		LocalDateTime dateTime = LocalDateTime.of(2023, 4, 7, 12, 0, 0);
//		when(adminDao.getCountOfFoodOpt(dateTime)).thenReturn(5);
//
//		// Act
//		int count = adminDao.getCountOfFoodOpt(dateTime);
//
//		// Assert
//		assertEquals(5, count);
//		verify(adminDao, times(1)).getCountOfFoodOpt(dateTime);
//	}
//
//	@Test
//	public void testFindShiftStartTimings() {
//		// create a mock AdminDao object
//		AdminDao adminDao = mock(AdminDao.class);
//
//		// create a list of expected shift start timings
//		List<String> expectedShiftStartTimings = Arrays.asList("9", "10", "4", "4");
//
//		// configure the mock AdminDao object to return the expected shift start timings
//		when(adminDao.findShiftStartTimings()).thenReturn(expectedShiftStartTimings);
//
//		// call the findShiftStartTimings method
//		List<String> actualShiftStartTimings = adminDao.findShiftStartTimings();
//
//		// verify that the AdminDao.findShiftStartTimings method was called
//		verify(adminDao).findShiftStartTimings();
//
//		// verify that the actual shift start timings match the expected shift start
//		// timings
//		assertEquals(expectedShiftStartTimings, actualShiftStartTimings);
//	}
//
//	@Test
//	public void testFindShiftEndTimings() {
//		// create a mock AdminDao object
//		AdminDao adminDao = mock(AdminDao.class);
//
//		// create a list of expected shift end timings
//		List<String> expectedShiftEndTimings = Arrays.asList("6", "7", "12", "12");
//
//		// configure the mock AdminDao object to return the expected shift end timings
//		when(adminDao.findShiftEndTimings()).thenReturn(expectedShiftEndTimings);
//
//		// call the findShiftEndTimings method
//		List<String> actualShiftEndTimings = adminDao.findShiftEndTimings();
//
//		// verify that the AdminDao.findShiftEndTimings method was called
//		verify(adminDao).findShiftEndTimings();
//
//		// verify that the actual shift end timings match the expected shift end timings
//		assertEquals(expectedShiftEndTimings, actualShiftEndTimings);
//	}
//
//	@Test
//	public void testApproveRegistration() {
//		int uId = 1; // User ID to be approved
//		adminDao.approroveRegistration(uId); // Approve the registration using the mock object
//
//		// Add assertions here to check if the user registration was approved
//		// successfully
//	}
//
//	@Test
//	public void testFindRoles() {
//		// create a mock AdminDao object
//		AdminDao adminDao = mock(AdminDao.class);
//
//		// create a list of expected roles
//		List<String> expectedRoles = Arrays.asList("Admin", "Employee", "Manager");
//
//		// configure the mock AdminDao object to return the expected roles
//		when(adminDao.findRoles()).thenReturn(expectedRoles);
//
//		// call the findRoles method
//		List<String> actualRoles = adminDao.findRoles();
//
//		// verify that the AdminDao.findRoles method was called
//		verify(adminDao).findRoles();
//
//		// verify that the actual roles match the expected roles
//		assertEquals(expectedRoles, actualRoles);
//	}

}