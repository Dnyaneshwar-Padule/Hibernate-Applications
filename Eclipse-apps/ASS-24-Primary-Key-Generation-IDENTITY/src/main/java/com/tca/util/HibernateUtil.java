package com.tca.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;
	
	static {
		try {
			Configuration configuration =  new Configuration().configure(); 
			configuration.addAnnotatedClass(Student.class);
			SESSION_FACTORY = configuration.buildSessionFactory();
		}
		catch(Exception e) {
			throw new ExceptionInInitializerError("Unable to build sessionFactory : " + e.getMessage());
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
	
	public static void shutdown() {
		if(SESSION_FACTORY != null)
				SESSION_FACTORY.close();
	}
}
