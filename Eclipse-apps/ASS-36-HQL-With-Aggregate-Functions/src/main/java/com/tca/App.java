package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.utils.HibernateUtil;


public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
/*			
			// Count(*)
			Query<Long> countQuery = session.createQuery("SELECT count(*) from Student", Long.class);
			
			Long studentCount = countQuery.uniqueResult();
			Long studentCount = countQuery.getSingleResult();
			
			if(studentCount == 0) {
				System.out.println("Student table is empty !");				
			}
			else{
				System.out.println(studentCount + " students are present in the class..");
			}
*/
			
		
/*			
			// Count of failed students
			Query<Long> query = session.createQuery("SELECT count(*) FROM Student WHERE per < 40", Long.class);
			Long cnt = query.getSingleResult();
			System.out.println("Failed Students count : " + cnt);
*/
			
			
/*
			// Average marks
			Query<Double> query = session.createQuery("SELECT avg(per) from Student", Double.class);
			Double avgPer = query.getSingleResult();
			System.out.println("Average Percentage : " + avgPer);
*/
			
		
/*    
			// Topper Marks 
			Query<Double> query = session.createQuery("SELECT max(per) from Student", Double.class);
			Double topperMarks = query.getSingleResult();
			System.out.println("Topper marks : " + topperMarks);
*/			
	
			
/*
			// All Passed student count
			Query<Long> query = session.createQuery("SELECT count(*) FROM Student WHERE per >= 40" , Long.class);
			Long count = query.getSingleResult();
			System.out.println(count + " students are passed !");
*/
			
			
/*
			//Failed students 
			Query<Long> query = session.createQuery("SELECT count(*) FROM Student WHERE per < 40", Long.class);
			Long cnt = query.getSingleResult();
			if(cnt == 0) {
				System.out.println("All students are Passed !!!!");
			}
			else {
				System.out.println("Failed Students : " + cnt);
			}
*/	
			
			
/*
			// Student with minimum marks
			Query<Double> query = session.createQuery("SELECT min(per) FROM Student", Double.class); 
			Double minMarks = query.getSingleResult();
			System.out.println("Minimum marks of student are " + minMarks);
*/
		
	
		
			//Minimum marks of passed student
			// (It should not be less than 40)
			Query<Double> query = session.createQuery("SELECT min(per) FROM Student WHERE per >= 40", Double.class); 
			Double minMarks = query.getSingleResult();
			System.out.println("Minimum marks in passed students : " + minMarks);

			
			
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
