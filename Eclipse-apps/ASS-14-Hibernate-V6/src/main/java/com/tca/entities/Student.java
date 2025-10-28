package com.tca.entities;

/**
 * Student, an entity class
 */
public class Student {
	
	/*
	 * Fields
	 */
	
	// To store roll number of the student
	private Integer rno;

	 //To store name of the student
	private String name;
	
	// To store percentage of the student
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
