package com.tca;

import com.tca.entities.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;

        try{

            //load configurations by parsing hibernate.cfg.xml file
            configuration = new Configuration();
            configuration.configure();

            //create session factory using configuration details
            sessionFactory = configuration.buildSessionFactory();

            //open session for database operations
            session = sessionFactory.openSession();

            //begin transaction
            transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setId(101);
            employee.setName("Ramesh");
            employee.setSalary(100000.0);

            //Save above employee record
            session.save(employee);

            //commit transaction
            transaction.commit();

            System.out.println("Record is saved successfully !");

        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        finally {
            try{
                session.close();
                sessionFactory.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
