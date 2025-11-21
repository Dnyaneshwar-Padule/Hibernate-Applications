package com.tca.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.tca.entity.Course;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;
import com.tca.factory.CourseDaoFactory;

public class CourseServiceImpl implements CourseService {

	@Override
	public Integer addCourse(Course c) throws DatabaseException {
		c.setName( c.getName().toLowerCase() );
		return CourseDaoFactory.getCourseDaoInstance().saveCourse(c);
	}

	@Override
	public Course getCourse(Integer cno) throws DatabaseException {
		return CourseDaoFactory.getCourseDaoInstance().fetchCourse(cno);
	}

	@Override
	public List<Course> getAllCourses() throws DatabaseException {
		return CourseDaoFactory.getCourseDaoInstance().fetchAllCourses();
	}

	@Override
	public List<Course> getCourseByName(String courseName) throws DatabaseException {
		return CourseDaoFactory.getCourseDaoInstance().fetchCourseByName(courseName.toLowerCase());
	}

	@Override
	public Boolean updateCourse(Integer cno) throws NotFoundException,DatabaseException, AppException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		try {
			Course c = getCourse(cno);
			
			if( cno == null) {
				throw new NotFoundException("Course does not exist with course number: " + cno);
			}
		
			String newCourseName = null;
			while(true) {
				System.out.print("[Old course name: " + c.getName() + "] Enter new course name : ");
				newCourseName = br.readLine();
				
				if(newCourseName.isBlank()) 
					System.out.println("Course name can not be blank !");
				else 
					break;
			}
			
			c.setName(newCourseName.toLowerCase());
			return CourseDaoFactory.getCourseDaoInstance().updateCourse(c);
		}
		catch(DatabaseException de) {
			throw de;
		}
		catch(IOException ie) {
			throw new AppException("Input Stream closed", ie);
		}
		catch(Exception e) {
			throw new AppException("Unkown exception :- " + e.getMessage(), e);
		}
	}

	@Override
	public Boolean deleteCourse(Integer cno) throws DatabaseException, AppException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		try {
			Course c = getCourse(cno);
			
			if( cno == null) {
				throw new NotFoundException("Course does not exist with course number: " + cno);
			}
		
			System.out.println("** Course details **");
			System.out.println("Course Number : " + cno);
			System.out.println("Course  Name  : " + c.getName());
			
			String choice;
			while(true) {
				System.out.print("Do you want to delete this course ? [Y] : ");				
				choice = br.readLine();
				
				if(choice.isBlank()) {
					System.out.println("Please enter valid choice !");
				}
				else {
					break;
				}
			}
		
			if(Character.toLowerCase( choice.charAt(0) ) == 'y')
				return CourseDaoFactory.getCourseDaoInstance().deleteCourse(c);
			return false;
		}
		catch(DatabaseException de) {
			throw de;
		}
		catch(IOException ie) {
			throw new AppException("Input Stream closed", ie);
		}
		catch(Exception e) {
			throw new AppException("Unkown exception :- " + e.getMessage(), e);
		}
		
	}

}
