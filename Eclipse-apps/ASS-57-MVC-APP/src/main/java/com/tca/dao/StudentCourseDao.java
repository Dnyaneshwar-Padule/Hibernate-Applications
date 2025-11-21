package com.tca.dao;

import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.exception.DatabaseException;

public interface StudentCourseDao {

	public abstract Boolean saveRegistration(Student student, Course course) throws DatabaseException;
}
