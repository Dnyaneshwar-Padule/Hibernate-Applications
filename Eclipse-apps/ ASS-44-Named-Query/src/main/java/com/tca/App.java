package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.MulticastChannel;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
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
			
			//Query<Student> query = session.createNamedQuery("Student_getByRno", Student.class);
			//query.setParameter("rollNumber", 3);
			
			Query<Student> query = session.createNamedQuery("Student_getByCity", Student.class);
			query.setParameter("studentCity", "Pune");
			
			MutationQuery updateQuery = session.createNamedMutationQuery("update_name_by_rno");
			updateQuery.setParameter("rollNumber", 1);
			updateQuery.setParameter("studentName", "Mayur");
			
			updateQuery.executeUpdate();
			
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