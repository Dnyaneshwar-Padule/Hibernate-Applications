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
			
			//we are doing a DML operation, so we also need a transaction object
			// in case, any error introduced while firing insert query, we can safely rollback
			transaction = session.beginTransaction();
			
			// Student Object to store in the database
			Student student = new Student(); 
			student.setName("Athrva");
			student.setPer(91.40); 
						
			session.save(student);
			
			transaction.commit();
		
			System.out.println("Record is saved successfuly with uuid : " + student.getUuid());
			
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
