package com.valtech.poc.sms.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.AttendanceTable;
import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;

@Component
public class AttendanceDaoImpl implements AttendanceDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void approveAttendance(int atId) {
		String sql="UPDATE attendance_table SET approval=? WHERE at_id=?";
		jdbcTemplate.update(sql, 1 ,atId);
		
	}



	@Override
	public AttendanceTable getList(int atId) {
		String query="select *from attendance_table a JOIN employee e ON a.e_id = e.e_id JOIN manager m ON e.m_id = m.m_id JOIN employee e1 ON m.e_id = e1.e_id 	WHERE a.at_id=?";
		Map<String, Object> result = jdbcTemplate.queryForMap(query, atId);
		 AttendanceTable attendance = new AttendanceTable();
		    attendance.setAtId((int) result.get("at_id"));
		    attendance.setStartDate((String) result.get("start_date"));
		    attendance.setEndDate((String) result.get("end_date"));
		    attendance.setShiftStart((String) result.get("shift_start"));
		    attendance.setShiftEnd((String) result.get("shift_end"));
		    attendance.setApproval((Boolean) result.get("approval"));
		    
		    Employee employee = new Employee();
		    employee.seteId((int) result.get("e_id"));
		    employee.setEmpName((String) result.get("emp_name"));
		    employee.setMailId((String) result.get("mail_id"));
		    employee.setPhNum((String) result.get("Ph_num"));
		    attendance.seteId(employee);
		    
		    
		    Manager manager = new Manager();
		    manager.setmId((int) result.get("m_id"));
		   int m=(int) result.get("m_id");
		   Employee employee1 = new Employee();
		   String query1="select * from employee e JOIN Manager m ON e.e_id = m.e_id where m.m_id=?";
		    		Map<String, Object> result1 = jdbcTemplate.queryForMap(query1, m);
		    	
		    employee1.seteId((int) result1.get("e_id"));
		    employee1.setEmpName((String) result1.get("emp_name"));
		    employee1.setMailId((String) result1.get("mail_id"));
		    employee1.setPhNum((String) result1.get("Ph_num"));
		    manager.setManagerDetails(employee1);
		    employee.setManagerDetails(manager);
		    
		    return attendance;
	}

	@Override
	public List<Map<String, Object>> getCompleteAttendanceList() {
		String query = "SELECT * FROM attendance_table a JOIN employee e ON a.e_id = e.e_id JOIN manager m ON e.m_id = m.m_id";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        return results;
	}

	@Override
	public Map<String, Object> getAttendanceListForEachEmployee(int atId) {
String query="select *from attendance_table a JOIN employee e ON a.e_id = e.e_id JOIN manager m ON e.m_id = m.m_id	WHERE a.at_id=?";
		
    	Map<String, Object> result = jdbcTemplate.queryForMap(query, atId);
		return result;
	}

	@Override
	public List<Map<String, Object>> getAttendanceForEmployeeBasedOnEmployeeId(int eId) {
String query="select *from attendance_table a JOIN employee e ON a.e_id = e.e_id JOIN manager m ON e.m_id = m.m_id	WHERE a.e_id=?";
		
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(query, eId);
		return result;
	}

	@Override
	public List<Map<String, Object>> getAttendanceListForApproval(int eId) {
String query="select * from attendance_table a JOIN employee e ON a.e_id = e.e_id JOIN manager m ON e.m_id = m.m_id	WHERE a.e_id=? and approval=?";
		
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(query, eId,false);
		return result;
	}
	
	@Override
	public void deleteAttendanceRequest(int atId) {
		String sql = "delete from attendance_table where at_id= ?";
		jdbcTemplate.update(sql, atId);
		
	}
	
	@Override
	public String getMailIdByAtId(int atId) {
        String query="select mail_id from employee e JOIN attendance_table a ON a.e_id = e.e_id WHERE a.at_id=?";
    	return jdbcTemplate.queryForObject(query, String.class, atId);
		
	}

	@Override
	public boolean checkIfTheAttendanceIsRegularized(int eId, String startDate, String endDate) {
		String sql= "SELECT COUNT(*) FROM attendance_table WHERE e_id = ? AND start_date = ? AND end_date=? ";
	       
		try {
			@SuppressWarnings("deprecation")
			int cnt = jdbcTemplate.queryForObject(sql,new Object[] { eId,startDate,endDate }, Integer.class);
		
        if(cnt>0)
        	return true;
        return false;
		}catch (DataAccessException e) {
			return false;
		}
	}



	@Override
	public boolean checkIfTheAttendanceIsRegularised(int eId, String startDate, String endDate) {
		String sql = "SELECT COUNT(*) FROM attendance_table WHERE e_id = ? AND start_date BETWEEN ? AND ? AND end_date BETWEEN ? AND ?";
		try {
			@SuppressWarnings("deprecation")
			int cnt = jdbcTemplate.queryForObject(sql, new Object[] { eId, startDate, endDate, startDate, endDate }, Integer.class);

			if (cnt > 0)
				return true;
			return false;
		} catch (DataAccessException e) {
			return false;
		}
	}




	
	
}
