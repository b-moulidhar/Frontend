package com.valtech.poc.sms.entities;

import java.time.LocalDateTime;

public class SeatBookingTempOne {

	private String shiftTimings;
	private Boolean food;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	public String getShiftTimings() {
		return shiftTimings;
	}
	public void setShiftTimings(String shiftTimings) {
		this.shiftTimings = shiftTimings;
	}
	public Boolean getFood() {
		return food;
	}
	public void setFood(Boolean food) {
		this.food = food;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	
}
