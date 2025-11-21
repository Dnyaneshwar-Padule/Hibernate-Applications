package com.tca.dao;

import java.util.List;

import com.tca.entity.Student;
import com.tca.exception.DatabaseException;

public interface StudentDao {

	public abstract Integer saveStudent(Student s) throws DatabaseException;
	
	public abstract Student fetchStudent(Integer rno) throws DatabaseException;
	
	public abstract List<Student> fetchAllStudents() throws DatabaseException;
	
	public abstract List<Student> fetchStudentsByName(String name) throws DatabaseException;
	
	public abstract List<Student> fetchstudentsByCity(String city) throws DatabaseException;
	
	public abstract Boolean updateStudent(Student student) throws DatabaseException;
	
	public abstract Boolean deleteStudent(Student student) throws DatabaseException; 
}
