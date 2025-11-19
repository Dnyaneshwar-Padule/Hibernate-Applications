package com.tca.service;

import java.util.List;

import com.tca.entity.Student;
import com.tca.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public Integer addStudent(Student s) {
		s.setName( s.getName().toLowerCase() );
		s.setCity( s.getCity().toLowerCase() );
		return StudentDaoFactory.getStudentDaoInstance().saveStudent(s);
	}

	@Override
	public Student getStudent(Integer rno) {
		return StudentDaoFactory.getStudentDaoInstance().fetchStudent(rno);
	}

	@Override
	public List<Student> getAllStudents() {
		return StudentDaoFactory.getStudentDaoInstance().fetchAllStudents();
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		return StudentDaoFactory.getStudentDaoInstance().fetchStudentsByName(name.toLowerCase().trim());
	}

	@Override
	public List<Student> getStudentsByCity(String city) {
		return StudentDaoFactory.getStudentDaoInstance().fetchstudentsByCity(city.trim());
	}

}
