package com.tca;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;


public class App{
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (Scanner sc = new Scanner(System.in)) {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Long totalRecords = session.createQuery("SELECT count(*) FROM Student", Long.class).getSingleResult();
			
			Query<Student> query = session.createQuery("FROM Student ORDER BY rno", Student.class);
			query.setMaxResults(3);
			
			for(int i = 0; i < totalRecords; i = i+3 ) {
				System.out.print("Enter any key to continue...");
				sc.next();
				
				query.setFirstResult(i);
				List<Student> students = query.list();
				
				for(Student student : students) {
					System.out.println("Name : " + student.getRno());
					System.out.println("Rno  : " + student.getName());
					System.out.println("Per  : " + student.getPer());
					System.out.println("City : " + student.getCity());
					System.out.println("--------------------------------------------");
				}
				
			}
			
			System.out.println("End....");
			
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
