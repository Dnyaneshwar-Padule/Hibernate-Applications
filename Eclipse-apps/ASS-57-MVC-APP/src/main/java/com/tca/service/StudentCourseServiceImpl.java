package com.tca.service;

import java.util.List;

import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.entity.StudentCourse;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;
import com.tca.factory.CourseServiceFactory;
import com.tca.factory.StudentCourseDaoFactory;
import com.tca.factory.StudentServiceFactory;

public class StudentCourseServiceImpl implements StudentCourseService {

	@Override
	public Boolean addRegistration(Integer rno, Integer cno) throws NotFoundException,AppException, DatabaseException {
		
		try {
			
			Student student  = StudentServiceFactory.getStudentServiceInstance().getStudent(rno);
			
			if(student == null)
				throw new NotFoundException("Student with roll number " + rno + " does not exists !");
			
			Course course = CourseServiceFactory.getCourseServiceInstance().getCourse(cno);
			
			if(course == null) 
				throw new NotFoundException("Course with course number " + cno + " does not exists !");
			
			return StudentCourseDaoFactory.getStudentCourseDaoInstance().saveRegistration(student, course);
		}
		catch(DatabaseException de) {
			throw de;
		}
		catch(Exception e) {
			throw new AppException("Unkown exception :- " + e.getMessage(), e);
		}
	}


	@Override
	public List<StudentCourse> getRegistrationsByCourse(Integer cno) throws AppException, NotFoundException, DatabaseException {
		return StudentCourseDaoFactory.getStudentCourseDaoInstance().fetchRegistrationsByCourse(cno);
	}

	@Override
	public List<StudentCourse> getRegistrationsByStudent(Integer rno) throws AppException , NotFoundException, DatabaseException{
		return StudentCourseDaoFactory.getStudentCourseDaoInstance().fetchRegistrationsByStudent(rno);
	}
}
