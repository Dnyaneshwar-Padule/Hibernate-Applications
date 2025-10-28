package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.entities.StudentCompositeKey;
import com.tca.util.HibernateUtil;

public class GetRecord {
   
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
		
			StudentCompositeKey studentCompositeKey = new StudentCompositeKey();
			studentCompositeKey.setFirstName("Athrva");
			studentCompositeKey.setLastName("Gheware");

			Student student = session.get(Student.class, studentCompositeKey);	
			transaction.commit();

			
			if( student != null )
				System.out.println("Rcord is found !");
			else
				System.out.println("Record not found !");
				
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
