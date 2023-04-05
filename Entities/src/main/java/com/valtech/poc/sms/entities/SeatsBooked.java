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
import jakarta.persistence.Transient;

@Entity
@Table(name = "SeatsBooked")
public class SeatsBooked {
	@Id
	@Column(name = "sbId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sbId;
	private LocalDateTime sbDate;
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
	private boolean verified;
	private boolean food;
	@OneToOne(targetEntity = ShiftTimings.class)
	@JoinColumn(name = "stId", referencedColumnName = "stId")
	private ShiftTimings st;

	@Transient
	private int emId;

	public boolean isFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
	}

	public ShiftTimings getSt() {
		return st;
	}

	public void setSt(ShiftTimings st) {
		this.st = st;
	}

	public int getEmId() {
		return emId;
	}

	public void setEmId(int emId) {
		this.emId = emId;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

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

	public LocalDateTime getSbDate() {
		return sbDate;
	}

	public void setSbDate(LocalDateTime sbDate) {
		this.sbDate = sbDate;
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

	public SeatsBooked() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatsBooked(int sbId, LocalDateTime sbDate, LocalDateTime punchIn, LocalDateTime punchOut, boolean current,
			String code, Seat sId, Employee eId, boolean notifStatus, boolean verified, boolean food, ShiftTimings st) {
		super();
		this.sbId = sbId;
		this.sbDate = sbDate;
		this.punchIn = punchIn;
		this.punchOut = punchOut;
		this.current = current;
		this.code = code;
		this.sId = sId;
		this.eId = eId;
		this.notifStatus = notifStatus;
		this.verified = verified;
		this.food = food;
		this.st = st;
	}

	public SeatsBooked(LocalDateTime sbDate, LocalDateTime punchIn, LocalDateTime punchOut, boolean current,
			String code, Seat sId, Employee eId, boolean notifStatus, boolean verified, boolean food, ShiftTimings st) {
		super();
		this.sbDate = sbDate;
		this.punchIn = punchIn;
		this.punchOut = punchOut;
		this.current = current;
		this.code = code;
		this.sId = sId;
		this.eId = eId;
		this.notifStatus = notifStatus;
		this.verified = verified;
		this.food = food;
		this.st = st;
	}

	@Override
	public String toString() {
		return "SeatsBooked [sbId=" + sbId + ", sbDate=" + sbDate + ", punchIn=" + punchIn + ", punchOut=" + punchOut
				+ ", current=" + current + ", code=" + code + ", sId=" + sId + ", eId=" + eId + ", notifStatus="
				+ notifStatus + ", verified=" + verified + ", food=" + food + ", st=" + st + ", emId=" + emId + "]";
	}



}