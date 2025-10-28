package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.util.HibernateUtil;

public class App {
   
	/**
	 * main method, app's entry point function 
	 * @param args, an array of String
	 */
	public static void main(String[] args) {
        
		Session session = null;
		
		//It is used when we fire DML queries
		// or in short, when we do operations like save(INSERT), update, delete 
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			// Student Object to store in the database
			Student student = new Student(); 
			student.setName("Athrva");
			student.setPer(91.40); 
						
			/*
			 * When we use GenerationType.AUTO as GenerationStrategy with MySQL database
			 * the hibernate automatically chooses the Primary Key generation strategy based on dialect class
			 * 
			 * Here hibernate used the table strategy as AUTO, because MySQL does not have SEQUENCE (as there in PostgreSQL)
			 *  create table student_SEQ (next_val bigint) engine=InnoDB
				insert into student_SEQ values ( 1 )
			 * 
			 * 
			 */
			
			session.persist(student);
			int rollNumber = (int)session.getIdentifier(student);
			transaction.commit();
			
			System.out.println("Record is saved successfuly with roll number : " + rollNumber);
			
		}
		catch(Exception e) {
			if (transaction != null ) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			//Close database connection
			if(session != null ) session.close(); // close connection
			HibernateUtil.shutdown();
		}
		
    }
}
