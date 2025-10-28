package com.tca;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tca.entities.Student;
import com.tca.utils.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
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

			
			//LIKE
			
			/*
			//Query  --> SELECT * FROM Student WHERE city like 'P%';
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.like(root.get("city"), "P%") );
			
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
			//Query --> SELECT * FROM Student WHERE city like 'P%' or city like 'M%';
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Predicate firstCondition = criteriaBuilder.like(root.get("city"), "P%");
			Predicate secondCondition = criteriaBuilder.like(root.get("city"), "M%");
			
			criteriaQuery.select(root)
			.where( criteriaBuilder.or(firstCondition, secondCondition) );
			
			List<Student> students = session.createQuery(criteriaQuery).list();
			
			for(Student student : students) {
				System.out.println("Name : " + student.getRno());
				System.out.println("Rno  : " + student.getName());
				System.out.println("Per  : " + student.getPer());
				System.out.println("City : " + student.getCity());
				System.out.println("--------------------------------------------");
			}
			*/
			
			
			
			// GROUP BY
			
			/*
			//Query -> SELECT city, count(*) FROM Student GROUP BY city;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class); // Use Tuple only when there are column which are part of the table (like name,per,rno,city), count(*) is not part(column) of the Student table
			Root<Student> root = criteriaQuery.from(Student.class);
			
			//Expression is something which produces a single value as a result when executed
			// count() is expression becaz it gives a single value as count
			// and it returns Long (int) value
			Expression<Long> countExpr = criteriaBuilder.count(root); // count(*)
			
			criteriaQuery.multiselect(root.get("city"), countExpr )
			.groupBy(root.get("city"));
			
			List<Object[]> l = session.createQuery(criteriaQuery).getResultList();
			
			for( Object[] row : l ) {
				for(Object col : row) {
					System.out.print(col + " ");
				}
				System.out.println();
			}
			*/
			
			/*
			//Query --> SELECT city,count(*) FROM Student WHERE per >= 40 GROUP BY city;
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Expression<Long> countExpr = criteriaBuilder.count(root); 
			Predicate perCondition = criteriaBuilder.ge(root.get("per"), 40.0);
			
			criteriaQuery.multiselect(root.get("city"), countExpr)
			.where( perCondition )
			.groupBy( root.get("city") );
			
			List<Object[]> l = session.createQuery(criteriaQuery).getResultList();
			
			for( Object[] row : l ) {
				for(Object col : row) {
					System.out.print(col + " ");
				}
				System.out.println();
			}
			*/
			
			
			//Count()
			/*
			//Query --> SELECT count(*) from Student
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<Student> root = criteriaQuery.from(Student.class);
		
			criteriaQuery.multiselect( criteriaBuilder.count(root).alias("count") );
			Long totalStudents = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println("Total Students : " + totalStudents);
			*/
			
			
			// avg(per)
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Double avragePer = session.createQuery( criteriaQuery.multiselect( criteriaBuilder.avg(root.get("per")) ) )
					.getSingleResult();
		
			System.out.println("Average Percentage : " + avragePer);
			*/
			
			
			// max()
			/*
			//Query --> select city, max(per) from student group by city;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Expression<Double> maxDoubleExpr = criteriaBuilder.max(root.get("per"));
			
			criteriaQuery.multiselect( root.get("city"), maxDoubleExpr )
			.groupBy( root.get("city") );
			
			List<Object[]> l = session.createQuery( criteriaQuery ).getResultList();
			
			for(Object[] record : l) {
				for(Object field : record) {
					System.out.print(field + "  ");
				}
				System.out.println();
			}
			*/
			
			
			/*
			//Query --> select city, avg(per) as avg from student group by city order by avg desc limit 1;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Expression<Double> avgPerExpr = criteriaBuilder.avg(root.get("per"));
			Order avgDesc = criteriaBuilder.desc(avgPerExpr);
			
			criteriaQuery.multiselect(root.get("city"), avgPerExpr )
			.groupBy(root.get("city"))
			.orderBy( avgDesc );
			
			Query<Object[]> query = session.createQuery(criteriaQuery);
			query.setMaxResults(1);
			
			List<Object[]> l = query.getResultList();
			
			for(Object[] result : l) {
				for(Object field : result) {
					System.out.print(field + " ");
				}
				System.out.println();
			}
			*/
			
			
			
			
			
			//HAVING
			/*
			//Query --> SELECT city, count(*) FROM Student GROUP BY city HAVING count(*) >= 2;
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Student> root = criteriaQuery.from(Student.class);
			
			Expression<Long> countExpr = criteriaBuilder.count(root);
			Predicate countCondition = criteriaBuilder.ge(countExpr, 2);
			
			criteriaQuery.multiselect(root.get("city"), countExpr)
			.groupBy( root.get("city") )
			.having( countCondition )
			.orderBy( criteriaBuilder.asc(root.get("city")) );
			
			//List<Object[]> l = session.createQuery(criteriaQuery).setMaxResults(1).setFirstResult(1).getResultList();
			List<Object[]> l = session.createQuery(criteriaQuery).getResultList();
			
			for( Object[] record : l ) {
				for(Object field : record) {
					System.out.print(field + " ");
				}
				System.out.println();
			}
			*/
			
			
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