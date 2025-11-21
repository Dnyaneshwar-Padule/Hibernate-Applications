package com.tca.service;

import java.util.List;

import com.tca.entity.StudentCourse;

public interface StudentCourseService {

	public abstract Boolean addRegistration(Integer rno, Integer cno);
	
	public abstract List<StudentCourse> getRegistrationsByCourse(Integer cid);
	
	public abstract List<StudentCourse> getRegistrationsByStudent(Integer rno);
	
}
