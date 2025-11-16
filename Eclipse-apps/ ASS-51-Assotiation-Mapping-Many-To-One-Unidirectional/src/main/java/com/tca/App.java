package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Department;
import com.tca.entities.Employee;
import com.tca.utils.HibernateUtil;


/*
 	In OneToMany Unidirectional,
 	the foreign key is created in the table where multiple records are mapped with one record
 	i.e.
 		Multiple employees can have one department, if we create foreign key in department table, 
 		then we can only assign that department to one employee, on adding new employee in same department
 		will give error, the error will be duplication of primary key, or department id
 		
 	So, in unidirectional mapping, hibernate created the foreign key in Employees column
 	so that, multiple employees can have same department
 	
 	
 	We can fetch employee by department, but can't fetch department by the employee
 	This use case is not ideal / logical
 	Employee - Department relationship should be bi-directional
 	
 	This example is only to demonstrate OneToMany Unidirectional relationship
 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			//AddRecords.main(null); // adding records in the table
		
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			
			
			// Add employees and departments
			/*
			Department d = new Department();
			d.setId(111);
			d.setName("CS");
			
			Employee e = new Employee();
			e.setEid(101);
			e.setName("Rakesh");
			e.setSalary(75000.0);
			
			d.setEmployees( Arrays.asList(e) );
			session.persist(d);
			*/
			
			// Getting employees by department
			Department d = session.get(Department.class, 111);
			List<Employee> l = d.getEmployees();
			
			for(Employee e: l)
					System.out.println(e.getEid() + " " + e.getName() + " " + e.getSalary());
			
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