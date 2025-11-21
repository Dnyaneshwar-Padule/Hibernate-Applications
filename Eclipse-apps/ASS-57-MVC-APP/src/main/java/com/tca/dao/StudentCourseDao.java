package com.tca.dao;

import java.util.List;

import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.entity.StudentCourse;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;

public interface StudentCourseDao {

	public abstract Boolean saveRegistration(Student student, Course course) throws DatabaseException;

	public List<StudentCourse> fetchRegistrationsByCourse(Integer cno) throws AppException, NotFoundException, DatabaseException;

	public List<StudentCourse> fetchRegistrationsByStudent(Integer rno) throws AppException , NotFoundException, DatabaseException;
}
