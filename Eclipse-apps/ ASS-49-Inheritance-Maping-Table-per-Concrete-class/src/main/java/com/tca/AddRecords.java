package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Employee;
import com.tca.entities.Executive;
import com.tca.utils.HibernateUtil;

public class AddRecords {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Employee e  = new Employee();
			e.setId(101);
			e.setName("Jay");
			e.setSalary(35000.0);
			
			Employee e2 = new Employee();
			e2.setId(102);
			e2.setName("Ram");
			e2.setSalary(95000.0);
			
			Executive ex = new Executive();
			ex.setId(103);
			ex.setName("Nupur");
			ex.setRole("Dev");
			ex.setBonus(45000.0);
			
			Executive ex2 = new Executive();
			ex2.setId(104);
			ex2.setName("Vipin");
			ex2.setRole("Dev");
			ex2.setBonus(40499.0);
			
			session.persist(e);
			session.persist(e2);
			session.persist(ex);
			session.persist(ex2);
			
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
