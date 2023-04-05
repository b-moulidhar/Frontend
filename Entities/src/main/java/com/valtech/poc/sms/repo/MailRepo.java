package com.valtech.poc.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valtech.poc.sms.entities.Mail;

@Repository
public interface MailRepo extends JpaRepository<Mail, Integer>{
	
	List<Mail> findAllByStatusFalse();

}
