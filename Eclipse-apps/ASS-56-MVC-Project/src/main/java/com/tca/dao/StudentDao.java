package com.tca.dao;

import java.util.List;

import com.tca.entity.Student;

public interface StudentDao {

	public abstract Integer saveStudent(Student s);
	
	public abstract Student fetchStudent(Integer rno);
	
	public abstract List<Student> fetchAllStudents();
	
	public abstract List<Student> fetchStudentsByName(String name);
	
	public abstract List<Student> fetchstudentsByCity(String city);
	
	public abstract Boolean updateStudent(Student student);
	
	public abstract Boolean deleteStudent(Student student); 
}
