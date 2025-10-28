package com.tca.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * An utility class to manage hibernate SessionFactory management.
 * <p>
 * SessionFactory should be created once at the startup for an application.
 * This class builds sessionFactory in it's static block and provides a global access method
 */
public class HibernateUtil {

	// Singleton SessionFactory instance
	private static final SessionFactory sessionFactory;

	static {
		try (FileInputStream fis =  new FileInputStream("src/main/resources/hibernate.properties")){
		
			Configuration configuration= new Configuration();
			
			//Load Properties from hibernate.properties file
			Properties properties = new Properties();
			properties.load( fis );
			
			configuration.setProperties(properties);
			configuration.addResource("student.hbm.xml");
			
			sessionFactory = configuration.buildSessionFactory() ;
		
			properties.clear();
			
		}
		catch(Throwable ex) {
			System.err.println("SessionFactory initialization failed: " + ex);
            throw new ExceptionInInitializerError(ex);
		}
	}
	
	/**
	 * Provides the global session factory instance
	 * @return sessionFactory {@link SessionFactory} 
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Closes the SessionFactory.s
	 */
	public static void shutdown() {
		if(sessionFactory != null) 
			sessionFactory.close();
	}
}
