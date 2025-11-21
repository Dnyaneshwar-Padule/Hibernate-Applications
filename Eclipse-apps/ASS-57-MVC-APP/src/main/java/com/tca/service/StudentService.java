package com.tca.service;

import java.util.List;

import com.tca.entity.Student;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;

public interface StudentService {

	public abstract Integer addStudent(Student s) throws DatabaseException; 

	public abstract Student getStudent(Integer rno) throws DatabaseException; 
	
	public abstract List<Student> getAllStudents() throws DatabaseException;
	
	public abstract List<Student> getStudentsByName(String name) throws DatabaseException;
	
	public abstract List<Student> getStudentsByCity(String city) throws DatabaseException;
	
	public abstract Boolean updateStudent(Integer rno) throws AppException, DatabaseException;
	
	public abstract Boolean deleteStudent(Integer rno);
}
