package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Student, an entity class
 */
@Entity
@Table(name="student") // table name is optional, defaults to the entity name.
public class Student {
	
	
	// To store roll number of the student
	@Id
	@Column(name="rno")
	private Integer rno;

	 //To store name of the student
	@Column(name="name")
	private String name;
	
	// To store percentage of the student
	@Column(name="per")
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
