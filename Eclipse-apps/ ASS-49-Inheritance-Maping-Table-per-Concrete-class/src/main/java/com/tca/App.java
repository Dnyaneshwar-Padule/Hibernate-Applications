package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Employee;
import com.tca.entities.Executive;
import com.tca.utils.HibernateUtil;


/*
 	In table per concrete class mapping
 	suppose there is a parent class which is abstract
 	so, we can't instantiate it, but if the child (entity) classes are not abstract
 	we can create their objects,  
 	suppose, parent (abstract) class have multiple child (entity) classes,
 	then with table per (concrete) class strategy, hibernate will create 
 	separate table for every class. 

 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			AddRecords.main(null); // adding records in the table
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			 
			
			Employee e = session.get(Employee.class, 101);
			System.out.println(e.getName() + " " + e.getId() + " " + e.getSalary());
			
			Executive e2 = session.get(Executive.class, 103);
			System.out.println(e2.getName() + " " + e2.getId() + " " + e2.getRole() + " " + e2.getBonus());
			
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