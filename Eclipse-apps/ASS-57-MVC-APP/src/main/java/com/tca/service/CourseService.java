package com.tca.service;

import java.util.List;

import com.tca.entity.Course;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;

public interface CourseService {

	public abstract Integer addCourse(Course c) throws DatabaseException;

	public abstract Course getCourse(Integer cno) throws DatabaseException;
	
	public abstract List<Course> getAllCourses() throws DatabaseException;
	
	public abstract List<Course> getCourseByName(String courseName) throws DatabaseException;
	
	public abstract Boolean updateCourse(Integer cno) throws DatabaseException, AppException, NotFoundException;
	
	public abstract Boolean deleteCourse(Integer cno) throws DatabaseException, AppException;
	
}
