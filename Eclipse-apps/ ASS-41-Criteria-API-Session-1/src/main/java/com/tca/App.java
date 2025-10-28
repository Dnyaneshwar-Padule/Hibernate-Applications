package com.tca;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


/*
 
 	The need :
 		Hibernate provides us HQL, which is object-oriented query language,
 		and HQL is database independent (mostly)
 		but there are some tasks, which HQL can't do it, so we have to use native sql
 		but again native sql's are database dependent, if in future we need to migrate database, 
 		then the existing native SQL may not work (mostly DML queries, function/procedures and aggregate functions) 
 
 		So, to solve these issues we use criteria API, 
 		with the help of Criterai API, we can create any kind of query, and that query will be database independent
 
 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			
			// Select * from student
			
			/*
			
			// To build a query using the Criteria API, we first need a CriteriaBuilder.
			// The CriteriaBuilder provides factory methods for creating SQL-like clauses 
			// such as SELECT, WHERE, ORDER BY, GROUP BY, etc.
			// Think of it as a factory that gives us the tools to build queries.
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			// This represents our actual query structure, created using the CriteriaBuilder.
			// The parameter (Student.class) specifies the type of result this query will return.
			CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);

			// The Root represents the entity (or table) on which the query will operate.
			// It is used to access entity attributes (columns) in the query.
			Root<Student> root = query.from(Student.class);
			
			query.select(root);
			
			Query<Student> q = session.createQuery(query);
			List<Student> students = q.list();
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}

			*/
			
			
			
			//Select specific columns
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> q = criteriaBuilder.createQuery(Tuple.class);  // if we are selecting specific columns and there is no extra column added, at this time use Tuple 
			Root<Student> root = q.from(Student.class);
			
			// To get specific columns use multiselect() method, with selection column names
			q.multiselect(
					root.get("rno").alias("stud_rno"),
					root.get("name").alias("stud_name"), 
					root.get("per").alias("stud_per"),
					root.get("city").alias("city")
					);

			Query<Tuple> query = session.createQuery(q);
			List<Tuple> students = query.list();
			
			for(Tuple student : students) {
				System.out.println("Name : " + student.get("stud_name")); // to fetch result from tuple, use alias, not actual column name
				System.out.println("Rno  : " + student.get("stud_rno"));
				System.out.println("Per  : " + student.get("stud_per"));
				System.out.println("City : " + student.get("city"));
				System.out.println("--------------------------------------------");
			}
			
			*/
			
			
			
			
			// Order By 
			
			/*
			//Query -> SELECT * FROM student ORDER BY per;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			criteriaQuery.select(root)
			.orderBy(criteriaBuilder.asc(root.get("per")));
			
			List<Student> students = session.createQuery(criteriaQuery).list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			*/
			
			/*
			//Query -> SELECT * FROM student ORDER BY per desc;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			criteriaQuery.select(root)
			.orderBy( criteriaBuilder.desc(root.get("per")) );
			
			List<Student> students = session.createQuery(criteriaQuery).list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			
			*/
			
			
			
			
			//WHERE
			
			/*
			//Query -> SELECT * FROM Student WHERE per >= 40 order by rno;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			criteriaQuery.select(root)
			.where(criteriaBuilder.ge(root.get("per"), 40.0))
			.orderBy( criteriaBuilder.asc(root.get("rno")) );
			
			List<Student> students = session.createQuery(criteriaQuery).list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			*/
			
			
			//Query --> SELECT * FROM Student WHERE per >= 75 and per <= 100 order by rno;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Predicate firstCondition = criteriaBuilder.ge(root.get("per"), 75.0);
			Predicate secondCondition = criteriaBuilder.le(root.get("per"), 100.0);
			
			Order cityAscending = criteriaBuilder.asc(root.get("city"));
			Order perDescending = criteriaBuilder.desc(root.get("per"));
			
			List<Order> resultOrder = new ArrayList<Order>();
			resultOrder.add(cityAscending);
			resultOrder.add(perDescending);
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.and(firstCondition,secondCondition) )
			.orderBy( resultOrder );

			List<Student> students = session.createQuery(criteriaQuery).list();
			
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