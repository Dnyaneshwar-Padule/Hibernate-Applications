package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Address;
import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;


/*
	Component Mapping is used if we want to group related fields
	like,
		Address can contains fields like, city name, apartment name, district name, pin code
		rather, defining all fields in the entity column, we can define them in a separate Address class
		and we can use that class, in the entity class
		
		It's advantage is, if more than one records have same address, 
		then all that entity objects will store the same address object
		
		suppose, 
		student A, student B and student C
		all are from same city, then all three student will store same address object
		It saves memory.
		
		And we use component mapping for composite keys
		we use @EmbeddedId annotation to achieve that
		

 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			AddRecords.main(null); // adding records in the table
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			 
			
			Address address = new Address();
			address.setCity("Pune");
			address.setPinCode(411038);
			
			Query<Student> q = session.createQuery("From Student where address = :address", Student.class);
			q.setParameter("address", address);
			
			List<Student> l = q.list();
			
			for(Student student : l) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getAddress().getCity());
				System.out.println("PIN  : " + student.getAddress().getPinCode());
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