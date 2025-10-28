package com.tca;

import java.util.concurrent.locks.Lock;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Account;
import com.tca.util.HibernateUtil;

/*
 * 
 
 	When one resource is accessed by multiple users, at that time
 	we need concurrency control
 	
 	 suppose, 
 	 there is an web application which is making a transaction
 	 it fetched sender's and receiver's accounts, and is waiting for some input
 	 to commit the changes 
 	 
 	 but while it was waiting, somebody did a transaction via an android app
 	 then at this time, when the hibernate application commit transaction
 	 then the changes made by the android app, will gone...
 	 
 	 and this should not be happen, so we use locks for that resource
 	 
 	 it works like this,
 	 when hibernate has accessed the sender's and receive's account
 	 it creates a lock for that resource
 	 So that nobody except hibernate could access the resource and make changes
 	 the will be gone once hibernate completes it's transaction
 
 * 
 */

public class Demo {
   
	/**
	 * main method, app's entry point function 
	 * @param args, an array of String
	 */
	public static void main(String[] args) {
        
		Session session = null;
		Transaction transaction = null;
		
		int senderAccountNo = 1001;
		int receiverAccountNo = 1002;
		
		float amountToTransfer = 5000f;
		
		try {	
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
		
			Account sender = session.get(Account.class, senderAccountNo, LockMode.PESSIMISTIC_WRITE);
			
			if(sender == null) {
				System.out.println("Sender's account no is not valid !");
				return;
			}
			
			if(sender.getAmount() < amountToTransfer) {
				System.out.println("Insufficient balance !");
				return;
			}
			
			if(sender.getAmount() - amountToTransfer < 500) {
				System.out.println("On transferrying money, sender's account won't have minimum balance !");
				return;
			}
			
			//fetch receivers account
			Account receiver = session.get(Account.class, receiverAccountNo, LockMode.PESSIMISTIC_WRITE);
			if(receiver == null) {
				System.out.println("Receiver's account no is not valid !");
				return;
			}
		
			
			System.in.read();
			
			// deduct money from sender's account
			sender.setAmount( sender.getAmount() - amountToTransfer );
			
			//credit money in receiver's account
			receiver.setAmount( receiver.getAmount() + amountToTransfer );
		
			//It will work without session.update() method
			//with hibernate's dirty checking mechanism
			
			transaction.commit();
			
			System.out.println("Transaction is performed successfully !");
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
