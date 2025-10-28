package com.tca.entities;

/**
 * An entity class to hold student record
 */
public class Student {

	//to hold roll number of student
	private Integer rno;
	
	//To hold name of a student
	private String name;
	
	//To hold percentage of student
	private Double per;

	
	//setters and getters

	/**
	 * gives the roll number of student
	 * @return rno Integer
	 */
	public Integer getRno() {
		return rno;
	}

	/**
	 * sets the roll number of student
	 * @param rno Integer
	 */
	public void setRno(Integer rno) {
		this.rno = rno;
	}

	/**
	 * gives the name of student
	 * @return name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of student
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gives the percentage of student
	 * @return per Double
	 */
	public Double getPer() {
		return per;
	}

	/**
	 * sets the percentage of student
	 * @param per Doubles
	 */
	public void setPer(Double per) {
		this.per = per;
	}

	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + "]";
	}
	
}
