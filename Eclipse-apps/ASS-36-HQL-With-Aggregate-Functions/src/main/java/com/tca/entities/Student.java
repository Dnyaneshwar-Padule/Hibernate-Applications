package com.tca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student, an entity class
 */
@Entity
@Table(name="tca_student")
public class Student {
	
	
	// To store roll number of the student
	@Id //PRIMARK KEY
	@Column(name = "tca_rno")
	private Integer rno;

	/*
	 * max character length should be 50
	 * It should not be null
	 * table column name = "tca_name" 
	 */
	 //To store name of the student
	@Column(name="tca_name" , length = 50, nullable = false)
	private String name;
	
	/*
	 * Per should not be negative and should be smaller than or equal to 100
	 * Some databases may not support Double so, we have to define the type first 
	 * table column name = "tca_per
	 */
	// To store percentage of the student
	@Column(name="tca_per", columnDefinition = "FLOAT CHECK (tca_per >= 0 and tca_per <= 100)")
	private Double per;

	
	/*
	 * Max character length should be 32
	 * Email should not be null
	 * Email should be unique
	 * table column name = "tca_email"
	 */
	//To Store the emailId of the student
	@Column(name="tca_email", length = 32, nullable = false, unique = true)
	private String email;

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + ", email=" + email + "]";
	}
	
		
}
