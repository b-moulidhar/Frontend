package com.valtech.poc.sms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Seat")
public class Seat {
	@Id
	@Column(name = "sId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sId;
	private String sName;

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}


	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(int sId, String sName) {
		super();
		this.sId = sId;
		this.sName = sName;
	}

	public Seat(String sName) {
		super();
		this.sName = sName;
	}

	@Override
	public String toString() {
		return "Seat [sId=" + sId + ", sName=" + sName +"]";
	}

}
