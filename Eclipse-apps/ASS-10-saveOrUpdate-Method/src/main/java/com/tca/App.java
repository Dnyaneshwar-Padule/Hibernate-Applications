package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;

/**
 * This is an hibernate application demonstrates session.saveOrUpdate() method
 * 
 * 
 */

public class App {
    
	/**
	 * main method, entry point of the application 
	 * 
	 * @param args an String array
	 */
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			//build session, to perform database operations
			session = HibernateUtil.getSessionFactory().openSession();
			
			//begin transaction
			transaction = session.beginTransaction();
			
		
			Student student = new Student();
			student.setRno(101);
			student.setName("AAA");
			student.setPer(60.0);
			
			//If record exists, update it
			// If no, insert new record
			session.saveOrUpdate(student);
			
			transaction.commit();			
	
		
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
			HibernateUtil.shutdown();
		}
	
    }
}
