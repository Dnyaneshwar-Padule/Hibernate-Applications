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
	 * Here I have used GenerationType.IDENTITY as primary key generation algorithm with MySQL database
	 * 
	 * In this algorithm, hibernated does not create any extra table (like there is in AUTO and SEQUENCE)
	 * instead it delegates primary key generation fully to the database column definition.
	 * 
	 * Table created by Hibernate : create table student (per FLOAT CHECK (per >= 0 and per <= 100), rno integer not null auto_increment, name varchar(255) not null, primary key (rno))
	 * 
	 * we can see, rno integer not null auto_increment, hibernated used auto increment which is used for SEQUENCIAL KEY generation in MySQL, 
	 * MySQL itself creates the Primark Key, hibernated just insert the rows without specifying the primary key
	 * 
	 * so, we can say that in IDENTITY, hibernated used database specific key generation algorithm
	 * and relies on it (on database), for key generation
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
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
