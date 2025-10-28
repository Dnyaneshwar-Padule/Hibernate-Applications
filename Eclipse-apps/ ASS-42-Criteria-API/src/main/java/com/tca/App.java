package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

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

			/*
		
			//Named Parameters
			//Query --> SELECT * FROM Student Where city like ? and per >= ?
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			ParameterExpression<String> cityParameter = criteriaBuilder.parameter(String.class, "tempCity" ); // tempCity is the name of the parameter
			ParameterExpression<Double> percentageParameter = criteriaBuilder.parameter(Double.class, "percentage");  // percentage is name of the parameter 
			
			Predicate cityCondition =  criteriaBuilder.like(root.get("city"), cityParameter); // city like ?
			Predicate percentageCondition = criteriaBuilder.ge(root.get("per"), percentageParameter); // per >= ?
			Predicate condition = criteriaBuilder.and(cityCondition, percentageCondition); // city like ? and per >= ?
			
			criteriaQuery.select(root)
			.where(condition);
			
			Query<Student> query = session.createQuery(criteriaQuery);
			
			System.out.print("Enter city : ");
			String city = br.readLine();

			System.out.print("Enter percentage : ");
			Double percentage = Double.parseDouble(br.readLine());
			
			query.setParameter("tempCity", city);
			query.setParameter("percentage", percentage);
			
			List<Student> students = query.list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
		
		*/
			
			
			// DISTINCT
			
			//Query --> SELECT distinct city from Student;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			criteriaQuery.multiselect( root.get("city").alias("city") )
			.distinct(true);
			
			List<Tuple>  records = session.createQuery(criteriaQuery).getResultList();
			
			for(Tuple record : records) {
				System.out.println(record.get("city"));
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