package com.tca.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entity.Student;
import com.tca.util.HibernateUtil;


public class StudentDaoImpl implements StudentDao {

	@Override
	public Integer saveStudent(Student s) {
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
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			return null;
		}
		finally {
			if(session != null ) session.close();
		}
		
	}

	@Override
	public Student fetchStudent(Integer rno) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Student s =  session.get(Student.class, rno);
			transaction.commit();
			return s;
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			return null;
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}

	@Override
	public List<Student> fetchAllStudents() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			List<Student> students = session.createQuery("From Student", Student.class).list();
			transaction.commit();
			return students;
		}
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			return null;
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<Student> fetchStudentsByName(String name) {
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
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			return null;
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<Student> fetchstudentsByCity(String city) {
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
		catch(Exception e) {
			if(transaction != null) transaction.rollback();
			return null;
		}
		finally {
			if(session != null)
				session.close();
		}
	}


}
