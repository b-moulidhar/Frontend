package com.valtech.poc.sms.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Holidays")
public class Holidays {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int hId;
	private LocalDate date;
	private String name;
	
	
	public Holidays() {
		super();
		}
	
	
	public Holidays(LocalDate date, String name) {
		super();
		this.date = date;
		this.name = name;
	}


	public Holidays(int hId, LocalDate date, String name) {
		super();
		this.hId = hId;
		this.date = date;
		this.name = name;
	}

	public int gethId() {
		return hId;
	}
	public void sethId(int hId) {
		this.hId = hId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Holidays [hId=" + hId + ", date=" + date + ", name=" + name + "]";
	}
	
	
}
