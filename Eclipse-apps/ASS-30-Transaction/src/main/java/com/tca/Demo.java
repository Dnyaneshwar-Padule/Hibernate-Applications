package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Account;
import com.tca.util.HibernateUtil;

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
			
			// I will fetch two records from the database table
			// And will make a transaction
			
			Account sender = session.get(Account.class, senderAccountNo);
			
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
			Account receiver = session.get(Account.class, receiverAccountNo);
			if(receiver == null) {
				System.out.println("Receiver's account no is not valid !");
				return;
			}
		
			
//			System.in.read();
			
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
