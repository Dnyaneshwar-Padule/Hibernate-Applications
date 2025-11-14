package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@EmbeddedId
	private EmployeeId employeeId;

	@Column(name = "name")
	private String name;

	public EmployeeId getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(EmployeeId employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
