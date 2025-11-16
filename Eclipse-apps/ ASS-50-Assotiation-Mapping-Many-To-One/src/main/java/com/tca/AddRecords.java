package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Customer;
import com.tca.entities.Orders;
import com.tca.utils.HibernateUtil;

public class AddRecords {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Customer c1 = new Customer();
			c1.setEmail("prasad@gmail.com");
			c1.setName("Prasad");
			
			Customer c2 = new Customer();
			c2.setEmail("ganesh@gmail.com");
			c2.setName("Ganesh");
			
			Customer c3 = new Customer();
			c3.setEmail("roshan@gmail.com");
			c3.setName("Roshan");
			
			Customer c4 = new Customer();
			c4.setEmail("avishkar@gmail.com");
			c4.setName("Avishkar");
			
			Orders o1 = new Orders();
			o1.setCustomer(c1);
			o1.setPrice(1500.0);
			o1.setProduct("Keyboard");
			
			Orders o2 = new Orders();
			o2.setCustomer(c1);
			o2.setPrice(999.0);
			o2.setProduct("Mouse");
			
			Orders o3 = new Orders();
			o3.setCustomer(c2);
			o3.setPrice(150000.0);
			o3.setProduct("Laptop");
			
			Orders o4 = new Orders();
			o4.setCustomer(c3);
			o4.setPrice(35000.0);
			o4.setProduct("Mobile");
			
			Orders o5 = new Orders();
			o5.setCustomer(c4);
			o5.setPrice(300.0);
			o5.setProduct("Charging cable");
			
			session.persist(c1);
			session.persist(c2);
			session.persist(c3);
			session.persist(c4);

			session.persist(o1);
			session.persist(o2);
			session.persist(o3);
			session.persist(o4);
			session.persist(o5);
			
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
