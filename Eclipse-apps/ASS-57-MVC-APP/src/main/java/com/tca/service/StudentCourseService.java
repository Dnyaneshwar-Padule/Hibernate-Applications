package com.tca.service;

import java.util.List;

import com.tca.entity.StudentCourse;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;

public interface StudentCourseService {

	public abstract Boolean addRegistration(Integer rno, Integer cno) throws AppException, DatabaseException, NotFoundException;
	
	public abstract List<StudentCourse> getRegistrationsByCourse(Integer cid) throws AppException, NotFoundException, DatabaseException;
	
	public abstract List<StudentCourse> getRegistrationsByStudent(Integer rno) throws AppException, NotFoundException, DatabaseException;
	
}
