package com.tca.factory;

import com.tca.dao.StudentCourseDao;
import com.tca.dao.StudentCourseDaoImpl;

public class StudentCourseDaoFactory {

	private static StudentCourseDao studentCourseDao = null;
	
	private StudentCourseDaoFactory() {}
	
	public static StudentCourseDao getStudentCourseDaoInstance() {
		if( studentCourseDao == null ) studentCourseDao = new StudentCourseDaoImpl();
		return studentCourseDao;
	}
}
