package com.tca.entities;

/**
 * An entity class to hold Teacher records
 */
public class Teacher {

	//To hold unique teacher ID
	private Integer tno;
	
	//To hold teacher name
	private String name;
	
	//To hold teacher salary
	private Double salary;

	/*
	 * getters and setters
	 */
	
	/**
	 * gives the unique teacher ID (identifier)
	 * @return tno Integer
	 */
	public Integer getTno() {
		return tno;
	}

	/**
	 * sets the teacher ID
	 * @param tno Integer
	 */
	public void setTno(Integer tno) {
		this.tno = tno;
	}

	/**
	 * gives name of the teacher
	 * @return name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the teacher
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gives the salary of the teacher
	 * @return salary Double
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * sets the salary of the teacher
	 * @param salary Double
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Teacher [tno=" + tno + ", name=" + name + ", salary=" + salary + "]";
	}
}
