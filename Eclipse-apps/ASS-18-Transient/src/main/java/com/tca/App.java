package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.util.HibernateUtil;

public class App {
    public static void main(String[] args) {

    	Session session = null;
    	Transaction transaction = null;
    	
    	try {
    		session = HibernateUtil.getSessionFactory().openSession();
    		transaction = session.beginTransaction();
    		
    		Student student = new Student();
    		student.setRno(38);
    		student.setName("Athrva");
    		student.setPer(86.80);
    		student.setEmail("athrva@gmail.com");
    		
    		session.save(student);
    		session.flush();
    		
    		Student s = session.get(Student.class, 38);
    		System.out.println(s);
    		
    		transaction.commit();
    	}
    	catch(Exception e) {
    		if(transaction != null) transaction.rollback();
    		e.printStackTrace();
    	}
    	finally {
			if(session != null) session.close();
			HibernateUtil.shutdown();
		}
    }
}
