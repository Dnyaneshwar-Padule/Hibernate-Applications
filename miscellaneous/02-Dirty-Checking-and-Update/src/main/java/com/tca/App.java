package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

/**
 * When we fetch an record from database, hibernate keeps internal snapshot of that record (in first level cache)
 * i.e. it saves the values of every field of that record
 * Hibernate stores these snapshots, in a Map
 * 
 *  and before commit/ flush, it check the values of entity object's fields against the saved snapshot
 *  if there is an change between values, it fires an UPDATE SQL, 
 *  
 *  this compare-now vs. compare-then. no change → no update, is dirty checking mechanism
 * 
 */
public class App {
    public static void main(String[] args) {
       
    	Configuration configuration = null;
    	SessionFactory sessionFactory = null;
    	Session session = null;
    	Transaction transaction = null;
    	
    	try {
    		
    		configuration = new Configuration();
    		configuration.configure();
    		sessionFactory = configuration.buildSessionFactory();
    		session = sessionFactory.openSession();
    		transaction = session.beginTransaction();
    
    		// fetched a record by primark key
    		Student s = session.get(Student.class, 35); // hibernate returned an student object, and also saved it's snapshots
    		
    		/*
    		 *  // TO check the snapshot........  
    		 *
    	
    		// Cast session to Hibernate’s internal API
    		SessionImplementor sessionImpl = session.unwrap(SessionImplementor.class);
    		PersistenceContext pc = sessionImpl.getPersistenceContext();

    		EntityEntry entry = pc.getEntry(s);

    		Object[] loadedState = entry.getLoadedState();

    		System.out.println("Snapshot values:");
    		int i = 0;
    		for (Object val : loadedState) {
    		    System.out.println("Property " + i + " = " + val);
    		    i++;
    		}
    		
    		 */
    		
    		
    		s.setPer(85.0); // one field is changed
    		
    		transaction.commit(); // at this point, hibernate will check the field values of student record, with it's saved snapshot, if there is change it will fire an UPDATE SQL 
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

	### Output : Without changed field  ### 
2025-09-01 13:31:41 INFO  Version:44 - HHH000412: Hibernate ORM core version 6.6.28.Final
2025-09-01 13:31:41 INFO  RegionFactoryInitiator:50 - HHH000026: Second-level cache disabled
2025-09-01 13:31:41 WARN  pooling:86 - HHH10001002: Using built-in connection pool (not intended for production use)
2025-09-01 13:31:42 WARN  deprecation:153 - HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-09-01 13:31:42 INFO  pooling:163 - HHH10001005: Database info:
	Database JDBC URL [jdbc:postgresql://localhost:5432/hibernate]
	Database driver: org.postgresql.Driver
	Database version: 15.4
	Autocommit mode: false
	Isolation level: undefined/unknown
	Minimum pool size: 1
	Maximum pool size: 20
2025-09-01 13:31:43 INFO  JtaPlatformInitiator:59 - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-09-01 13:31:43 DEBUG SQL:135 - select s1_0.rno,s1_0.name,s1_0.per from student s1_0 where s1_0.rno=?
Hibernate: select s1_0.rno,s1_0.name,s1_0.per from student s1_0 where s1_0.rno=?

Only SELECT SQL is fired.............


	### Output : With changed field  ### 
2025-09-01 13:32:11 INFO  Version:44 - HHH000412: Hibernate ORM core version 6.6.28.Final
2025-09-01 13:32:11 INFO  RegionFactoryInitiator:50 - HHH000026: Second-level cache disabled
2025-09-01 13:32:11 WARN  pooling:86 - HHH10001002: Using built-in connection pool (not intended for production use)
2025-09-01 13:32:11 WARN  deprecation:153 - HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-09-01 13:32:11 INFO  pooling:163 - HHH10001005: Database info:
	Database JDBC URL [jdbc:postgresql://localhost:5432/hibernate]
	Database driver: org.postgresql.Driver
	Database version: 15.4
	Autocommit mode: false
	Isolation level: undefined/unknown
	Minimum pool size: 1
	Maximum pool size: 20
2025-09-01 13:32:12 INFO  JtaPlatformInitiator:59 - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-09-01 13:32:12 DEBUG SQL:135 - select s1_0.rno,s1_0.name,s1_0.per from student s1_0 where s1_0.rno=?
Hibernate: select s1_0.rno,s1_0.name,s1_0.per from student s1_0 where s1_0.rno=?
2025-09-01 13:32:12 DEBUG SQL:135 - update student set name=?,per=? where rno=?
Hibernate: update student set name=?,per=? where rno=?

UPDATE SQL is fired after SELECT SQL...
(Make sure you have changed the name, percentage)
 */

