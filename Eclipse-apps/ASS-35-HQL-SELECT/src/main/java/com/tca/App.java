package com.tca;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;


public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			/*
			 * 
			Query<Student> query = session.createQuery("from Student", Student.class);
			
			List<Student> students = query.list();
			
			for(Student s : students)
			{
				System.out.println(s);
			}
			 */

			//Query query = session.createQuery("SELECT name, per FROM Student");
			Query<Object[]> query = session.createQuery("SELECT name, per FROM Student WHERE per > 50", Object[].class);
			
			List<Object[]> students = query.list();
			
			for(Object[] student : students) {
				for(Object field : student) {
					System.out.print(field + " ");
				}
				System.out.println();
			}
			
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
