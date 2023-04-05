package com.valtech.poc.sms.dao;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Seat;
import com.valtech.poc.sms.entities.SeatsBooked;
import com.valtech.poc.sms.entities.User;
import com.valtech.poc.sms.repo.EmployeeRepo;
import com.valtech.poc.sms.repo.ManagerRepo;
import com.valtech.poc.sms.repo.SeatRepo;
import com.valtech.poc.sms.repo.SeatsBookedRepo;
import com.valtech.poc.sms.repo.ShiftTimingsRepo;
import com.valtech.poc.sms.repo.UserRepo;
import com.valtech.poc.sms.service.EmployeeService;
import com.valtech.poc.sms.service.ShiftTimingsService;

@Component
@ComponentScan

public class SeatBookingDaoImpl implements SeatBookingDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SeatRepo seatRepo;
	
	@Autowired
	SeatsBookedRepo seatsBookedRepo;

	@Autowired
	SeatBookingDao seatBookingDao;

	@Autowired
	ManagerRepo managerRepo;

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private ShiftTimingsService shiftTimingsService;
	
	@Autowired
	private ShiftTimingsRepo shiftTimingsRepo;

	@Override
	public List<Integer> getAllSeats() {
		String query = "SELECT s_name FROM seat";
		List<Integer> allSeats = jdbcTemplate.queryForList(query, Integer.class);
		return allSeats;
		// fetching all the available seats
	}

//	@Override
//	public List<Integer> availableSeats() {
//		String query = "SELECT sb_id FROM seats_booked WHERE current = 1";
//		List<Integer> availableSeats = jdbcTemplate.queryForList(query, Integer.class);
//		return availableSeats;
//		// fetching the seats which are booked
//	}
// retreiving seat booking details where current = 1

	@Override
	public List<Integer> availableSeats() {
		String query = "SELECT sb.sb_id, s.s_name " + "FROM seats_booked sb " + "INNER JOIN seat s ON sb.s_id = s.s_id "
				+ "WHERE current = 1";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		List<Integer> availableSeats = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Integer sbId = (Integer) row.get("sb_id");
			String sName = (String) row.get("s_name");
			System.out.println("Seat ID: " + sbId + ", Seat Name: " + sName);
			availableSeats.add(sbId);

		}
		return availableSeats;
	}
//   @Override
//	public List<Object[]> availableSeats() {
//	    String query = "SELECT sb.sb_id, s.s_name FROM seats_booked sb INNER JOIN seat s ON sb.s_id = s.s_id WHERE sb.current = 1";
//	    List<Object[]> rows = jdbcTemplate.query(query, new Object[]{}, new BeanPropertyRowMapper(Object[].class));
//
//	    List<Object[]> availableSeats = new ArrayList<>();
//	    for (Object[] row : rows) {
//	        Integer sbId = (Integer) row[0];
//	        String sName = (String) row[1];
//	        availableSeats.add(new Object[]{sbId, sName});
//	    }
//
//	    return availableSeats;
//	}

	@Override
	public void notifStatus(int sbId) {
		String sql = "UPDATE seats_booked SET notif_status = ? WHERE sb_id = ?";
		jdbcTemplate.update(sql, 1, sbId);
	}

