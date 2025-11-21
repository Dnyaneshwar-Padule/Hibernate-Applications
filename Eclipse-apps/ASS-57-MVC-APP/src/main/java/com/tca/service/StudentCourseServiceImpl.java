package com.tca.service;

import java.util.List;

import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.entity.StudentCourse;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.factory.CourseServiceFactory;
import com.tca.factory.StudentCourseDaoFactory;
import com.tca.factory.StudentServiceFactory;

public class StudentCourseServiceImpl implements StudentCourseService {

	@Override
	public Boolean addRegistration(Integer rno, Integer cno) throws AppException, DatabaseException {
		try {
			Student student  = StudentServiceFactory.getStudentServiceInstance().getStudent(rno);
			if(student == null) {
				System.out.println("Student with roll number " + rno + " does not exists !");
				return false;
			}
			
			Course course = CourseServiceFactory.getCourseServiceInstance().getCourse(cno);
			if(course == null) {
				System.out.println("Course with course number " + cno + " does not exists !");
				return false;
			}
			
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
	public List<StudentCourse> getRegistrationsByCourse(Integer cno) throws AppException {
		try {
			Course course = CourseServiceFactory.getCourseServiceInstance().getCourse(cno);
			if(course == null) {
				System.out.println("Course with course number " + cno + " does not exists !");
				return null;
			}
			return  course.getRegistrations();
		}
		catch(Exception e) {
			throw new AppException("Unkown exception :- " + e.getMessage(), e);
		}
	}

	@Override
	public List<StudentCourse> getRegistrationsByStudent(Integer rno) throws AppException {
		try {
			Student student  = StudentServiceFactory.getStudentServiceInstance().getStudent(rno);
			if(student == null) {
				System.out.println("Student with roll number " + rno + " does not exists !");
				return null;
			}
			return student.getRegistrations();
		}
		catch(Exception e) {
			throw new AppException("Unkown exception :- " + e.getMessage(), e);
		}
	}

}
