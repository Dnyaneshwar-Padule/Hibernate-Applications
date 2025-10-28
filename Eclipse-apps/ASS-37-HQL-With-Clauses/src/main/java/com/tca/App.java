package com.tca;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;


public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			
			// Group By
			
			// City name and students count from that city
			//Query<Object[]> query = session.createQuery("SELECT city, count(*) FROM Student GROUP BY city",Object[].class);

			// City and count of passed students from that city 
			//Query<Object[]> query = session.createQuery("SELECT city, count(*) FROM Student WHERE per >= 40 GROUP BY city",Object[].class);
			
			// Cities from more than or 2 students are coming
			//Query<Object[]> query = session.createQuery("SELECT city, count(*) FROM Student GROUP BY city HAVING count(*) >= 2", Object[].class);

			/*
			List<Object[]> result = query.list();
			
			for(Object[] row : result) {
				for(Object value : row) {
					System.out.print(value + "  ");
				}
				System.out.println();
			}
			*/
			
			
			
			
			
			//WHERE CLAUSE
		
			// Students who has percentage between 70 and 80, 
			//Query<Student> query = session.createQuery("FROM Student WHERE per BETWEEN 60.0 AND 70.0", Student.class);
			
			
			// Students who are passed with Distinction
			//Query<Student> query = session.createQuery("FROM Student WHERE per >= 75", Student.class);
			
			//Students who are coming from Pune
			//Query<Student> query = session.createQuery("FROM Student WHERE city LIKE 'Pune'", Student.class);
			
			//Students whose name starts with 'A' or 'S'
			//Query<Student> query = session.createQuery("FROM Student WHERE name LIKE 'A%' or name LIKE 'S%' ", Student.class);
			
			//Students whose city name has only 4 letters
			//Query<Student> query = session.createQuery("FROM Student WHERE city LIKE '____'", Student.class);
			
			//city name starts with P
			//Query<Student> query = session.createQuery("FROM Student WHERE city LIKE 'P%'", Student.class);
			
			//Students who appeared for exam
			Query<Student> query = session.createQuery("FROM Student WHERE per IS NOT NULL", Student.class);
			
			
			List<Student> students = query.list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			
			
			
			
			transaction.commit();
			
				
		}
		catch(Exception e) {
			if (transaction != null ) transaction.rollback();
			e.printStackTrace();
		}
		finally {
			//Close database connection
			if(session != null ) session.close(); // close connection
			HibernateUtil.shutdown();
		}
		
    }
}



/*
 
 // 1. with 2 queries
//			NativeQuery<Object[]> query = session.createNativeQuery("SELECT city, count(*) as count FROM Student GROUP BY city HAVING count(*) = ( SELECT max(count) as count FROM ( SELECT count(*) as count FROM Student GROUP BY city ) )", Object[].class ).addScalar("city", StandardBasicTypes.STRING).addScalar("count", StandardBasicTypes.LONG);
			NativeQuery<Student> query = session.createNativeQuery("SELECT * FROM student", Student.class );
		
			
			List<Student> result = query.list();
 
 */
/*
			SELECT city, count(*) FROM Student GROUP BY city HAVING count(*) = 
					( SELECT max(count) as max_count FROM 	
								( SELECT count(*) as count FROM Student GROUP BY city )
					 )
			 
			 
 */