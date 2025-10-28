package com.tca;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;

/**
 * This is an hibernate application demonstrates the use of session.update() method
 * 
 * Before we update the record, we have to get the record from table, 
 * then we can modify some fields and give the updated object to the update() method
 * 
 * because, update method updates all fields (except primary key), 
 * so if there is some field data is missing in the object, then the respective column data will be lost
 */
public class App {
    
	/**
	 * main method, entry point of the application 
	 * 
	 * @param args an String array
	 */
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			//build session, to perform database operations
			session = HibernateUtil.getSessionFactory().openSession();
			
			//begin transaction
			transaction = session.beginTransaction();
			
			//rno is the roll number of the student 
			//It is the primary key in database table (student table)
			//So we will get the student record through rno (roll number)
			int rno = 27;
			
			//get method works for all types of entity classes
			//so it does not have a fixed return type
			// So we need to cast the returned object to Student class
			Student student = (Student) session.get(Student.class, rno ); // we have also specified the class name, so that hibernate could know in which table to search the record
			
			if(student == null) {
				System.out.println("Student record with roll number " + rno + " does not exists !");
			}
			else {
				//update name and percentage in the received object
				student.setName("Ramesh");
				student.setPer(90.94);
				
				//Update the record to newly modified fields
				//session.update("Student", student);
				session.update(student);
				
				/*
				 * Actually, this code can update the student record, without the update method
				 * just get the student record, modify the fields in the object
				 * and commit transaction hibernate will automatically update the record
				 * hibernate uses dirty checking mechanism to do it
				 */
				
				//commit transaction to reflect changes to the database
				transaction.commit();
			
				System.out.println("The student record is updated successfully !");
			}
		
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
			HibernateUtil.shutdown();
		}
	
    }
}
