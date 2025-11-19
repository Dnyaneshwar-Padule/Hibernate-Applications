package com.tca.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tca.config.HibernateConfig;

public final class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private HibernateUtil() {}
	
	public static SessionFactory  getSessionFactory() {
		
		if(sessionFactory == null) {
			sessionFactory = new Configuration()
					.configure(HibernateConfig.HB_CONFIG_FILE_NAME)
					.buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	public static void closeSessionFactory(){ 
		if(sessionFactory != null)
				sessionFactory.close();
	}
}
