package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Student, an entity class
 */
@Entity
@Table(name="student")
public class Student {

	
	/*
	 * This entity uses two fields (firstName, lastName) as composite key
	 * 
	 * we have added both fields in a class, hibernate serialize it, and take one byte stream of that class's object
	 */
	
	@EmbeddedId
	private StudentCompositeKey name;
	
	@Column(name="city", nullable = false, length = 32)
	private String city;

	public StudentCompositeKey getName() {
		return name;
	}

	public void setName(StudentCompositeKey name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
		
}
