package com.tca.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentCourse {

	@Id
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FK_rno", nullable = false)
	private Student student;
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FK_cno", nullable = false)
	private Course course;
	
	private LocalDate regDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	
}
