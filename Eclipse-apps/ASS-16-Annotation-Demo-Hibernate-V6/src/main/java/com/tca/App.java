package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;



/**
 * First Hibernate Application
 * --> Persists (stores) data with save() method
 */
public class App {
   
	/**
	 * main method, app's entry point function 
	 * @param args, an array of String
	 */
	public static void main(String[] args) {
        
		//To load hibernate configuration details,
		Configuration configuration = null;
		
		//It is built once at startup
		//It creates session objects
		SessionFactory sessionFactory = null;
		
		//It is used to perform database operations (CRUD operations or other queries)
		//it holds database connection
		Session session = null;
		
		//It is used when we fire DML queries
		// or in short, when we do operations like save(INSERT), update, delete 
		Transaction transaction = null;
		
		try {
			
			// Load configuration details by parsing hibernate.cfg.xml file
			configuration = new Configuration();
			configuration.configure();  // by default it loads data from hibernate.cfg.xml file
			
			//If mapping class is not specified in configuration file
			//configuration.addAnnotatedClass(Student.class); 
			
			//Create sessionFactory from configuration details
			sessionFactory = configuration.buildSessionFactory();
			
			//get session object from session factory, to perform database operations
			session = sessionFactory.openSession();
			
			//we are doing a DML operation, so we also need a transaction object
			// in case, any error introduced while firing insert query, we can safely rollback
			transaction = session.beginTransaction();
			
			// Student Object to store in the database
			Student student = new Student();
			student.setRno(26); // setting roll number
			student.setName("Rahul"); // setting name
			student.setPer(89.94); // setting percentage
			
			// saving student record to the database
			
			//In hibernate 6, the save method is deprecated, we should persist() method
			//session.save(student);
			
			session.persist(student);
			
			// if there is no problem, we actually reflect changes to the database table
			transaction.commit();
			
			System.out.println("Record is saved successfuly !");
			
		}
		catch(Exception e) {
			if (transaction != null ) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			//Close database connection
			if(session != null ) session.close(); // close connection
			if(sessionFactory != null) sessionFactory.close(); // release all resources, no need of new sessions
		}
		
    }
}
