package com.valtech.poc.sms.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SeatsBooked")
public class SeatsBooked {
	@Id
	@Column(name = "sbId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sbId;
	private LocalDateTime sbStartDate;
	private LocalDateTime sbEndDate;
	private LocalDateTime punchIn;
	private LocalDateTime punchOut;
	private boolean current;
	private String code;
	@OneToOne(targetEntity = Seat.class)
	@JoinColumn(name = "sId", referencedColumnName = "sId")
	private Seat sId;
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "eId", referencedColumnName = "eId")
	private Employee eId;
	private boolean notifStatus;

	public boolean isNotifStatus() {
		return notifStatus;
	}

	public void setNotifStatus(boolean notifStatus) {
		this.notifStatus = notifStatus;
	}

	public int getSbId() {
		return sbId;
	}

	public void setSbId(int sbId) {
		this.sbId = sbId;
	}

	public LocalDateTime getPunchIn() {
		return punchIn;
	}

	public void setPunchIn(LocalDateTime punchIn) {
		this.punchIn = punchIn;
	}

	public LocalDateTime getPunchOut() {
		return punchOut;
	}

	public void setPunchOut(LocalDateTime punchOut) {
		this.punchOut = punchOut;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Seat getsId() {
		return sId;
	}

	public void setsId(Seat sId) {
		this.sId = sId;
	}

	public Employee geteId() {
		return eId;
	}

	public void seteId(Employee eId) {
		this.eId = eId;
	}

	public LocalDateTime getSbStartDate() {
		return sbStartDate;
	}

	public void setSbStartDate(LocalDateTime sbStartDate) {
		this.sbStartDate = sbStartDate;
	}

	public LocalDateTime getSbEndDate() {
		return sbEndDate;
	}

	public void setSbEndDate(LocalDateTime sbEndDate) {
		this.sbEndDate = sbEndDate;
	}

	public SeatsBooked() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatsBooked(LocalDateTime sbStartDate, LocalDateTime sbEndDate, LocalDateTime punchIn,
			LocalDateTime punchOut, boolean current, String code, Seat sId, Employee eId, boolean notifStatus) {
		super();
		this.sbStartDate = sbStartDate;
		this.sbEndDate = sbEndDate;
		this.punchIn = punchIn;
		this.punchOut = punchOut;
		this.current = current;
		this.code = code;
		this.sId = sId;
		this.eId = eId;
		this.notifStatus = notifStatus;
	}

	public SeatsBooked(int sbId, LocalDateTime sbStartDate, LocalDateTime sbEndDate, LocalDateTime punchIn,
			LocalDateTime punchOut, boolean current, String code, Seat sId, Employee eId, boolean notifStatus) {
		super();
		this.sbId = sbId;
		this.sbStartDate = sbStartDate;
		this.sbEndDate = sbEndDate;
		this.punchIn = punchIn;
		this.punchOut = punchOut;
		this.current = current;
		this.code = code;
		this.sId = sId;
		this.eId = eId;
		this.notifStatus = notifStatus;
	}

	@Override
	public String toString() {
		return "SeatsBooked [sbId=" + sbId + ", sbStartDate=" + sbStartDate + ", sbEndDate=" + sbEndDate + ", punchIn="
				+ punchIn + ", punchOut=" + punchOut + ", current=" + current + ", code=" + code + ", sId=" + sId
				+ ", eId=" + eId + "]";
	}

}