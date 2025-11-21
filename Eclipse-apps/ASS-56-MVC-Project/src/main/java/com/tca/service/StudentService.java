package com.tca.service;

import java.util.List;

import com.tca.entity.Student;

public interface StudentService {

	public abstract Integer addStudent(Student s); 

	public abstract Student getStudent(Integer rno); 
	
	public abstract List<Student> getAllStudents();
	
	public abstract List<Student> getStudentsByName(String name);
	
	public abstract List<Student> getStudentsByCity(String city);
	
	public abstract Boolean updateStudent(Integer rno);
	
	public abstract Boolean deleteStudent(Integer rno);
}
