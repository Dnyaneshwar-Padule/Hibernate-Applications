package com.tca.factory;

import com.tca.service.StudentCourseService;
import com.tca.service.StudentCourseServiceImpl;

public class StudentCourseServiceFactory {

	private static StudentCourseService studentCourseService  = null;
	
	private StudentCourseServiceFactory() {}
	
	public static StudentCourseService getStudentCourseServiceInstance() {
		if( studentCourseService == null ) studentCourseService = new StudentCourseServiceImpl();
		return studentCourseService;
	}
}
