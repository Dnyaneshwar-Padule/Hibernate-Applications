package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.tca.config.DatabaseConfigurations;

public class AddRecord {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

			//Register Driver
			Class.forName(DatabaseConfigurations.DB_DRIVER);
			
			//Form Connection 
			conn = DriverManager.getConnection(DatabaseConfigurations.DB_URL, DatabaseConfigurations.DB_USER, DatabaseConfigurations.DB_PWD);
			
			//prepare SQL
			ps = conn.prepareStatement("INSERT INTO STUDENTS VALUES(?,?,?)");
			
			int choice; // choice of user
			
			while(true) {
				
				int rno; // to store roll number
				String name; //to store name
				float per;  // to store percentage
				
				System.out.print("\nEnter '1' to add new record : ");
				
				try {
					choice = Integer.parseInt(br.readLine());
				}catch (Exception e) { // If choice is not a valid integer
					break;
				}
				
				//end loop, if user don't want to insert new record
				if(choice != 1) {
					break;
				}
				
				// Take data
				try {
					
					System.out.print("Enter the roll number : ");
					rno = Integer.parseInt(br.readLine());
					
					System.out.print("Enter    the     name : ");
					name = br.readLine();
					
					System.out.print("Enter  the percentage : ");
					per = Float.parseFloat(br.readLine());
				}catch(Exception e) { 
					//In case, if parsing failed !
					System.out.println(e.getMessage() + " : Please enter valid data !");
					System.out.println("---------------------------------------------------");
					continue;
				}
				
				//Map values to the query
				ps.setInt(1, rno);
				ps.setString(2, name);
				ps.setFloat(3, per);
				
				
				//Fire Query, and check the status 
				try {
					
					if( ps.executeUpdate() == 1) {
						System.out.println("Record is added successfully !");
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");						
					}
					else {
						System.out.println("Unable to add record, please try again !");						
						System.out.println("---------------------------------------------------");
					}
					
				}catch(Exception e) {
					System.out.println(e.getMessage() + " : Unable to add record, please try again !");
					System.out.println("---------------------------------------------------");
				}
			}
			
			System.out.println("Exiting.....");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				conn.close();
				System.out.println("Connection closed !");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
}



/*
  	CREATE TABLE STUDENTS(
 		rno int PRIMARY KEY,
 		name varchar(32) NOT NULL,
 		per float
  	);
  
 */
