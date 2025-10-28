package com.tca.entities;

/**
 * This is an entity class
 */
public class Student {

	private Integer rno; // roll number of student
	private String name; // name of student
	private Double per; // percentage of student
	
	//getters and setters
	
	/**
	 * gives roll number of student
	 * @return rno Integer
	 */
	public Integer getRno() {
		return rno;
	}
	
	/**
	 * sets roll number of student
	 * @param rno Integer
	 */
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	
	/**
	 * gives name of student
	 * @return name String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets name of student
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * gives percentage of student
	 * @return per Double
	 */
	public Double getPer() {
		return per;
	}
	
	/**
	 * sets percentage of student
	 * @param per Double
	 */
	public void setPer(Double per) {
		this.per = per;
	}

	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + "]";
	}
	
	
	
}
