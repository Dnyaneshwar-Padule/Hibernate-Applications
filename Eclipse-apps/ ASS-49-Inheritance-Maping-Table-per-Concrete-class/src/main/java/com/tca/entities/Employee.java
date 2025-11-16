package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="employee_2")
public class Employee extends Person {

	@Column(name="salary", columnDefinition = "Float")
	private Double Salary;

	public Double getSalary() {
		return Salary;
	}

	public void setSalary(Double salary) {
		Salary = salary;
	}
	
}
