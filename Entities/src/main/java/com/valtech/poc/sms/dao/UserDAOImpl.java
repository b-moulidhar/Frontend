package com.valtech.poc.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.Manager;


@Component
public class UserDAOImpl implements UserDAO {
	 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Manager getMidByMname(String managerName,Employee emp) {
		String sql="select * from manager where e_id=(select e_id from employee where emp_name=?)";
//		return jdbcTemplate.query(sql,new Object[]{managerName}, Manager.class);
		Manager mng=  this.jdbcTemplate.queryForObject(
		       sql,
		        new Object[]{managerName},
		        new RowMapper<Manager>() {
		        	public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Manager manager = new Manager();
		                manager.setmId(rs.getInt("m_id"));
		                manager.setManagerDetails(emp);
		                return manager;
		            }
					
		        });
		System.out.println(mng);
		return mng;
	}
//
//	@Override
//	public void saveEmployee(Employee employee, int mId) {
//		String sql="insert into employee values (?,?,?,?)";
//		jdbcTemplate.update(sql,employee.getEmpName(),employee.getMailId(),employee.getPhNum(),mId);
//	}
////
//	@Override
//	public void saveUser(User user, Employee eId) {
//		String sql="insert into user values (?,?,?,?,?)";
//		jdbcTemplate.update(sql,false,user.getEmpId(),user.getPass(),eId.geteId(),null);
//	}

	@Override
	public int getRidByRoleName(String role) {
		String sql="select r_id from roles where role=?";
		return jdbcTemplate.queryForObject(sql, Integer.class,role);
	}

	@Override
	public void saveUserRole(int uId, int rId) {
		String sql="insert into user_roles values(?,?)";
		jdbcTemplate.update(sql,uId,rId);
	}

//	
	@Override
	public void saveManager(Manager mng) {
		String sql="insert into manager values (?)";
		jdbcTemplate.update(sql,mng);
	}
	@SuppressWarnings("deprecation")
	@Override
	public int checkIfEmpIdExist(int empId) {

		String sql="select emp_id from user where emp_id=?";
		return jdbcTemplate.queryForObject(sql,new Object[]{empId},Integer.class);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		String sql="delete from employee where e_id=?";
		jdbcTemplate.update(sql,emp.geteId());
	}

	@Override
	public void deleteUserRoles(int uId) {
		
		String sql="delete from user_roles where u_id=?";
		jdbcTemplate.update(sql,uId);
	}

	@Override
	public List<String> getMangerNames() {
		// TODO Auto-generated method stub
		String query = "select emp_name"
				+ " from  employee e,user u,user_roles ur"
				+ " where e.e_id=u.e_id and ur.u_id=u.u_id and ur.r_id=(select r_id from roles where role=\"Manager\")";
		List<String> managernames = jdbcTemplate.queryForList(query, String.class);
		return managernames;
	}

	


}
