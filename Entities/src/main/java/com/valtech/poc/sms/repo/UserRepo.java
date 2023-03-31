package com.valtech.poc.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	List<User> findAll();

	User findByEmpDetails(Employee empDetails);

	User findByEmpId(int empId);

	User findById(int uId);

	void save(Employee employee);




	
}
