package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;
import com.tca.entities.Teacher;

/**
 * Hibernate application with two databases 
 * PostgreSQL database and MySQL databases
 */
public class App {
    
	/**
	 * main method 
	 * @param args String[]
	 */
	public static void main(String[] args) {
    	
		Configuration postgreSQLConfiguration = null;
		SessionFactory postgreSQLSessionFactory = null;
		Session postgreSQLSession = null;
		Transaction postgreSQLTransaction = null;
		
		Configuration mySQLConfiguration = null;
		SessionFactory mySQLSessionFactory = null;
		Session mySQLSession = null;
		Transaction mySQLTransaction = null;
		
		try {

			postgreSQLConfiguration = new Configuration();
			postgreSQLConfiguration.configure("PostgreSQL.cfg.xml");
			postgreSQLSessionFactory = postgreSQLConfiguration.buildSessionFactory();
			postgreSQLSession = postgreSQLSessionFactory.openSession();
			postgreSQLTransaction = postgreSQLSession.beginTransaction();
			
			
			mySQLConfiguration = new Configuration();
			mySQLConfiguration.configure("MySQL.cfg.xml");			
			mySQLSessionFactory = mySQLConfiguration.buildSessionFactory();
			mySQLSession = mySQLSessionFactory.openSession();
			mySQLTransaction = mySQLSession.beginTransaction();
			
			Student student1 = new Student();
			student1.setRno(27);
			student1.setName("AAA");
			student1.setPer(80.0);
			
			Student student2 = new Student();
			student2.setRno(28);
			student2.setName("BBB");
			student2.setPer(70.0);
			
			Teacher teacher1 = new Teacher();
			teacher1.setTno(101);
			teacher1.setName("PPP");
			teacher1.setSalary(60000.0);
			
			Teacher teacher2 = new Teacher();
			teacher2.setTno(102);
			teacher2.setName("QQQ");
			teacher2.setSalary(55000.0);
			
			postgreSQLSession.save(student1);
			postgreSQLSession.save(teacher1);
			
			mySQLSession.save(student2);
			mySQLSession.save(teacher2);
			
			postgreSQLTransaction.commit();
			mySQLTransaction.commit();
			
			System.out.println("Records are saved successfully !");
			
		}
		catch(Exception e) {
			if( postgreSQLTransaction != null ) postgreSQLTransaction.rollback();
			if( mySQLTransaction != null ) mySQLTransaction.rollback();
			e.printStackTrace();
		}
		finally {
			if( postgreSQLSession != null ) postgreSQLSession.close();
			if( mySQLSession != null ) mySQLSession.close();
			
			if(postgreSQLSessionFactory != null ) postgreSQLSessionFactory.close();
			if( mySQLSessionFactory != null) mySQLSessionFactory.close();
		}

    }
}

/*
 * Output :-
 * 
log4j:WARN No appenders could be found for logger (org.hibernate.type.BasicTypeRegistry).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Hibernate: insert into student (NAME, PER, RNO) values (?, ?, ?)
Hibernate: insert into teacher (NAME, SALARY, TNO) values (?, ?, ?)
Hibernate: insert into student (NAME, PER, RNO) values (?, ?, ?)
Hibernate: insert into teacher (NAME, SALARY, TNO) values (?, ?, ?)
Records are saved successfully !

 * 
 */
