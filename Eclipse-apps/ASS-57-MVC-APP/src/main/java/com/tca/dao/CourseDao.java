package com.tca.dao;

import java.util.List;

import com.tca.entity.Course;
import com.tca.exception.DatabaseException;

public interface CourseDao {

	public abstract Integer saveCourse(Course c) throws DatabaseException;

	public abstract Course fetchCourse(Integer cno) throws DatabaseException;
	
	public abstract List<Course> fetchAllCourses() throws DatabaseException;
	
	public abstract List<Course> fetchCourseByName(String courseName) throws DatabaseException;
	
	public abstract Boolean updateCourse(Course course) throws DatabaseException;
	
	public abstract Boolean deleteCourse(Course course) throws DatabaseException;
}