//	@Override
//	public List<SeatsBooked> findAllByEId(Employee emp) {
//		String query = "select * from seats_booked where e_id = ?";
//		int empId = emp.geteId();
//		@SuppressWarnings("deprecation")
//		List<SeatsBooked> seatsBooked = jdbcTemplate.query(query, new Object[] { empId },
//				new ResultSetExtractor<List<SeatsBooked>>() {
//					public List<SeatsBooked> extractData(ResultSet rs) throws SQLException, DataAccessException {
//						List<SeatsBooked> list = new ArrayList<SeatsBooked>();
//						while (rs.next()) {
//							SeatsBooked seatsBooked = new SeatsBooked();
//							seatsBooked.setSbId(rs.getInt("sb_id"));
//							int seatId = rs.getInt("s_id");
//							Seat seat = seatRepo.findById(seatId).get();
//							seatsBooked.setsId(seat);
////							int mngId = emp.getManagerDetails().getmId();
////							Manager mng = managerRepo.findById(mngId);
////							System.out.println(mng);					
////							emp.setManagerDetails(mng);
//							seatsBooked.seteId(emp);
//							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//							String sbSDate = rs.getString("sb_start_date");
//							LocalDateTime dateTime = LocalDateTime.parse(sbSDate, formatter);
//							seatsBooked.setSbStartDate(dateTime);
//							String sbEDate = rs.getString("sb_end_date");
//							LocalDateTime dateTime1 = LocalDateTime.parse(sbEDate, formatter);
//							seatsBooked.setSbEndDate(dateTime1);
//							String sbISDate = rs.getString("punch_in");
//							LocalDateTime dateTimeI = LocalDateTime.parse(sbISDate, formatter);
//							seatsBooked.setPunchIn(dateTimeI);
//							String sbOSDate = rs.getString("punch_out");
//							LocalDateTime dateTimeO = LocalDateTime.parse(sbOSDate, formatter);
//							seatsBooked.setPunchOut(dateTimeO);
//							seatsBooked.setCode(rs.getString("code"));
//							seatsBooked.setCurrent(rs.getBoolean("current"));
//							list.add(seatsBooked);
//						}
//						return list;
//						
//					}
//
//				});
////		System.out.println(seatsBooked);
//		return seatsBooked;
//	}
	@SuppressWarnings("deprecation")
	@Override
	public List<SeatsBooked> getSeatsBookedByDate(LocalDateTime startDate, LocalDateTime endDate) {
		String query = "SELECT * FROM seats_booked WHERE sb_date >= ? AND sb_date <= ?";
		List<SeatsBooked> seatsBooked = new ArrayList<>();
		jdbcTemplate.query(query, new Object[] { startDate, endDate }, resultSet -> {
			SeatsBooked sb = new SeatsBooked();
			sb.setSbId(resultSet.getInt("sb_id"));
			sb.setSbDate(resultSet.getObject("sb_date", LocalDateTime.class));
			sb.setPunchIn(resultSet.getObject("punch_in", LocalDateTime.class));
            sb.setPunchOut(resultSet.getObject("punch_out", LocalDateTime.class));
            sb.setCurrent(resultSet.getBoolean("current"));
            sb.setCode(resultSet.getString("code"));
            sb.setFood(resultSet.getBoolean("food"));
			sb.setsId(seatRepo.findById(resultSet.getInt("s_id")).get());
			sb.seteId(employeeRepo.findById(resultSet.getInt("e_id")).get());
			sb.setSt(shiftTimingsRepo.findById(resultSet.getInt("st_id")).orElse(null));

			seatsBooked.add(sb);
		});
		return seatsBooked;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SeatsBooked> getSeatsBookedByEmployeeAndDate(int empId, LocalDateTime startDate,
			LocalDateTime endDate) {
		String query = "SELECT sb.* FROM seats_booked sb JOIN employee e ON sb.e_id = e.e_id JOIN user u ON e.e_id = u.e_id WHERE u.emp_id = ? AND sb.sb_date BETWEEN ? AND ?";
		List<SeatsBooked> seatsBooked = new ArrayList<>();

		jdbcTemplate.query(query, new Object[] { empId, startDate, endDate }, resultSet -> {
			SeatsBooked sb = new SeatsBooked();
			sb.setSbId(resultSet.getInt("sb_id"));
			sb.setSbDate(resultSet.getObject("sb_date", LocalDateTime.class));
			sb.setPunchIn(resultSet.getObject("punch_in", LocalDateTime.class));
            sb.setPunchOut(resultSet.getObject("punch_out", LocalDateTime.class));
            sb.setCurrent(resultSet.getBoolean("current"));
            sb.setCode(resultSet.getString("code"));
            sb.setFood(resultSet.getBoolean("food"));
			sb.setsId(seatRepo.findById(resultSet.getInt("s_id")).get());
			sb.seteId(employeeRepo.findById(resultSet.getInt("e_id")).get());
			sb.setSt(shiftTimingsRepo.findById(resultSet.getInt("st_id")).orElse(null));

			seatsBooked.add(sb);
		});

		return seatsBooked;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SeatsBooked> getSeatsBookedByShiftTimingBetweenDates(int stId,LocalDateTime startDate, LocalDateTime endDate) {
		String sql = "SELECT * FROM seats_booked WHERE st_id = ? AND sb_date >= ? AND sb_date <= ?";
	    List<SeatsBooked> seatsBookedList = new ArrayList<>();
	    jdbcTemplate.query(sql, new Object[]{stId, startDate, endDate}, (resultSet, i) -> {
	        SeatsBooked seatsBooked = new SeatsBooked();
	        seatsBooked.setSbId(resultSet.getInt("sb_id"));
	        seatsBooked.setSbDate(resultSet.getObject("sb_date", LocalDateTime.class));
			seatsBooked.setPunchIn(resultSet.getObject("punch_in", LocalDateTime.class));
            seatsBooked.setPunchOut(resultSet.getObject("punch_out", LocalDateTime.class));
	        seatsBooked.setCurrent(resultSet.getBoolean("current"));
	        seatsBooked.setCode(resultSet.getString("code"));
	        seatsBooked.setFood(resultSet.getBoolean("food"));
			seatsBooked.setsId(seatRepo.findById(resultSet.getInt("s_id")).get());
			seatsBooked.seteId(employeeRepo.findById(resultSet.getInt("e_id")).get());
			seatsBooked.setSt(shiftTimingsRepo.findById(resultSet.getInt("st_id")).orElse(null));
	        seatsBookedList.add(seatsBooked);
	        return seatsBooked;
	    });
	    return seatsBookedList;
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public SeatsBooked findCurrentSeat(Employee emp) {
		int empId = emp.geteId();
		System.out.println(empId);
		System.out.println(emp.getEmpName());
		List<SeatsBooked> sb1= seatsBookedRepo.findAllByeId(emp);
		System.out.println(sb1);
		return null;
//		String query = "select * from seats_booked where current = 1 and e_id = ?";
//		return jdbcTemplate.queryForObject(query, new Object[] { empId }, BeanPropertyRowMapper.newInstance(SeatsBooked.class));
//		return jdbcTemplate.queryForObject(query, new Object[] { empId }, new RowMapper<SeatsBooked>() {
//			public SeatsBooked mapRow(ResultSet rs, int rowNum) throws SQLException {
//				SeatsBooked seatsBooked = new SeatsBooked();
//				seatsBooked.setSbId(rs.getInt("sb_id"));
//				int seatId = rs.getInt("s_id");
//				Seat seat = seatRepo.findById(seatId).get();
//				seatsBooked.setsId(seat);
////				int mngId = emp.getManagerDetails().getmId();
////				Manager mng = managerRepo.findById(mngId);
////				System.out.println(mng);					
////				emp.setManagerDetails(mng);
//				seatsBooked.seteId(emp);
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				String sbSDate = rs.getString("sb_date");
//				LocalDateTime dateTime = LocalDateTime.parse(sbSDate, formatter);
//				seatsBooked.setSbDate(dateTime);
////				String sbEDate = rs.getString("sb_end_date");
////				LocalDateTime dateTime1 = LocalDateTime.parse(sbEDate, formatter);
////				seatsBooked.setSbEndDate(dateTime1);
//				String sbISDate = rs.getString("punch_in");
//				LocalDateTime dateTimeI = LocalDateTime.parse(sbISDate, formatter);
//				seatsBooked.setPunchIn(dateTimeI);
//				String sbOSDate = rs.getString("punch_out");
//				LocalDateTime dateTimeO = LocalDateTime.parse(sbOSDate, formatter);
//				seatsBooked.setPunchOut(dateTimeO);
//				seatsBooked.setCode(rs.getString("code"));
//				return seatsBooked;	
	}

	@Override
	public List<Integer> countTotalSeats() {
		String query = "SELECT COUNT(*) FROM seat";
		List<Integer> totalSeats = jdbcTemplate.queryForList(query, Integer.class);
		return totalSeats;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Seat> findAvailableSeatsByDate(LocalDate date) {
		String query = "SELECT s.s_id, s.s_name " + "FROM seat s " + "WHERE s.s_id NOT IN ( " + "   SELECT sb.s_id "
				+ "   FROM seats_booked sb " + "   WHERE DATE(sb.sb_date) = ? AND sb.current = true" + ")";
		List<Seat> availableSeats = jdbcTemplate.query(query, new Object[] { date },
				new BeanPropertyRowMapper<>(Seat.class));
		return availableSeats;
	}

	@Override
	public void bookSeat(SeatsBooked seatsBooked) {
		String sql = "INSERT INTO seats_booked (sb_id, sb_date, punch_in, punch_out, current, code, s_id, e_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, seatsBooked.getSbId(), seatsBooked.getSbDate(), seatsBooked.getPunchIn(),
					seatsBooked.getPunchOut(), seatsBooked.isCurrent(), seatsBooked.getCode(), seatsBooked.getsId(),
					seatsBooked.geteId());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkIfEmployeeAlredyBookTheSeat(int eId, LocalDateTime from, LocalDateTime to)
			throws DataAccessException {
		String sql = "SELECT COUNT(*) FROM seats_booked WHERE e_id = ? AND sb_date BETWEEN ? AND ? AND current = true";

		try {
			int cnt = jdbcTemplate.queryForObject(sql, new Object[] { eId, from, to }, Integer.class);

			if (cnt > 0)
				return true;
			return false;
		} catch (DataAccessException e) {
			return false;
		}
	}
	
	@Override
	public boolean checkIfEmployeeAlredyBookTheSeatDaily(int eId,LocalDateTime from) throws DataAccessException{
		String sql= "SELECT COUNT(*) FROM seats_booked WHERE e_id = ? AND sb_date=from AND current = true";
       
		try {
			int cnt = jdbcTemplate.queryForObject(sql,new Object[] { eId,from }, Integer.class);
		
        if(cnt>0)
        	return true;
        return false;
		}catch (DataAccessException e) {
			return false;
		}
	}
	

	@Override
	public boolean checkIfTheSameSeatBookingRecurring(int eId) throws DataAccessException {
		
		String sql = "SELECT COUNT(*) As bookings FROM seat s INNER JOIN seats_booked sb ON s.s_id = sb.s_id INNER JOIN employee e ON sb.e_id = e.e_id WHERE e.e_id=? GROUP BY s.s_id, s.s_name, e.emp_name HAVING COUNT(*) >= 1 ORDER BY bookings DESC;";
		try {
			@SuppressWarnings("deprecation")
			int cnt = jdbcTemplate.queryForObject(sql, new Object[] { eId }, Integer.class);

			if (cnt >= 1)
				return true;
			return false;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean canEmployeeBookSeat(int eId, int sId, LocalDate sbDate) throws DataAccessException {
		try {
			String sql = "SELECT COUNT(*) FROM seats_booked " + "WHERE e_id = ? "
					+ "AND DATE_FORMAT(sb_date, '%Y-%m-%d') = DATE_FORMAT(:sbDate, '%Y-%m-%d')";

			int count = jdbcTemplate.queryForObject(sql, new Object[] { eId }, Integer.class);
			return count == 0;
		} catch (DataAccessException e) {

			return false;
		}
	}

	@Override
	public byte[] generateSeatsBookedPDF(List<SeatsBooked> seatsBooked) throws Exception {
	    // Create a new PDF document
	    Document document = new Document(new Rectangle(612, 792), 50, 50, 50, 50);

	    // Create a byte array output stream to write the document to a byte array
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    // Create a PDF writer to write the document to the output stream
	    PdfWriter.getInstance(document, byteArrayOutputStream);
	    
	    

	    // Open the document
	    document.open();

	    Paragraph title = new Paragraph("Seats Booked Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
	    title.setAlignment(Element.ALIGN_CENTER);
	    document.add(title);

	    // Add some space between the title and the table
	    document.add(new Paragraph("\n"));
	    PdfPTable table = new PdfPTable(12);
	    table.setWidthPercentage(100);

	    float[] columnWidths = {1f, 1.2f, 1f, 1.5f, 1.5f, 1.5f, 1f, 1f, 1f, 1.5f, 1.5f,1f};
	    table.setWidths(columnWidths);

	    Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);

	    table.addCell(new PdfPCell(new Phrase("ID", font)));
	    table.addCell(new PdfPCell(new Phrase("Date", font)));
	    table.addCell(new PdfPCell(new Phrase("Current", font)));
	    table.addCell(new PdfPCell(new Phrase("Punch In", font)));
	    table.addCell(new PdfPCell(new Phrase("Punch Out", font)));
	    table.addCell(new PdfPCell(new Phrase("Code", font)));
	    table.addCell(new PdfPCell(new Phrase("Seat", font)));
	    table.addCell(new PdfPCell(new Phrase("Shift Start", font)));
	    table.addCell(new PdfPCell(new Phrase("Shift End", font)));
	    table.addCell(new PdfPCell(new Phrase("Employee ID", font)));
	    table.addCell(new PdfPCell(new Phrase("Employee Name", font)));
	    table.addCell(new PdfPCell(new Phrase("Food Opted", font)));

	    for (SeatsBooked sb : seatsBooked) {
	        table.addCell(new PdfPCell(new Phrase(String.valueOf(sb.getSbId()), font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getSbDate().toString(), font)));
	        table.addCell(new PdfPCell(new Phrase(String.valueOf(sb.isCurrent()), font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getPunchIn() != null ? sb.getPunchIn().toString() : "", font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getPunchOut() != null ? sb.getPunchOut().toString() : "", font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getCode() != null ? sb.getCode().toString() : "", font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getsId().getsName(), font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getSt().getStStart(), font)));
	        table.addCell(new PdfPCell(new Phrase(sb.getSt().getStEnd(), font)));
	        Employee emp = sb.geteId();
	        User user = userRepo.findByEmpDetails(emp);
	        if (emp != null) {
	            table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getEmpId()), font)));
	        } else {
	            table.addCell(new PdfPCell(new Phrase("", font)));
	        }
	        table.addCell(new PdfPCell(new Phrase(sb.geteId().getEmpName(), font)));
	        table.addCell(new PdfPCell(new Phrase(String.valueOf(sb.isFood()), font)));
	    }


	    document.add(table);

	    // Add a title and some text to the document
//	    document.add(new Paragraph("Employees"));
//
//	    // Add a table to the document to display the data
//	    PdfPTable empTable = new PdfPTable(4);
//	    empTable.addCell("ID");
//	    empTable.addCell("Name");
//	    empTable.addCell("Phone Number");
//	    empTable.addCell("Email");
//
//	    for (SeatsBooked sb : seatsBooked) {
//	        empTable.addCell(String.valueOf(sb.geteId().geteId()));
//	        empTable.addCell(sb.geteId().getEmpName());
//	        empTable.addCell(sb.geteId().getPhNum());
//	        empTable.addCell(sb.geteId().getMailId());
//	    }
//
//	    document.add(empTable);

	    // Close the document
	    document.close();

	    // Convert the byte array output stream to a byte array
	    byte[] pdfBytes = byteArrayOutputStream.toByteArray();

	    return pdfBytes;
	}


//	
//	    @Override
//	    public List<SeatsBooked> getSeatBookingsByEId(int eId)  {
//	        List<SeatsBooked> bookings = new ArrayList<>();
//
//	        try (Connection conn = getConnection();
//	             Statement stmt = conn.createStatement();
//	             ResultSet rs = stmt.executeQuery(
//	                     "SELECT s.s_id, s.s_name, COUNT(*) AS bookings, e.emp_name "
//	                   + "FROM seat s "
//	                   + "INNER JOIN seats_booked sb ON s.s_id = sb.s_id "
//	                   + "INNER JOIN employee e ON sb.e_id = e.e_id "
//	                   + "WHERE e.e_id=" + eId + " "
//	                   + "GROUP BY s.s_id, s.s_name, e.emp_name "
//	                   + "HAVING COUNT(*) >= 1 "
//	                   + "ORDER BY bookings DESC")) {
//
//	            while (rs.next()) {
//	                int seatId = rs.getInt("s_id");
//	                String seatName = rs.getString("s_name");
//	                int numBookings = rs.getInt("bookings");
//	                String employeeName = rs.getString("emp_name");
//	                
//
//	                SeatsBooked booking = new SeatsBooked(seatId, seatName, numBookings, employeeName);
//	                bookings.add(booking);
//	            }
//	        } 
//	  
//	        
//	        return bookings;
//	    }

//		private Connection getConnection() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}
//

//	@Override
//	public List<RecurringSeats> countRecurringSeats() {
//		String sql = "SELECT s.s_id, s.s_name, COUNT(*) AS bookings, e.emp_name\r\n"
//			+ "FROM seat s\r\n"
//				+ "INNER JOIN seats_booked sb ON s.s_id = sb.s_id\r\n"
//				+ "INNER JOIN employee e ON sb.e_id = e.e_id\r\n"
//				+ "WHERE   e.e_id=123\r\n"
//				+ "GROUP BY s.s_id, s.s_name, e.emp_name\r\n"
//			+ "HAVING COUNT(*) >= 1\r\n"
//				+ "ORDER BY bookings DESC;";
//		List<RecurringSeats> RecurringList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RecurringSeats.class));
//		return RecurringList;
//	}
//	
//
//	

}

//	public List<RecurringSeats> getRecurringSeats() {
//	    String sql = "SELECT s.s_id, s.s_name, COUNT(*) AS bookings, e.emp_name\r\n"
//	    		+ "FROM seat s\r\n"
//	    		+ "INNER JOIN seats_booked sb ON s.s_id = sb.s_id\r\n"
//	    		+ "INNER JOIN employee e ON sb.e_id = e.e_id\r\n"
//	    		+ "WHERE   e.e_id=123\r\n"
//	    		+ "GROUP BY s.s_id, s.s_name, e.emp_name\r\n"
//	    		+ "HAVING COUNT(*) >= 1\r\n"
//	    		+ "ORDER BY bookings DESC;";
//
//SELECT s.s_id, s.s_name, COUNT(*) AS bookings, e.emp_name
//FROM seat s
//INNER JOIN seats_booked sb ON s.s_id = sb.s_id
//INNER JOIN employee e ON sb.e_id = e.e_id
//WHERE   e.e_id=123
//GROUP BY s.s_id, s.s_name, e.emp_name
//HAVING COUNT(*) >= 1
//ORDER BY bookings DESC;
//	    List<RecurringSeats> RecurringList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RecurringSeats.class));
//	    return RecurringList;
//	}
// book a recurring seat
// view a recurring seat

//public List<Map<String, Object>> getSeatBookingsByEmpId(int empId) throws SQLException {
//    String sql = "SELECT s.s_name, sb.* " +
//                 "FROM seat s " +
//                 "JOIN seats_booked sb ON s.s_id = sb.s_id " +
//                 "WHERE sb.e_id = (SELECT e_id FROM employee WHERE emp_id = ?)";
//    try (Connection conn = dataSource.getConnection();
//         PreparedStatement stmt = conn.prepareStatement(sql)) {
//        stmt.setInt(1, empId);
//        try (ResultSet rs = stmt.executeQuery()) {
//            List<Map<String, Object>> results = new ArrayList<>();
//            ResultSetMetaData meta = rs.getMetaData();
//            int numColumns = meta.getColumnCount();
//            while (rs.next()) {
//                Map<String, Object> row = new HashMap<>();
//                for (int i = 1; i <= numColumns; i++) {
//                    String column = meta.getColumnName(i);
//                    Object value = rs.getObject(i);
//                    row.put(column, value);
//                }
//                results.add(row);
//            }
//            return results;
//        }
//    }
//}

//select s.*
//from seat s
//left join seats_booked sb on s.s_id = sb.s_id
//where sb.s_id IS NULL;
//
//}
//@Override
//
//public List<Seat> findByBooked(boolean booked) {
//    String sql = "SELECT * FROM seats WHERE booked = ?";
//    return jdbcTemplate.query(sql, new SeatRowMapper(), !booked);
//}
//
//
//
//
//
//private class SeatRowMapper implements RowMapper {
//    public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Seat seat = new Seat();
//        seat.setsId(rs.getLong("id"));
//        seat.setNumber(rs.getInt("number"));
//        seat.setBooked(rs.getBoolean("booked"));
//        return seat;
//    }
//}

//@Override
//public List<Seat> findByBooked(boolean booked) {
//    String sql = "SELECT * FROM seats WHERE booked = ?";
//    return jdbcTemplate.query(sql, new SeatRowMapper(), !booked);
//}
//
//private class SeatRowMapper implements RowMapper<Seat> {
//    @Override
//    public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Seat seat = new Seat();
//        seat.setId(rs.getLong("id"));
//        seat.setNumber(rs.getInt("number"));
//        seat.setBooked(rs.getBoolean("booked"));
//        return seat;
//    }
//}

//public List<Map<String, Object>> getSeatBookingsByEmployeeId(int employeeId) throws SQLException {
//    String sql = "SELECT s.s_name, sb.* " +
//                 "FROM seat s " +
//                 "JOIN seats_booked sb ON s.s_id = sb.s_id " +
//                 "WHERE sb.e_id = ?";
//    try (Connection conn = getConnection();
//         PreparedStatement stmt = conn.prepareStatement(sql)) {
//        stmt.setInt(1, employeeId);
//        try (ResultSet rs = stmt.executeQuery()) {
//            List<Map<String, Object>> results = new ArrayList<>();
//            ResultSetMetaData meta = rs.getMetaData();
//            int numColumns = meta.getColumnCount();
//            while (rs.next()) {
//                Map<String, Object> row = new HashMap<>();
//                for (int i = 1; i <= numColumns; i++) {
//                    String column = meta.getColumnName(i);
//                    Object value = rs.getObject(i);
//                    row.put(column, value);
//                }
//                results.add(row);
//            }
//            return results;
//        }
//    }
//}

//}

//@Repository
//public class SeatRepositoryImpl implements SeatRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    public SeatRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Seat> findByBooked(boolean booked) {
//        String sql = "SELECT * FROM seats WHERE booked = ?";
//        return jdbcTemplate.query(sql, new SeatRowMapper(), booked);
//    }
//}
//
//class SeatRowMapper implements RowMapper<Seat> {
//    @Override
//    public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Seat seat = new Seat();
//        seat.setId(rs.getLong("id"));
//        seat.setNumber(rs.getInt("number"));
//        seat.setBooked(rs.getBoolean("booked"));
//        return seat;
//    }
//}

//@Repository
//public class SeatBookingDAOImpl implements SeatBookingDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public SeatBookingDAOImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public int bookSeat(int seatNumber) {
//        String sql = "UPDATE seats SET booked = true WHERE seat_number = ?";
//        return jdbcTemplate.update(sql, seatNumber);
//    }
//
//    @Override
//    public int unbookSeat(int seatNumber) {
//        String sql = "UPDATE seats SET booked = false WHERE seat_number = ?";
//        return jdbcTemplate.update(sql, seatNumber);
//    }
//
//    @Override
//    public List<Integer> getBookedSeats() {
//        String sql = "SELECT seat_number FROM seats WHERE booked = true";
//        return jdbcTemplate.queryForList(sql, Integer.class);
//    }
//
//    @Override
//    public int countBookedSeats() {
//        String sql = "SELECT COUNT(*) FROM seats WHERE booked = true";
//        return jdbcTemplate.queryForObject(sql, Integer.class);
//    }
//}