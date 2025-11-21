package com.tca.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.entity.StudentCourse;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;
import com.tca.util.HibernateUtil;

public class StudentCourseDaoImpl implements StudentCourseDao {

	@Override
	public Boolean saveRegistration(Student student, Course course) throws DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			StudentCourse studentCourse = new StudentCourse();
			studentCourse.setCourse(session.merge(course));
			studentCourse.setStudent(session.merge(student));
			//session.save(studentCourse);
			session.persist(studentCourse);
			transaction.commit();
			return true;
		}
		catch(ConstraintViolationException ce) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Duplicate Student in same course is not allowed !", ce);
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			throw new DatabaseException("Failed to save registration", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<StudentCourse> fetchRegistrationsByCourse(Integer cno) throws AppException, NotFoundException, DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Course c = session.get(Course.class, cno);
			
			if(c == null) 
				throw new NotFoundException("Course with course number " + cno + " does not exists !");
		
			List<StudentCourse> enrollments = c.getRegistrations();
			Hibernate.initialize(enrollments);
			transaction.commit();
			return enrollments;
			
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			pe.printStackTrace();
			throw new DatabaseException("Problem while fetching enrollments", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public List<StudentCourse> fetchRegistrationsByStudent(Integer rno) throws AppException, NotFoundException, DatabaseException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Student s = session.get(Student.class, rno);
			
			if(s == null) 
				throw new NotFoundException("Student with roll number " + rno + " does not exists !");
			
			List<StudentCourse> enrollments = s.getRegistrations();
			Hibernate.initialize(enrollments);
			transaction.commit();
			return enrollments;
			
		}
		catch(Exception pe) {
			if(transaction != null) 
				transaction.rollback();
			pe.printStackTrace();
			throw new DatabaseException("Problem while fetching enrollments", pe);
		}
		finally {
			if(session != null)
				session.close();
		}
	}
}
