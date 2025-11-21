package com.tca.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.entity.StudentCourse;
import com.tca.exception.DatabaseException;
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
			studentCourse.setCourse(course);
			studentCourse.setStudent(student);
			session.persist(studentCourse);
			transaction.commit();
			return true;
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
}
