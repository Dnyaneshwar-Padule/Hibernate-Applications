package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.tca.entity.Student;
import com.tca.factory.StudentServiceFactory;
import com.tca.service.StudentService;
import com.tca.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
       	
    	//optionSaveStudent();
    	//optionGetStudentByName();
    	//optionGetAllStudents();
    	
    	optionGetStudentByCity();
    	HibernateUtil.closeSessionFactory();
    	System.out.println("Done...");
    	
    }
    
    private static void optionGetStudentByCity() {
    	try (BufferedReader br = new BufferedReader( new InputStreamReader(System.in)))
    	{
    		System.out.print("Enter name of the city : ");
    		List<Student> students = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getStudentsByCity( br.readLine() );
    		
    		if(students == null || students.size() == 0) {
    			System.out.println("No records found !");
    		}
    		else {
    			System.out.println("** Student Records **");
    			for(Student s : students) {
    				System.out.println("Student  Roll no   : " + s.getRno());
    				System.out.println("Student     Name   : " + s.getName());
    				System.out.println("Student Percentage : " + s.getPer());
    				System.out.println("Student     City   : " + s.getCity());    				
    				System.out.println("");
    			}
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching data !");
    	}
    }
    
    private static void optionGetStudentByName() {
    	try (BufferedReader br = new BufferedReader( new InputStreamReader(System.in)))
    	{
    		System.out.print("Enter name of the student : ");
    		List<Student> students = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getStudentsByName( br.readLine() );
    		
    		if(students == null || students.size() == 0) {
    			System.out.println("No records found !");
    		}
    		else {
    			System.out.println("** Student Records **");
    			for(Student s : students) {
    				System.out.println("Student  Roll no   : " + s.getRno());
    				System.out.println("Student     Name   : " + s.getName());
    				System.out.println("Student Percentage : " + s.getPer());
    				System.out.println("Student     City   : " + s.getCity());    				
    				System.out.println("");
    			}
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching data !");
    	}
    }
    
    private static void optionGetAllStudents() {
    	try {
    		List<Student> students = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getAllStudents();
    		
    		if(students == null || students.size() == 0) {
    			System.out.println("No records found !");
    		}
    		else {
    			System.out.println("** Student Records **");
    			for(Student s : students) {
    				System.out.println("Student  Roll no   : " + s.getRno());
    				System.out.println("Student     Name   : " + s.getName());
    				System.out.println("Student Percentage : " + s.getPer());
    				System.out.println("Student     City   : " + s.getCity());    				
    				System.out.println("");
    			}
    		}
    		
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching data !");
    	}
    }
    
    private static void optionSaveStudent() {    	
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
    		StudentService studentService = StudentServiceFactory.getStudentServiceInstance();
    		
    		Student s = new Student();
    		
    		System.out.print("Enter    name    :");
    		s.setName( br.readLine().trim() );
    		
    		System.out.print("Enter percentage :");
    		s.setPer(Double.parseDouble(br.readLine()));
    		
    		System.out.print("Enter    city    :");
    		s.setCity( br.readLine().trim() );

    		Integer id = studentService.addStudent(s);
    		
    		if(id == null) {
    			System.out.println("Unable to save student record !");
    		}
    		else {
    			System.out.println("Student is saved with roll number : " + id);
    		}
    		
    	}
    	catch(NumberFormatException ne) {
    		System.out.println("Enter valid percentage !");
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while saving data !");
    	}

    	
    }
}
