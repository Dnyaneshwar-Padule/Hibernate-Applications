package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;


/*

	Filter allows us to apply where conditions on SQL with dynamic values
 
 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			//Filter filter = session.enableFilter("cityFilter");
			//filter.setParameter("targetCity", "Pune");
			
			Filter filter = session.enableFilter("perFilter");
			filter.setParameter("minPer", 40.0);
			
			Query<Student> query = session.createQuery("FROM Student", Student.class);
			List<Student> students = query.list();
			
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