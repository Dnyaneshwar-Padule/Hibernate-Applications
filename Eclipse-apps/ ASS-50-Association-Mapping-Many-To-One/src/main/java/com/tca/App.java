package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Customer;
import com.tca.entities.Orders;
import com.tca.utils.HibernateUtil;


/*
 	When we get the record of the customer the orders don't come until we call a getter for it, because of lazy loading
 	but if we get a order, the customer will come with it, because it has eager loading
 	
 	For one order there is only one Customer, so we can get it with the order
 	
 	but for one customer, there will be many orders, fetching orders with customer, will increase space and time 
 	so get orders only if needed
 	
 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			AddRecords.main(null); // adding records in the table
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Customer c = session.get(Customer.class, 1);
			System.out.println(c.getName() + " " +  c.getEmail());
			
			List<Orders> l = c.getOrders();

			for(Orders o : l)
				System.out.println(o.getProduct() + " " + o.getPrice() + " " + o.getId());
			
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