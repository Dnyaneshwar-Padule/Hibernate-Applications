package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;


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
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			
			//Query -->  select * from student where per = (select max(per) from student);
			// Here we can see nested queries 
			// the inner query gives max(per) from student table, which has return type of Double
			// and the outer query selects all records with per matching with percentage returned by inner query
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
			Root<Student> subRoot = subquery.from(Student.class);
			
			subquery.select( criteriaBuilder.max(subRoot.get("per")) ); // inner query
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.equal(root.get("per"), subquery) );
			
			List<Student> students = session.createQuery(criteriaQuery).list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			*/
			
			
			
			
			
			/* Query --> 
				select * from student where per = (
					select max(per) from student where per != ( 
						select max(per) from student
					)
				);
			*/
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Subquery<Double> innerSubquery = criteriaQuery.subquery(Double.class);
			Root<Student> innerRoot = innerSubquery.from(Student.class);
			innerSubquery.select( criteriaBuilder.max(innerRoot.get("per")) );  // select max(per) from student
			
			Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
			Root<Student> subRoot  = subquery.from(Student.class);
			subquery.select( criteriaBuilder.max(subRoot.get("per")) )
			.where( criteriaBuilder.notEqual(subRoot.get("per"), innerSubquery) ); // select max(per) from student where per != ( select max(per) from student )
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.equal(root.get("per"), subquery) );
			
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
			 Query --> 
			 	select * from student where per >= (
			 		select avg(per) from student
			 	);
			 */
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
			Root<Student> subRoot = subquery.from(Student.class);
			subquery.select( criteriaBuilder.avg(subRoot.get("per")) );
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.ge(root.get("per"), subquery) );
			
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
			 Query -->
			 	select * from student s where per = (
    					select min(s2.per) from student s2
    					where s2.city = s.city
    				);

			 */
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
			Root<Student> subRoot = subquery.from(Student.class);
			subquery.select( criteriaBuilder.min(subRoot.get("per")) )
			.where( criteriaBuilder.equal(subRoot.get("city"), root.get("city")) );
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.equal(root.get("per"), subquery) );
			
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
			 Question -->
			 		Get Top 3 Students by percentage
			*/
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			
			//select distinct per from Student order by per desc limit 3;
			CriteriaQuery<Double> cqPer = criteriaBuilder.createQuery(Double.class);
			Root<Student> rootPer = cqPer.from(Student.class);
			
			cqPer.select( rootPer.get("per") ).distinct(true)
			.orderBy( criteriaBuilder.desc(rootPer.get("per")) );
			
			List<Double> percentages  =  session.createQuery(cqPer)
												.setMaxResults(3)
												.getResultList();
			
			if( percentages.isEmpty() ) {
				System.out.println("No records found !");
				transaction.commit();
				return;
			}
			
			//select * from student where per in (percentages) order by per desc
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			criteriaQuery.select(root)
			.where( root.get("per").in(percentages) ) 
			.orderBy( criteriaBuilder.desc(root.get("per")));
			
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