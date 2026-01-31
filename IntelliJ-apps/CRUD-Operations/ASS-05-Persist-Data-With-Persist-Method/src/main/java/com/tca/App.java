package com.tca;

import com.tca.entities.Employee;
import com.tca.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * This Hibernate application persists (stores) Employee record in the table
 * with {@code session.persist()} method.
 *
 * <p>
 *     The {@code session.persist()} method returns void,
 *     unlike session.save() method returns, the primary key of inserted record.
 *     But it inserts the record reliably in the database table,
 *     if there is an problem while insertion,
 *     it throws an exception while {@code transaction.commit()}
 * </p>
 */
public class App {

    /**
     * Application entry point
     * @param args command line arguments, not used here
     */
    public static void main(String[] args) {

        /*
            Session object is used to perform database operations
         */
        Session session = null;

        /*
            Transaction object is necessary, when we fire DML queries
            (or when we perform save(), update(), delete() operations)
            although, it should also be used with DQL queries (get() method)
         */
        Transaction transaction = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            //Employee object to be saved in the database table
            Employee employee = new Employee();
            employee.setId(101);
            employee.setName("AAA");
            employee.setSalary(200000.0);

            session.persist(employee);

            transaction.commit(); // Reflect changes to the database

            System.out.println("Record is saved successfully !");
        }
        catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        finally {

            //close session
            if( session != null )
                session.close();

            //close SessionFactory
            HibernateUtil.shutdown();
        }

    }
}
