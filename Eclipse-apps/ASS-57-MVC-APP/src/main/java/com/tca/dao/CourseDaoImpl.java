package com.tca.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entity.Course;
import com.tca.exception.DatabaseException;
import com.tca.util.HibernateUtil;

public class CourseDaoImpl implements CourseDao {

	@Override
	public Integer saveCourse(Course c) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session  = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.persist(c);
			Integer cno = (Integer)session.getIdentifier(c);
			transaction.commit();
			return cno;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to save course", pe);
		}
		finally {
			if( session != null) 
				session.close();
		}
	}

	@Override
	public Course fetchCourse(Integer cno) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Course c = session.get(Course.class, cno);
			transaction.commit();
			return c;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch course", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}

	@Override
	public List<Course> fetchAllCourses() throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			List<Course> courses =  session.createQuery("From Course", Course.class).list();
			transaction.commit();
			return courses;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch courses", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<Course> fetchCourseByName(String courseName) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Query<Course> query = session.createQuery("From Course where name LIKE :courseName", Course.class);
			query.setParameter("courseName", "%" + courseName + "%");
			List<Course> courses = query.list();
			
			transaction.commit();
			return courses;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch course", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}

	@Override
	public Boolean updateCourse(Course course) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.merge(course);
			transaction.commit();
			return true;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to update course", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}

	@Override
	public Boolean deleteCourse(Course course) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.remove(course);
			transaction.commit();
			return true;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to delete course", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}

}
