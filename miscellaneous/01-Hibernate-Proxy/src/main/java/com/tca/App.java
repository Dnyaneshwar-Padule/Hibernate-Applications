package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

/**
 * 
 * When we call session.load(), it gives us a proxy object of our entity class
 * This proxy object is a subclass of our entity class, it is created by hibernate at runtime
 * 
 *  It overrides getters, so that hibernate can perform lazy loading
 *  (lazy loading - Fire SELECT SQL, when getter/setter is called first time )
 *  
 *  - This application shows us that session.load() method does not fire SQL on calling it, 
 *    and it does not return us plain entity class
 *    
 *    
 *    To see proper working, we need to give VM argument  
    --add-opens java.base/java.lang=ALL-UNNAMED

 *   
 * 
 */
public class App {
    public static void main(String[] args) {
       
    	Configuration configuration = null;
    	SessionFactory sessionFactory = null;
    	Session session = null;
    	
    	try {
    		
    		configuration = new Configuration();
    		configuration.configure();
    		sessionFactory = configuration.buildSessionFactory();
    		session = sessionFactory.openSession();
    	
    
    		// fetched a record using load()
    		Student s = session.load(Student.class, 35); // At this time, proxy object is created and returned
    		
    		System.out.println("Load method called.....");
    		System.out.println("Student object returned by hibernaste : " + s.getClass().getName()); //class name =  com.tca.entities.Student$HibernateProxy
    		
    		System.out.println("Calling getters...");
    		System.out.println("Roll Number : " + s.getRno());
    		System.out.println("Name : " + s.getName()); // At this point, the SELECT SQL is sent to the database
    		System.out.println("Percentage : " + s.getPer());
    	
    		
    	}
    	catch (Exception e) {
			e.printStackTrace();
    	}
    	finally {
			if( session != null) session.close();
			if(sessionFactory != null) sessionFactory.close();
		}
    	
    }
}

/**

Output

2025-09-01 12:52:09 INFO  Version:44 - HHH000412: Hibernate ORM core version 6.6.28.Final
2025-09-01 12:52:09 INFO  RegionFactoryInitiator:50 - HHH000026: Second-level cache disabled
2025-09-01 12:52:10 WARN  pooling:86 - HHH10001002: Using built-in connection pool (not intended for production use)
2025-09-01 12:52:10 WARN  deprecation:153 - HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-09-01 12:52:10 INFO  pooling:163 - HHH10001005: Database info:
	Database JDBC URL [jdbc:postgresql://localhost:5432/hibernate]
	Database driver: org.postgresql.Driver
	Database version: 15.4
	Autocommit mode: false
	Isolation level: undefined/unknown
	Minimum pool size: 1
	Maximum pool size: 20
2025-09-01 12:52:11 INFO  JtaPlatformInitiator:59 - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Load method called.....
Student object returned by hibernaste : com.tca.entities.Student$HibernateProxy
Calling getters...
Roll Number : 35
2025-09-01 12:52:11 DEBUG SQL:135 - select s1_0.rno,s1_0.name,s1_0.per from student s1_0 where s1_0.rno=?
Hibernate: select s1_0.rno,s1_0.name,s1_0.per from student s1_0 where s1_0.rno=?
Name : Aakash
Percentage : 89.52


You can see the class name ,of the student object returned by hibernate
Student object returned by hibernaste : com.tca.entities.Student$HibernateProxy

and also the, SELECT SQL is fired when we tried to access fields using getters
SQL didn't fired when we called getter for roll number (primark key)
SQL fires, when we called other getters, like name, percentage

 */

