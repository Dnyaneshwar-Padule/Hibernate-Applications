package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Employee;
import com.tca.entities.EmployeeId;
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
			
			EmployeeId eId = new EmployeeId();
			eId.setCompany("Google");
			eId.setEmployeeNo(1024);
			
			
			Employee e = session.get(Employee.class, eId);
			
			if(e!= null) {
				System.out.println("Employee Name : " + e.getName());
				System.out.println("Employee No.  : " + e.getEmployeeId().getEmployeeNo());
				System.out.println("Employee Company : " + e.getEmployeeId().getCompany());
			}
			else {
				System.out.println("Record not found !");
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