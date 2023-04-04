package com.valtech.poc.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;

@Component
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@SuppressWarnings("deprecation")
	@Override
    public Employee getEmployeeByeId(int id) {
        String sql = "SELECT * FROM Employee WHERE e_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }
    
    private class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.seteId(rs.getInt("e_id"));
            employee.setEmpName(rs.getString("emp_name"));
            employee.setPhNum(rs.getString("ph_num"));
            employee.setMailId(rs.getString("mail_id"));
            int managerId = rs.getInt("m_id");
            if (!rs.wasNull()) {
                Manager manager = new Manager();
                manager.setmId(managerId);
                employee.setManagerDetails(manager);
            }
            return employee;
        }
    }
	
	@Override
	public List<Employee> getAllEmployees(int empID) {
		String sql="select * from employee where m_id=(select m_id from manager where e_id=(select e_id from user where emp_id=?))";
	    @SuppressWarnings("deprecation")
		List<Employee> employees = jdbcTemplate.query(
	    		sql,new Object[]{empID},new ResultSetExtractor<List<Employee>>() {
	    			public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException{
	    				List<Employee> list= new ArrayList<Employee>();
	    				while(rs.next()) {
	    					Employee employee=new Employee();
	    					employee.setEmpName(rs.getString("emp_name"));
			                employee.seteId(rs.getInt("e_id"));
			                employee.setPhNum(rs.getString("ph_num"));
			                employee.setMailId(rs.getString("mail_id"));
			                list.add(employee);
	    				}
	    				return list;
	    			}
	    			
	    		});
		return employees;
	}

	
	
	

}
