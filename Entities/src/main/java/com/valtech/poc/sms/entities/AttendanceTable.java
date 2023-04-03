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
@Table(name = "AttendanceTable")
public class AttendanceTable {
	@Id
	@Column(name = "atId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int atId;
	private String startDate;
	private String endDate;
	private String shiftStart;
	private String shiftEnd;
	private boolean approval;
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "eId", referencedColumnName = "eId")
	private Employee eId;
	

	public int getAtId() {
		return atId;
	}

	public void setAtId(int atId) {
		this.atId = atId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getShiftStart() {
		return shiftStart;
	}

	public void setShiftStart(String shiftStart) {
		this.shiftStart = shiftStart;
	}

	public String getShiftEnd() {
		return shiftEnd;
	}

	public void setShiftEnd(String shiftEnd) {
		this.shiftEnd = shiftEnd;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Employee geteId() {
		return eId;
	}

	public void seteId(Employee eId) {
		this.eId = eId;
	}

	public AttendanceTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttendanceTable(String startDate, String endDate, String shiftStart, String shiftEnd, boolean approval,
			Employee eId) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.approval = approval;
		this.eId = eId;
	}

	public AttendanceTable(int atId, String startDate, String endDate, String shiftStart, String shiftEnd,
			boolean approval, Employee eId) {
		super();
		this.atId = atId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.approval = approval;
		this.eId = eId;
	}

	@Override
	public String toString() {
		return "AttendanceTable [atId=" + atId + ", startDate=" + startDate + ", endDate=" + endDate + ", shiftStart="
				+ shiftStart + ", shiftEnd=" + shiftEnd + ", approval=" + approval + ", eId=" + eId + "]";
	}

	
}