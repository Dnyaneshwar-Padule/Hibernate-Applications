package com.tca.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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


}
