package com.valtech.poc.sms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@Column(name = "eId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;
	private String empName;
	private String phNum;
	private String mailId;
	@OneToOne(targetEntity = Manager.class)
	@JoinColumn(name = "mId", referencedColumnName = "mId")
	private Manager managerDetails;

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhNum() {
		return phNum;
	}

	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public Manager getManagerDetails() {
		return managerDetails;
	}

	public void setManagerDetails(Manager managerDetails) {
		this.managerDetails = managerDetails;
	}

	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", empName=" + empName + ", phNum=" + phNum + ", mailId=" + mailId
				+ ", managerDetails=" + managerDetails + "]";
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int eId, String empName, String phNum, String mailId, Manager managerDetails) {
		super();
		this.eId = eId;
		this.empName = empName;
		this.phNum = phNum;
		this.mailId = mailId;
		this.managerDetails = managerDetails;
	}

	public Employee(String empName, String phNum, String mailId, Manager managerDetails) {
		super();
		this.empName = empName;
		this.phNum = phNum;
		this.mailId = mailId;
		this.managerDetails = managerDetails;
	}

}
