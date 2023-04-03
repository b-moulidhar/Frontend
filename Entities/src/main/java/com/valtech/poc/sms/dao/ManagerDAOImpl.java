package com.valtech.poc.sms.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Employee;

@Component
public class ManagerDAOImpl implements ManagerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

				@SuppressWarnings("deprecation")
				@Override
				public Employee getManagerByEmpId(int empId) {
					return (Employee)  this.jdbcTemplate.queryForObject(
					        "select * from employee where e_id=(select e_id from user u where u.emp_id=? AND u.u_id=(select ur.u_id from roles r INNER JOIN user_roles ur ON r.r_id = ur.r_id AND r.role in ('Manager')))",
					        new Object[]{empId},
					        new RowMapper<Employee>() {
					        	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					                Employee employee = new Employee();
					                employee.setEmpName(rs.getString("emp_name"));
					                employee.seteId(rs.getInt("e_id"));
					                employee.setPhNum(rs.getString("ph_num"));
					                employee.setMailId(rs.getString("mail_id"));
					                return employee;
					            }
								
					        });
				}

				@Override
				public List<String> getAllManagerNames() {
					
					return null;
				}

				
	        }