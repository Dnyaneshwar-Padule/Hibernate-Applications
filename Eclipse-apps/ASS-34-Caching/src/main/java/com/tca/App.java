package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;


public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			// Saving a record
			/*
			 * 
			Student student = new Student();
			student.setRno(26); 
			student.setName("Rahul");
			student.setPer(89.94);
			student.setEmail("a@gmail.com");
			
			session.persist(student);
			System.out.println("Record is saved successfuly !");
			 */
			
			// Fetching a record
			
			/*
			 * 
			
			Student s1 = session.get(Student.class, 26);
			Student s2 = session.get(Student.class, 26);
			System.out.println("Reference of s1 : " + s1.toString() + " | Reference of s2 : " + s2.toString());

			//This shows that, when we get a record from database, hibernate caches it in the particular session's cache
			// If I use different session objects for s1 and s2 the references will be different, bcaz each session will fetch data from database
			
			// hibernate caches object when we fetch them, save them and update them
			 * */

			
			/*
			 * 
			
			//Evict 
			Student s = session.get(Student.class, 26);
			System.out.println(s.getName());
			
			session.evict(s); // Detaching the student object from session cache
			
			Student std = session.get(Student.class, 26);
			System.out.println(s.getName());
			
			System.out.println(s);
			System.out.println(std);
			
			// The references will be different
			//When first time we called get method
			//hibernate fired a SQL and fetched the record and stored it's reference in respective session's cache
			// then we detached the student object (s) from the session's cache
			// and called get method again for same record
			//this time hibernate first searched the record in cache but we detached it, so it did not find it in the cache
			// So he fired SQL again, and fetched the record (with creating a new object)
			
			 */
		 
			
			// Merge
			Student s = new Student(); // object is created and is in transient state
			s.setRno(50);
			s.setName("Prasad");
			s.setEmail("prasad@gmail.com");
			s.setPer(91.20);
			
			Student std = (Student)session.merge(s); 
			// now s is detached and merge created another copy of s which is in persistent state
			// std is the new object (copy of s) created by merge
			
			System.out.println(std.getName());
			
			System.out.println(s);
			System.out.println(std);
			
			
			 // This snippet saves a record in the database
			 // How ?
			 //	Hibernate keeps the session cache and the database in sync.
			 //	When a change is made in the session cache and the session is flushed, the change is also reflected in the database. */
			
				//merge() does the following internally:
				//Step-by-step inside merge():
				//Hibernate checks if an entity with the same identifier (primary key) already exists in the session cache.
				//If not found, it looks in the database.
				//If a matching record exists in the database, Hibernate:
				//Fetches that record into the session (as a persistent instance).
				//Copies the values from your detached/transient object (s) into that persistent instance.
				//If no matching record exists in the database:
				//Hibernate creates a new persistent instance with the values from s.
				//Marks it for INSERT during flush.
				//The method returns a persistent instance (not necessarily the same as s).
				//The original s remains unchanged in transient or detached state.
			
			transaction.commit();
			
			
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
