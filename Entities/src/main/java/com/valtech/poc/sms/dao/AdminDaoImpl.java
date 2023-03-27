package com.valtech.poc.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.AttendanceTable;

@Component
public class AdminDaoImpl implements AdminDao {
    
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int getFoodCount(LocalDateTime dateTime) {
		String query="select count from Food where ft_date=?";
		return jdbcTemplate.queryForObject(query, Integer.class, dateTime);
	}

	@Override
	public int getSeatBookedCount(LocalDateTime dateTime) {
		String query="select count(sb_date) from seats_booked where sb_date=?"; 
	    return jdbcTemplate.queryForObject(query, Integer.class, dateTime);
	}
	
	@Override
	public void approveAttendance(int atId) {
		String sql="UPDATE attendance_table SET approval=? WHERE at_id=?";
		jdbcTemplate.update(sql, 1 ,atId);
		
	}

	@Override
	public List<AttendanceTable> listAttendance() {
		String SQL = "select * from attendance_table";
    	List <AttendanceTable> att = 
    			jdbcTemplate.query( SQL, new ResultSetExtractor<List<AttendanceTable>>()
    	{
    		public List<AttendanceTable> extractData(ResultSet rs) throws SQLException, DataAccessException { 
    			List<AttendanceTable> list = new ArrayList<AttendanceTable>();
    			while(rs.next()){ AttendanceTable attendanceTable = new AttendanceTable();
    			attendanceTable.setAtId(rs.getInt("at_id"));
    			attendanceTable.setStartDate(rs.getString("start_date")); 
    			attendanceTable.setEndDate(rs.getString("end_date")); 
    			attendanceTable.setShiftStart(rs.getString("shift_start"));
    			attendanceTable.setShiftEnd(rs.getString("shift_end")); 
    			attendanceTable.setApproval(rs.getBoolean("approval"));
    			list.add(attendanceTable); 
    			} 
    			return list; 
    			} 
    		} 
    			);
    	return att; 
		
		
	}
	
}
