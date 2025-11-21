package com.tca.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.tca.entity.Course;
import com.tca.factory.CourseDaoFactory;

public class CourseServiceImpl implements CourseService {

	@Override
	public Integer addCourse(Course c) {
		c.setName( c.getName().toLowerCase() );
		return CourseDaoFactory.getCourseDaoInstance().saveCourse(c);
	}

	@Override
	public Course getCourse(Integer cno) {
		return CourseDaoFactory.getCourseDaoInstance().fetchCourse(cno);
	}

	@Override
	public List<Course> getAllCourses() {
		return CourseDaoFactory.getCourseDaoInstance().fetchAllCourses();
	}

	@Override
	public List<Course> getCourseByName(String courseName) {
		return CourseDaoFactory.getCourseDaoInstance().fetchCourseByName(courseName.toLowerCase());
	}

	@Override
	public Boolean updateCourse(Integer cno) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		try {
			Course c = getCourse(cno);
			
			if( cno == null) {
				System.out.println("Course does not exist with course number: " + cno);
				return false;
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
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleteCourse(Integer cno) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		try {
			Course c = getCourse(cno);
			
			if( cno == null) {
				System.out.println("Course does not exist with course number: " + cno);
				return false;
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
		catch(Exception e) {
			return false;
		}
		
	}

}
