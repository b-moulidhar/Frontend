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
@Table(name = "Manager")
public class Manager {
	@Id
	@Column(name = "mId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "eId", referencedColumnName = "eId")
	private Employee managerDetails;

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public Employee getManagerDetails() {
		return managerDetails;
	}

	public void setManagerDetails(Employee managerDetails) {
		this.managerDetails = managerDetails;
	}

	@Override
	public String toString() {
		return "Manager [mId=" + mId + ", managerDetails=" + managerDetails + "]";
	}

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int mId, Employee managerDetails) {
		super();
		this.mId = mId;
		this.managerDetails = managerDetails;
	}

	public Manager(Employee managerDetails) {
		super();
		this.managerDetails = managerDetails;
	}

}
