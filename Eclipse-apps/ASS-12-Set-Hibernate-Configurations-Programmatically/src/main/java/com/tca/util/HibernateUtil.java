package com.tca.util;

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
		try {
		
			Configuration configuration= new Configuration();
			
			//Set Database properties
			configuration.setProperty("hibernate.connection.driver_class" , "org.postgresql.Driver" );
			configuration.setProperty("hibernate.connection.url" , "jdbc:postgresql://localhost:5432/hibernate" );
			configuration.setProperty("hibernate.connection.user" , "dnyaneshwar" );
			configuration.setProperty("hibernate.connection.password" , "root@3121" );
			
			//Hibernate properties
			configuration.setProperty("hibernate.dialect" , "org.hibernate.dialect.PostgreSQLDialect");
			configuration.setProperty("hibernate.show_sql" , "true");
			configuration.setProperty("hibernate.format_sql" , "false");
			configuration.setProperty("hibernate.hbm2ddl.auto" , "create");
			
			configuration.addResource("student.hbm.xml");
			
			sessionFactory = configuration.buildSessionFactory() ;
		
			
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
