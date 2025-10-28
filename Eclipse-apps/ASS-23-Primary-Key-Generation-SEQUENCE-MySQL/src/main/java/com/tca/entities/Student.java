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
	 * Here I have used GenerationType.SEQUENCE as primary key generation strategy/algorithm with MySQL database
	 * 
	 * MySQL does not have SEQUENCE, so just like AUTO, here hibernate also uses a table to generate primary key
	 * Hibernate : create table student_SEQ (next_val bigint)
	 * 
	 * Here the allocation size is also 50, 
	 * hibernate gets key from student_SEQ table and increase it by 50
	 * and generated 50 values by himself
	 * 
	 * for example, 
	 * suppose, now there is 1 in student_SEQ, so it fetches 1,
	 * and update the student_SEQ.next_val by 50, so there will be 51 in the next_val
	 * 
	 * and hibernate itself generated values between 1 to 51 for us, to use as primark keys
	 * by doing this it increases the performance
	 * 
	 * 
	 * if we want to change the allocation size (50 is default),
	 * we have to use a sequence generator
	 * like this 
		@SequenceGenerator(name="std_seq", sequenceName = "student_SEQ", allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "std_seq") 
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) 
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
