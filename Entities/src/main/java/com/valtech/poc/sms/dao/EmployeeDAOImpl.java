package com.valtech.poc.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public Employee getEmployeeByeId(int id) throws Exception {
		try {
			String sql = "SELECT * FROM Employee WHERE e_id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new EmployeeRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("Employee with id {} does not exist in the database", id);
			throw new Exception("Employee with Id " + id + " does not exist in the database!", e);
		} catch (Exception e) {
			logger.error("An error occurred while fetching employee with id {}", id, e);
			throw new Exception("An error occurred while fetching employee with Id " + id, e);
		}
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
	public List<Employee> getAllEmployees(int eID) {
		String sql = "select * from employee where m_id=(select m_id from manager where e_id=?)";
		@SuppressWarnings("deprecation")
		List<Employee> employees = jdbcTemplate.query(sql, new Object[] { eID },
				new ResultSetExtractor<List<Employee>>() {
					public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Employee> list = new ArrayList<Employee>();
						while (rs.next()) {
							Employee employee = new Employee();
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

	@Override
	public List<Map<String, Object>> getAllEmployeesUnderTheManager(int eId) {
		String query = "SELECT * FROM employee WHERE m_id = (SELECT m_id FROM manager WHERE e_id = ?)";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(query, eId);

		return result;
	}

}
