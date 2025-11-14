package com.tca.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String company;
	private Integer employeeNo;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(Integer employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	
}
