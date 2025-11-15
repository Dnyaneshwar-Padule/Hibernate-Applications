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
			e.setEno(101);
			e.setName("Jay");
			
			Employee e2 = new Employee();
			e2.setEno(102);
			e2.setName("Ram");
			
			Executive ex = new Executive();
			ex.setEno(103);
			ex.setName("Nupur");
			ex.setRole("Dev");
			
			Executive ex2 = new Executive();
			ex2.setEno(104);
			ex2.setName("Vipin");
			ex2.setRole("Dev");
			
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
