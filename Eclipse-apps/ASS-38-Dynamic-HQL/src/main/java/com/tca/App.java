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
				We can write dynamic HQL by 2 ways
					1. using positional parameters
					2. using named parameter
			*/
			
			
			
			// 1. Using positional Parameters
			
    		//Query<Student> query = session.createQuery("FROM Student WHERE per=?1",Student.class);	
    		//query.setParameter(1, 98.0);
    		
    		//Query<Student> query = session.createQuery("FROM Student WHERE per >= ?1 and per <= ?2", Student.class);
			//query.setParameter(1, 50.0);
			//query.setParameter(2, 99.0);
			
			//Query<Student> query = session.createQuery("FROM Student WHERE city LIKE ?1", Student.class);
			//query.setParameter(1, "Pune");
			
			//Query<Student> query = session.createQuery("FROM Student WHERE rno = ?0", Student.class);
			//query.setParameter(0, 1);
			
			//Query<Student> query = session.createQuery("FROM Student WHERE city LIKE ?1 and per >= ?2 and per <= ?3 ", Student.class);
			//query.setParameter(1, "P%");
			//query.setParameter(2, 40.0);
			//query.setParameter(3, 95.0);
			
			
			
			
			// 2. Using named parameter
			
			//Query<Student> query = session.createQuery("FROM Student WHERE per >= :minPer and per <= :maxPer", Student.class);
			//query.setParameter("minPer", 55.0);
			//query.setParameter("maxPer", 95.0);
			
			
			Query<Student> query = session.createQuery("FROM Student where name LIKE :studentName", Student.class);			
    		query.setParameter("studentName", "Sushant");
			
			//Update
			//Query<?> query = session.createQuery("UPDATE Student SET per = per + :additionalPer WHERE city LIKE :destCity");
			//query.setParameter("additionalPer", 2.5);
			//query.setParameter("destCity", "Panji");
			//Integer rowsUpdated = query.executeUpdate();
			//System.out.println(rowsUpdated + " records updated (affected) !");
			
			
			//DELETE
			//Query<?> query = session.createQuery("DELETE FROM Student WHERE city LIKE :tempCity");
			//query.setParameter("tempCity", "Jaipur");
			//Integer result = query.executeUpdate();
			//System.out.println(result + " records deleted !");
			
			
			List<Student> students  = query.list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
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