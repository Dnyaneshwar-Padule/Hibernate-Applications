package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.utils.HibernateUtil;


public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
		
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			
			// Inserting Student with Courses
			/*
			Course c = new Course();
			c.setCno(111);
			c.setName("Advance Java");
			
			Course c2 = new Course();
			c2.setCno(222);
			c2.setName("Hibernate");
			
			Student s = new Student();
			s.setRno(101);
			s.setName("Aayush");
			s.setCourses(Arrays.asList(c,c2));
			
			session.persist(s);
			*/
			
			
			// inserting Course with student
			/*
			Course c = new Course();
			c.setCno(333);
			c.setName("Spring Boot");
			
			Student s = new Student();
			s.setName("Nipun");
			s.setRno(102);
			s.setCourses(Arrays.asList(c));
			c.setStudents(Arrays.asList(s));
			
			session.persist(c);
			*/
			
			
			
			//Inserting a new student with old course
			/*
			Student s = new Student();
			s.setRno(103);
			s.setName("Aakash");
			s.setCourses(Arrays.asList( 
								session.get(Course.class, 222), 
								session.get(Course.class, 111) 
							)
					);
			
			
			session.persist(s);
			*/
			
			//Fetching student record
			/*
			Student s = session.get(Student.class, 101);
			System.out.println(s.getName() + " " + s.getRno());
			System.out.println(s.getName() + " is enrolled in");
			
			List<Course> l = s.getCourses();
			for(Course c : l) 
				System.out.println(c.getName());
			
			*/
			
			
			//delete a student
			/*
				session.delete(session.get(Student.class, 102));			 
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