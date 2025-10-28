package com.tca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student{
	
	@Id
	@Column(name="rno")
	private Integer rno;

	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="per", columnDefinition = "FLOAT CHECK(per >= 0 and per <= 100)")
	private Double per;
	
	@Column(name="city", nullable = false)
	private String city;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}