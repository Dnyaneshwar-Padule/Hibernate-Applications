package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
@NamedQuery(name="Student_getByRno", query = "From Student where rno = :rollNumber")
@NamedQuery(name="Student_getByCity", query = "From Student where city LIKE :studentCity")
@NamedQuery(name="update_name_by_rno", query = "UPDATE Student SET name = :studentName WHERE rno = :rollNumber")

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