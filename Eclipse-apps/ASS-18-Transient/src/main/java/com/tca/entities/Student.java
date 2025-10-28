package com.tca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="student")
public class Student {

	//Primary Key
	@Id
	@Column(name="rno")
	private Integer rno;

	@Column(name="name", nullable = false) // name should not be null
	private String name;
	
	@Column(name="per", columnDefinition = "FLOAT CHECK(per >= 0 and per <= 100)")
	private Double per;
	
	@Column(name="email", nullable = false, unique = true) // email should not be null, and should be unique
	private String email;
	
	/*
	 * 
	 * grade isn't an actual column in database table
	 * we can derive grade from percentage
	 * so we have used the Transient annotation,
	 * which tells that a entity class's field isn't present in the database table as an column
	 * 
	 */
	@Transient
	private Character grade;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Character getGrade() {
		return grade;
	}
	public void setGrade(Character grade) {
		this.grade = grade;
	}
	
	
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + ", email=" + email + ", grade=" + grade
				+ "]";
	}
	
	
}
