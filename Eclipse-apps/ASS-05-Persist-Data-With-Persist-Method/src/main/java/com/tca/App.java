package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

/*
 * Persist (Store) data with persist()
 * 
 * persist() method does not return anything, so we know data is persisted only when transaction is commited (transaction.commit())
 * if the data is not persisted hibernate throws exception while doing transaction.commit()
 * 
 * It persists data reliably, but it's better to use save() method as it also gives primary key of inserted record
 * but it depends on needs of application.....
 */

/*
 * Class describing use of persist() method
 */
public class App {
    
	/**
	 * main() method, entry point function
	 * @param args, an array of String
	 */
	public static void main(String[] args) {
        
		Configuration configuration = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction  = null;
		
		try {
			
			//Load configuration details from hibernate.cfg.xml file
			configuration = new Configuration();
			configuration.configure();  // by default, configuration details are loaded by parsing hibernate.cfg.xml file
			
			//build session factory to create session objects, and form connection with the help of configuration details
			sessionFactory = configuration.buildSessionFactory();
			
			//we perform database operation with session objects
			session = sessionFactory.openSession();
			
			//transaction object is required, when we perform DML operations
			// like, INSERT (save(), persist()), UPDATE, DELETE
			transaction = session.beginTransaction();
			
			//Student object to persist in database table 
			Student student = new Student();
			student.setRno(26);
			student.setName("Pavan");
			student.setPer(78.86);
			
			//persist student object
			session.persist(student); 
			
			//Commit Transaction, to reflect changes on the table
			transaction.commit();
			
			
			System.out.println("Data is stored successfully !");
			
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
			if(sessionFactory != null) sessionFactory.close();
		}
		
    }
}
