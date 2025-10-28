package com.tca.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;
	
	static {
		try {
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
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
