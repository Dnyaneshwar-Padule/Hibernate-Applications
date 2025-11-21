package com.tca.service;

import java.util.List;

import com.tca.entity.Course;

public interface CourseService {

	public abstract Integer addCourse(Course c);

	public abstract Course getCourse(Integer cno);
	
	public abstract List<Course> getAllCourses();
	
	public abstract List<Course> getCourseByName(String courseName);
	
	public abstract Boolean updateCourse(Integer cno);
	
	public abstract Boolean deleteCourse(Integer cno);
	
}
