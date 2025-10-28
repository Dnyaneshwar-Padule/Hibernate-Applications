package com.tca;

import org.hibernate.Session;

import com.tca.entities.Student;
import com.tca.util.HibernateUtil;

/**
 * This is an hibernate application demonstrates how to load configurations from properties file
 * 
 * I have created a hibernate.propertie file in main/resources folder (if you are seeing on github, you won't see it)
 * there I have written all Database properties and hibernate properties
 * like this, 
 * 			 hibernate.connection.driver_class = org.postgresql.Driver
 * 			 hibernate.show_sql = true
 */
public class App {
    
	/**
	 * main method, entry point of the application 
	 * 
	 * @param args an String array
	 */
	public static void main(String[] args) {

		Session session = null;
		
		try {
			
			//build session, to perform database operations
			session = HibernateUtil.getSessionFactory().openSession();
			
			//rno is the roll number of the student 
			//It is the primary key in database table (student table)
			//So we will get the student record through rno (roll number)
			int rno = 27;
			
			//get method works for all types of entity classes
			//so it does not have a fixed return type
			// So we need to cast the returned object to Student class
			Student student = (Student) session.get(Student.class, rno ); // we have also specified the class name, so that hibernate could know in which table to search the record
			
			if(student != null) {
				System.out.println("Student record is found successfully !");
				System.out.println("Student record : " + student.toString());
			}
			else {
				System.out.println("No record is found with roll number : " + rno);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
			HibernateUtil.shutdown();
		}
	
    }
}
