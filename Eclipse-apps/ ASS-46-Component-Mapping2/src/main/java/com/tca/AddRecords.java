package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Employee;
import com.tca.entities.EmployeeId;
import com.tca.utils.HibernateUtil;

public class AddRecords {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			EmployeeId employeeId1 = new EmployeeId();
			employeeId1.setCompany("Google");
			employeeId1.setEmployeeNo(1024);
			Employee e1 = new Employee();
			e1.setEmployeeId(employeeId1);
			e1.setName("Om");
			
			EmployeeId employeeId2 = new EmployeeId();
			employeeId2.setCompany("Apple");
			employeeId2.setEmployeeNo(2048);
			Employee e2 = new Employee();
			e2.setEmployeeId(employeeId2);
			e2.setName("Sandip");
			
			EmployeeId employeeId3 = new EmployeeId();
			employeeId3.setCompany("Microsoft");
			employeeId3.setEmployeeNo(3072);
			Employee e3 = new Employee();
			e3.setEmployeeId(employeeId3);
			e3.setName("Varun");
			
			session.persist(e1);
			session.persist(e2);
			session.persist(e3);
			
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
