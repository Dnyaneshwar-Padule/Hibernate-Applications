package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

/**
 * This is an hibernate application demonstrates the use of session.get() method
 */
public class App {
    
	/**
	 * main method, entry point of the application 
	 * 
	 * @param args an String array
	 */
	public static void main(String[] args) {

		Configuration configuration = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		
		try {
			
			//load configuration details and metadata from hibernate.cfg.xml file
			configuration = new Configuration();
			configuration.configure();  // By default the data is loaded by parsing hibernate.cfg.xml file
			
			//build sessionFactory
			sessionFactory = configuration.buildSessionFactory();
			
			//build session, to perform database operations
			session = sessionFactory.openSession();
			
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
			if(sessionFactory != null) sessionFactory.close();
		}
	
    }
}
