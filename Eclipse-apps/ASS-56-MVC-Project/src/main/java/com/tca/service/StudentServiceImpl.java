package com.tca.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.tca.entity.Student;
import com.tca.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public Integer addStudent(Student s) {
		s.setName( s.getName().toLowerCase() );
		s.setCity( s.getCity().toLowerCase() );
		return StudentDaoFactory.getStudentDaoInstance().saveStudent(s);
	}

	@Override
	public Student getStudent(Integer rno) {
		return StudentDaoFactory.getStudentDaoInstance().fetchStudent(rno);
	}

	@Override
	public List<Student> getAllStudents() {
		return StudentDaoFactory.getStudentDaoInstance().fetchAllStudents();
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		return StudentDaoFactory.getStudentDaoInstance().fetchStudentsByName(name.toLowerCase().trim());
	}

	@Override
	public List<Student> getStudentsByCity(String city) {
		return StudentDaoFactory.getStudentDaoInstance().fetchstudentsByCity(city.trim());
	}

	@Override
	public Boolean updateStudent(Integer rno) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Boolean result = false;
		try {
			Student student = getStudent(rno);
			if(student == null) {
				System.out.println("Student with roll number " + rno + " does not exists !");
				return result;
			}
			
			String newName = null;
			Double newPer = null;
			String newCity = null;
			
			while(true) {
				System.out.print("[Old Name: " + student.getName()+ "] Enter new name :");
				newName = br.readLine();
				
				if(newName.isBlank()) 
					System.out.println("Name can not be blank !");
				else 
					break;
			}
				
			while(true) {
				try {
					System.out.print("[Old Percentage: " + student.getPer() + "] Enter new percentage : ");
					newPer = Double.parseDouble(br.readLine());
					break;
				}
				catch(NumberFormatException ne) {
					System.out.println("Please enter valid percentage !");
				}
			}
				
			while(true) {
				System.out.print("[Old City: " + student.getCity()+ "] Enter new city :");
				newCity = br.readLine();
				
				if(newCity.isBlank()) 
					System.out.println("City can not be blank !");
				else 
					break;
			}

			student.setName(newName.toLowerCase());
			student.setCity(newCity.toLowerCase());
			student.setPer(newPer);
			return StudentDaoFactory.getStudentDaoInstance().updateStudent(student);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Boolean deleteStudent(Integer rno) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Boolean result = false;
		try {
			Student student = getStudent(rno);
			if(student == null) {
				System.out.println("Student with roll number " + rno + " does not exists !");
				return result;
			}
			
			System.out.println("** Student Data **");
			System.out.println("Student    name    : " + student.getName());
			System.out.println("Student    city    : " + student.getCity());
			System.out.println("Student percentage : " + student.getPer());
			
			String choice = null;
			while(true) {
				System.out.print("Do you want to delete this record ? [Y] : ");
				choice = br.readLine();
				
				if( choice.isBlank() ) 
					System.out.println("Please enter valid choice !");
				else
					break;
			}
			
			if (Character.toLowerCase( choice.trim().charAt(0) ) == 'y')
				result =  StudentDaoFactory.getStudentDaoInstance().deleteStudent(student);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
