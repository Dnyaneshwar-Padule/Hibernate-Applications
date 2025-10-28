package com.tca;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

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
			
			// Select all columns ...
			
			// here the 'student' is database side name of the table
			NativeQuery<Student> query = session.createNativeQuery("SELECT * FROM student", Student.class);
			
			List<Student> students  = query.list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			
			*/
			
			// Select Specific columns
			
			//NativeQuery<Object[]> query = session.createNativeQuery("select per,name,city from student",Object[].class);
			
			
			//NativeQuery<Object[]> query = session.createNativeQuery("select per,name,city from student where per >= ?1",Object[].class);
			//query.setParameter(1, 75.0);
		
			//NativeQuery<Object[]> query = session.createNativeQuery("select per,name,city from student where per >= ?1 and city = ?2",Object[].class);
			//query.setParameter(1, 50);
			//query.setParameter(2, "Pune");
			
			//NativeQuery<Object[]> query = session.createNativeQuery("select per,name,city from student where per >= :minPer and city = :targetCity",Object[].class);
			//query.setParameter("minPer", 45);
			//query.setParameter("targetCity", "Pune");
			
			//NativeQuery<Object[]> query = session.createNativeQuery("select city, count(*) as count FROM student group by city", Object[].class);
			
			NativeQuery<Object[]> query = session.createNativeQuery("select * from student", Object[].class);
			query.setMaxResults(3);
			
			for(int i = 0; i < 10; i = i + 3) {
				query.setFirstResult(i);
				
				List<Object[]> students = query.list();
				
				for(Object[] student : students) {
					for(Object data : student) {
						System.out.println(data + " ");
					}
					System.out.println("-------------------------");
				}
				System.out.println("+=-=-=-=-=-=+ Fetched records from " + i + " to " + (i + 3) + " +=-=-=-=-=-=+");
				
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