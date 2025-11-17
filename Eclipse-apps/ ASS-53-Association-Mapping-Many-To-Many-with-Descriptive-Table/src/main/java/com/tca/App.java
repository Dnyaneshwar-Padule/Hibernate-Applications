package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.entities.StudentCourse;
import com.tca.utils.HibernateUtil;


public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
		
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			// Adding new Student Associated with a new course
			/*
			Student s = new Student();
			s.setRno(101);
			s.setName("Rahul");
			
			Course c = new Course();
			c.setCno(111);
			c.setName("Advance Java");
			
			StudentCourse sc = new StudentCourse();
			sc.setId(1);
			sc.setCourse(c);
			sc.setStudent(s);
			sc.setRegDate(LocalDate.now());
			
			session.persist(sc);
			*/
			
			// Adding new Student
			/*
			Student s = new Student();
			s.setName("Mayur");
			s.setRno(102);
			
			session.persist(s);
			*/
			
			
			
			//Adding a course
			/*
			Course c = new Course();
			c.setCno(222);
			c.setName("Hibernate");
			session.persist(c);
			*/
			
			
			// Assigning a course to a existing student with date 2025-05-31
			/*
			StudentCourse sc = new StudentCourse();
			sc.setId(2);
			sc.setCourse(session.get(Course.class, 222));
			sc.setStudent(session.get(Student.class, 102));
			sc.setRegDate(LocalDate.of(2025, 5, 31));
			
			session.persist(sc);
			*/
			
			
			//Fetching data for specific date
			/*
			String hql = "FROM StudentCourse where regDate = :uDate";
			
			Query<StudentCourse> query = session.createQuery(hql, StudentCourse.class);
			query.setParameter("uDate", LocalDate.of(2025, 5, 31));
			
			List<StudentCourse> l = query.getResultList();
			
			
			for(StudentCourse sc : l ) 
				System.out.println(sc.getStudent().getName() + " has registered for " + sc.getCourse().getName() + " on " + sc.getRegDate());
			*/
			
			
			
			//Fetching all data
			/*
			String hql = "FROM StudentCourse";
			
			Query<StudentCourse> query = session.createQuery(hql, StudentCourse.class);
			
			List<StudentCourse> l = query.getResultList();
			
			
			for(StudentCourse sc : l ) 
				System.out.println(sc.getStudent().getName() + " has registered for " + sc.getCourse().getName() + " on " + sc.getRegDate());
			*/
			
			// In which courses student 101 is enrolled in ?
			
			String hql = "FROM StudentCourse where student = :srno";
			
			Student s = new Student();
			s.setRno(101);
			
			Query<StudentCourse> query = session.createQuery(hql, StudentCourse.class);
			query.setParameter("srno", s);
			
			List<StudentCourse> l = query.getResultList();
			
			
			for(StudentCourse sc : l ) 
				System.out.println(sc.getStudent().getName() + " has registered for " + sc.getCourse().getName() + " on " + sc.getRegDate());
			
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