package com.valtech.poc.sms.entities;

import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@Column(name = "uId")
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Override
	public String toString() {
		return "User [uId=" + uId + ", empId=" + empId + ", pass=" + pass + ", empDetails=" + empDetails + ", approval="
				+ approval + ", roles=" + roles + ", otp=" + otp + "]";
	}

	

}
