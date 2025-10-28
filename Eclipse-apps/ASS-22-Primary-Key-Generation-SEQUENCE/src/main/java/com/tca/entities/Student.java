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
	 * Here I have used GenerationType.SEQUENCE as PrimaryKey Generation algorithm with PostgreSQL database
	 *
	 * Just like AUTO, hibernate here also created a SEQUENCE 
	 * 
	 * Hibernate: create sequence student_SEQ start with 1 increment by 50
	 * 
	 * Hibernate will fetch an value from the sequence, and increase the sequence by 50
	 * as It will generated the 50 values by himself and give them to us, this increases performance
	 * fetch once, increase by 50, and create 50 values by itself
	 *
	 *by default the allocationSize is 50, if we want to change the allocationSize, we have to use 
	 *a custom sequence like this
	 //@SequenceGenerator(name="std_SEQ", sequenceName = "student_SEQ", allocationSize = 1)
	 //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "std_SEQ")
	 */
	@Id
	//@SequenceGenerator(name="std_SEQ", sequenceName = "student_SEQ", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "std_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO, INDENTITY, SEQUENCE,TABLE  
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
