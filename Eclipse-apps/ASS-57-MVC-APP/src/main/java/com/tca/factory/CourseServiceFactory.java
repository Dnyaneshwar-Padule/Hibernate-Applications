package com.tca.factory;

import com.tca.service.CourseService;
import com.tca.service.CourseServiceImpl;

public class CourseServiceFactory {
	private static CourseService courseService = null;

	private CourseServiceFactory() {}
	
	public static CourseService getCourseServiceInstance() {
		if(courseService == null) courseService = new CourseServiceImpl();
		return courseService;
	}
}
