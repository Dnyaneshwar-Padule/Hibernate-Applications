package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Student, an entity class
 */
@Entity
@Table(name="student")
public class Student {
	
	/*
	 * Fields
	 */
	
	// To store roll number of the student
	/*
	 * In GenerationType.AUTO
	 * 	if it's MySQL, hibernate uses table strategy
	 * 	if it's PostgreSQL hibernate uses Sequence
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="rno")
	private Integer rno;

	 //To store name of the student
	@Column(name="name", nullable = false)
	private String name;
	
	// To store percentage of the student
	@Column(name="per", columnDefinition = "FLOAT CHECK (per >= 0 and per <= 100)")
	private Double per;


	// setters and getters
	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPer() {
		return per;
	}

	public void setPer(Double per) {
		this.per = per;
	}

	//toString() method
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + "]";
	}
		
}
