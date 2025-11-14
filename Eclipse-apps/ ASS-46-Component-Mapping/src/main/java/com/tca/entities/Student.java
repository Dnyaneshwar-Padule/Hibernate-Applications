package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_new")
public class Student{
	
	@Id
	@Column(name="rno")
	private Integer rno;

	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="per", columnDefinition = "FLOAT CHECK(per >= 0 and per <= 100)")
	private Double per;
	
	@Embedded
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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


	
}