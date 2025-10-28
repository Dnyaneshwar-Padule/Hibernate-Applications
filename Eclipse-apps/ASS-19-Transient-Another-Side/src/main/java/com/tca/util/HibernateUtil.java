package com.tca.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;
	
	static {
		try (FileInputStream fis = new FileInputStream("src/main/resources/hibernate.properties")){
			
			Properties properties = new Properties();
			properties.load(fis);
			
			Configuration configuration = new Configuration(); 
			configuration.setProperties(properties);
			configuration.addAnnotatedClass(Student.class);
			
			SESSION_FACTORY = configuration.buildSessionFactory();
			
			properties.clear();
		}
		catch(Exception e) {
			throw new ExceptionInInitializerError("Unable to built sessionFactory : "  + e.getMessage());
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
