package com.tca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.tca.dao.StudentDao;
import com.tca.entities.Student;


public class AddStudent {

	public static void main(String[] args) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
		
			int choice = 0, count = 0;
			
			while(true) {
				
				try {
					System.out.print("\nEnter '1' to add new record : ");
					choice = Integer.parseInt(br.readLine());
				}
				catch (NumberFormatException e) {
					break;
				}
				
				if(choice != 1)
					break;
				
				Student student = acceptStudent(br);
				
				if(student == null)
					continue;
				
				try {
					if(StudentDao.save(student)) {
						count++;
						System.out.println("Record is saved successfully for roll no. " + student.getRno());
						System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					}
					else {
						System.out.println("Failed to save record for roll no. " + student.getRno());						
						System.out.println("-----------------------------------------------------------------");
					}
				}
				catch(SQLException se) {
					System.out.println("Failed to save record for roll no. " + student.getRno());						
					System.out.println("-----------------------------------------------------------------");					
				}
			}
			
			System.out.println(count + " record(s) saved !");
		
		}
		catch(IOException ie) {
			System.out.println("Failed to detect input device !");
		}

	}
	
	private static Student acceptStudent(BufferedReader br) throws IOException{
		Student student = new Student();
		
		try {
			System.out.print("Enter the  roll no.  : ");
			student.setRno( Integer.parseInt(br.readLine()) );	
		}
		catch (NumberFormatException ne) {
			System.out.println(ne.getMessage() + " : Please enter valid data !");
			System.out.println("*******************************************************");
			return null;
		}
		
		System.out.print("Enter   the    name  : ");
		student.setName(br.readLine());
	
		try {
			System.out.print("Enter the percentage : ");
			student.setPer( Double.parseDouble(br.readLine()) );
		}
		catch (NumberFormatException ne) {
			System.out.println(ne.getMessage() + " : Please enter valid data !");
			System.out.println("*******************************************************");
			return null;
		}
		
		return student;
	}

}
