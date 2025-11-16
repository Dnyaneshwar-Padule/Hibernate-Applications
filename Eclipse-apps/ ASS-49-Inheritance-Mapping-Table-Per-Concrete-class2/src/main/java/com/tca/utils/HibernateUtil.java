package com.tca.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;
	
	static {
		try {
			
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError();
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
