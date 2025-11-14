package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.utils.HibernateUtil;
import com.tca.entities.Student;

public class AddRecords {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			 
			Student s1 = new Student();
			s1.setRno(1);
			s1.setCity("Pune");
			s1.setName("Anant");
			s1.setPer(75.0);
			
			Student s2 = new Student();
			s2.setRno(2);
			s2.setCity("Mumbai");
			s2.setName("Varun");
			s2.setPer(80.0);
			
			Student s3 = new Student();
			s3.setRno(3);
			s3.setCity("Pune");
			s3.setName("Sushant");
			s3.setPer(98.0);
			
			Student s4 = new Student();
			s4.setRno(4);
			s4.setCity("Panji");
			s4.setName("Aditya");
			s4.setPer(45.0);
			
			Student s5 = new Student();
			s5.setRno(5);
			s5.setCity("Hydrabad");
			s5.setName("Anuj");
			s5.setPer(85.0);
			
			Student s6 = new Student();
			s6.setRno(6);
			s6.setCity("Bangalore");
			s6.setName("Ajay");
			s6.setPer(65.0);
			
			Student s7 = new Student();
			s7.setRno(7);
			s7.setCity("Bangalore");
			s7.setName("Vijay");
			s7.setPer(77.0);
			
			Student s8 = new Student();
			s8.setRno(8);
			s8.setCity("Kolkata");
			s8.setName("Ram");
			s8.setPer(89.0);
			
			session.persist(s1);
			session.persist(s2);
			session.persist(s3);
			session.persist(s4);
			session.persist(s5);
			session.persist(s6);
			session.persist(s7);
			session.persist(s8);
			
			transaction.commit();
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		finally{
			if (session != null) session.close();
			HibernateUtil.shutdown();
		}
		
	}
	
}
