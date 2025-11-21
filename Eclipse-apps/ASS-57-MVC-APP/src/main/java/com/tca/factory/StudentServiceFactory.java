package com.tca.factory;

import com.tca.service.StudentService;
import com.tca.service.StudentServiceImpl;

public final class StudentServiceFactory {

	private static StudentService studentService;
	
	private StudentServiceFactory() {}
	
	public static StudentService getStudentServiceInstance() {
		if(studentService == null)
			studentService = new StudentServiceImpl();
		return studentService;
	}
	
}
