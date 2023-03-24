package com.valtech.poc.sms.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@Column(name = "uId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int uId;
	private int empId;
	private String pass;
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "eId", referencedColumnName = "eId")
	private Employee empDetails;
	private boolean approval;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "uId"), inverseJoinColumns = @JoinColumn(name = "rId"))
	private Set<Roles> roles = new HashSet<Roles>();
	
	@OneToOne(targetEntity = Otp.class)
	@JoinColumn(name = "oId", referencedColumnName = "oId")
	private Otp otp;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Employee getEmpDetails() {
		return empDetails;
	}

	public void setEmpDetails(Employee empDetails) {
		this.empDetails = empDetails;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Otp getOtp() {
		return otp;
	}

	public void setOtp(Otp otp) {
		this.otp = otp;
	}

	public User() {
		super();
	}

	public User(int uId, int empId, String pass, Employee empDetails, boolean approval, Set<Roles> roles, Otp otp) {
		super();
		this.uId = uId;
		this.empId = empId;
		this.pass = pass;
		this.empDetails = empDetails;
		this.approval = approval;
		this.roles = roles;
		this.otp = otp;
	}

	public User(int empId, String pass, Employee empDetails, boolean approval, Set<Roles> roles, Otp otp) {
		super();
		this.empId = empId;
		this.pass = pass;
		this.empDetails = empDetails;
		this.approval = approval;
		this.roles = roles;
		this.otp = otp;
	}

	
	
	
	
	
}
