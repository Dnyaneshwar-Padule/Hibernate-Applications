package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
		
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			
			
			// Adding a car and a engine
			/*
			 
			Engine e = new Engine();
			e.setEid(101);
			e.setType("Petrol");
			
			Vehicle v = new Vehicle();
			v.setName("Swift");
			v.setCno("MH25DP8749");
			v.setEngine(e);  // assign an engine to a vehicle
			e.setVehicle(v);  // assign a vehicle to an engine
						
			session.persist(v);
			 */

			/*
			// Get car by engine ID
			Engine e = session.get(Engine.class, 101);
			Vehicle v = e.getVehicle();
			System.out.println(v.getCno() + " " + v.getName() + " " + e.getEid() + " " + e.getType());
			*/
			
			
			//Trying to assign existing engine to new car
			/*
			 
			 // It will throw an exception, because duplicate engine is not allowed
			 
			Engine e = session.get(Engine.class, 101);
			Vehicle v = new Vehicle();
			v.setCno("MH12AB1234");
			v.setName("Bolero");
			v.setEngine(e);
			
			session.persist(v);
			*/			
			
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