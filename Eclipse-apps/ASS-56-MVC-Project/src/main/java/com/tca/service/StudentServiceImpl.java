package com.tca.service;

import com.tca.entity.Student;
import com.tca.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public Integer addStudent(Student s) {
		return StudentDaoFactory.getStudentDaoInstance().saveStudent(s);
	}

}
