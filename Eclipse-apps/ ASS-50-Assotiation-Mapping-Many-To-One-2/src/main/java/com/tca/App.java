package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tca.entities.Department;
import com.tca.utils.HibernateUtil;


/*
 	When we get the record of the customer the orders don't come until we call a getter for it, because of lazy loading
 	but if we get a order, the customer will come with it, because it has eager loading
 	
 	For one order there is only one Customer, so we can get it with the order
 	
 	but for one customer, there will be many orders, fetching orders with customer, will increase space and time 
 	so get orders only if needed
 	
 */

public class App {
   
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		
		try (BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) )){
			//AddRecords.main(null); // adding records in the table
		
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			
			
			// Adding Department and employees
			/*
			  If we are adding a new department and a new employees
			  and we first created a department , and then created few employee objects
			  then assigned those employees to the department by department.setEmployees()
			  but we don't set department to those employees 
			  i.e. not calling employee.setDepartment()
			  and we only give department object to persist method, then
			  the department will be added into the table as well as the employees will also be added to employee table
			  but the department_id in employee table will remain null, due to not setting department for employee object
			  
			 */
			/*
			Department d = new Department();
			d.setId(111);
			d.setName("CS");
			
			Employee e = new Employee();
			e.setEid(101);
			e.setName("Ram");
			e.setSalary(65000.0);
			e.setDepartment(d);   // if we don't set it, it will be null
			
			Employee e2 = new Employee();
			e2.setEid(102);
			e2.setName("Rohit");
			e2.setSalary(55000.0);
			e2.setDepartment(d);
			
			d.setEmployees(Arrays.asList(e,e2));
			
			session.persist(d);
			*/
			
			
			
			// Adding only department
			/*
			Department d = new Department();
			d.setId(222);
			d.setName("AI");
			session.persist(d);
			*/
			
			
			
			// Adding employee, without setting the department
			// IT is possible, if we have set nullable  = true, or don't used nullabe for dept_id in employee class
			/*
			Employee e = new Employee();
			e.setEid(103);
			e.setName("Roshan");
			e.setSalary(75000.0);
			session.persist(e);
			*/
			
			
			
			// Assigning department to an employee
			/*
			Department d = session.get(Department.class, 222);
			Employee e = session.get(Employee.class, 103);
			e.setDepartment(d);
			 */

			//delete a department
			Department d = session.get(Department.class,222);
			session.delete(d);
			
			
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