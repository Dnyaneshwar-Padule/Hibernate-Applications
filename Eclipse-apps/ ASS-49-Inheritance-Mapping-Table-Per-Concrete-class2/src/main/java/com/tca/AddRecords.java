package com.tca;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Department;
import com.tca.entities.Employee;
import com.tca.utils.HibernateUtil;

public class AddRecords {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			// Add 2 departments and 3 employees
			
			Department d1= new Department();
			d1.setId(1);
			d1.setName("CS");
			
			Department d2 = new Department();
			d2.setId(2);
			d2.setName("AI");
			
			Employee e1 = new Employee();
			e1.setEid(111);
			e1.setName("Mahesh");
			e1.setSalary(69000.0);
			e1.setDepartment(d1);

			Employee e2 = new Employee();
			e2.setEid(222);
			e2.setName("Madan");
			e2.setSalary(59000.0);
			e2.setDepartment(d2);

			Employee e3 = new Employee();
			e3.setEid(333);
			e3.setName("Mangesh");
			e3.setSalary(55000.0);
			e3.setDepartment(d1);

			Employee e4 = new Employee();
			e4.setEid(444);
			e4.setName("John");
			e4.setSalary(45000.0);
			
			d1.setEmployees( Arrays.asList(e1,e3) );
			d2.setEmployees( Arrays.asList(e2) );
			
			session.persist(d1);
			session.persist(d2);
			session.persist(e4);
			
			transaction.commit();
			
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		finally{
			if (session != null) session.close();
			//HibernateUtil.shutdown();
		}
		
	}
	
}
