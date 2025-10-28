package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import com.tca.entities.Student;

/**
 * This is an hibernate application demonstrates the use of session.load() method
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
			
			//roll number, primary key
			int rno = 27;
			
			
			/*
			 * Load method also gives a record from database but it does not work like get method
			 * Get method retrieves the record on calling it, it uses early loading
			 * 
			 * Load method created proxy object on calling it, the object contains primary key only
			 * when we try to get values from that object, at that time hibernate retrieves data from database
			 * load method uses lazy loading
			 */ 
			
			// At this time, there is just a proxy student object, with roll number
			Student student = (Student) session.load(Student.class, rno);
			
			System.out.println("Load method called....");

			System.out.println(student.getClass().getName());
			if (student instanceof HibernateProxy) {
			    LazyInitializer initializer = ((HibernateProxy) student).getHibernateLazyInitializer();
			    System.out.println("Is initialized? " + initializer.isUninitialized());
			    System.out.println("Entity name: " + initializer.getEntityName());
			    System.out.println("Identifier: " + initializer.getIdentifier());
			}
			
			
			//System.out.println(student.toString());
			
			///At this time, load will fire SELECT SQL, and retrieve data from database
			System.out.println("Rno  : " + student.getRno());
			System.out.println("Name : " + student.getName());
			System.out.println("Per  : " + student.getPer());
	
			System.out.println(student.getClass().getName());
			
			if (student instanceof HibernateProxy) {
			    LazyInitializer initializer = ((HibernateProxy) student).getHibernateLazyInitializer();
			    System.out.println("Is initialized? " + initializer.isUninitialized());
			    System.out.println("Entity name: " + initializer.getEntityName());
			    System.out.println("Identifier: " + initializer.getIdentifier());
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
