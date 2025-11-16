package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Department;
import com.tca.entities.Employee;
import com.tca.utils.HibernateUtil;


/*
 	
 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			//AddRecords.main(null); // adding records in the table
		
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			
			
			// Add employees and departments
			//Adding department by employee
			/*
			Department d = new Department();
			d.setId(111);
			d.setName("CS");
			
			Employee e = new Employee();
			e.setEid(101);
			e.setName("Rakesh");
			e.setSalary(75000.0);
			e.setDepartment(d);
			
			session.persist(e);
			*/
			
			
			// Getting Department by Employee
			
			Employee e = session.get(Employee.class, 101);
			System.out.println(e.getName() + " " + e.getEid() + " " + e.getSalary());
			Department d = e.getDepartment();
			
			System.out.println(d.getName() + " " + d.getId());
			
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