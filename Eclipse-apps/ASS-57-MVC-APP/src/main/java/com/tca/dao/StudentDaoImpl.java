package com.tca.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entity.Student;
import com.tca.exception.DatabaseException;
import com.tca.util.HibernateUtil;


public class StudentDaoImpl implements StudentDao {

	@Override
	public Integer saveStudent(Student s) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			session.persist(s);
			Integer id = (Integer)session.getIdentifier(s);
			
			transaction.commit();
			return id;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to save student record", pe);
		}
		finally {
			if(session != null ) session.close();
		}
		
	}

	@Override
	public Student fetchStudent(Integer rno) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Student s =  session.get(Student.class, rno);
			transaction.commit();
			return s;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch student record", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}

	@Override
	public List<Student> fetchAllStudents() throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			List<Student> students = session.createQuery("From Student", Student.class).list();
			transaction.commit();
			return students;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch student records", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<Student> fetchStudentsByName(String name) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query<Student> query = session.createQuery("From Student where name like :studentName", Student.class);
			query.setParameter("studentName", "%" + name + "%");
			List<Student> students = query.list();
			transaction.commit();
			return students;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch student record", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<Student> fetchstudentsByCity(String city) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query<Student> query = session.createQuery("From Student where city like :studentCity", Student.class);
			query.setParameter("studentCity", "%" + city + "%");
			List<Student> students = query.list();
			transaction.commit();
			return students;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to fetch student record", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public Boolean updateStudent(Student student) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		Boolean result = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			result = session.merge(student) == null ? false : true;
			transaction.commit();
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to update student record", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
		return result;
	}

	@Override
	public Boolean deleteStudent(Student student) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		boolean result = false;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.remove(student);
			transaction.commit();
			result = true;
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to delete student record", pe);
		}
		finally {
			if(session != null)	
				session.close();
		}
		
		return result;
	}

}
