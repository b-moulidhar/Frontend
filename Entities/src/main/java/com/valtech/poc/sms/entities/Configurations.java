package com.valtech.poc.sms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Configurations {

	@Id
	@Column(name = "cId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String name;
	private String stringInfo;
	private int value;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStringInfo() {
		return stringInfo;
	}

	public void setStringInfo(String stringInfo) {
		this.stringInfo = stringInfo;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Configurations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Configurations(int cId, String name, String stringInfo, int value) {
		super();
		this.cId = cId;
		this.name = name;
		this.stringInfo = stringInfo;
		this.value = value;
	}

	public Configurations(String name, String stringInfo, int value) {
		super();
		this.name = name;
		this.stringInfo = stringInfo;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Configurations [cId=" + cId + ", name=" + name + ", stringInfo=" + stringInfo + ", value=" + value
				+ "]";
	}

}
