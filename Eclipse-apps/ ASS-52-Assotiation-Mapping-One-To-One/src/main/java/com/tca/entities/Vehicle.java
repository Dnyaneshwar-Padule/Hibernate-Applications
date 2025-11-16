package com.tca.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="vehicle")
public class Vehicle {
	
	@Id
	@Column(length = 10)
	private String cno;
	
	@Column(name="name", nullable = false)
	private String name;

	// every engine ID is unique
	// and every car has a engine, so it should not be null
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FK_EID", unique = true, nullable = false) 
	private Engine engine;

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
}
