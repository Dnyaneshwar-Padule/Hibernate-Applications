package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.tca.entity.Student;
import com.tca.factory.StudentServiceFactory;
import com.tca.service.StudentService;
import com.tca.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
       	
    	optionSaveStudent();
    	
    	HibernateUtil.closeSessionFactory();
    	System.out.println("Done...");
    	
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
