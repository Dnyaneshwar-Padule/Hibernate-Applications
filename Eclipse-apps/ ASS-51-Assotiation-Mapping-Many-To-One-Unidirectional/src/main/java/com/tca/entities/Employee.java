package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="emp")
public class Employee {

	@Id
	private Integer eid;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name = "salary", nullable = false, columnDefinition = "FLOAT CHECK(salary >= 0)")
	private Double salary;


	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
}
