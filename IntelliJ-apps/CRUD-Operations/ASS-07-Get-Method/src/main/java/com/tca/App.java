package com.tca;

import com.tca.entities.Employee;
import com.tca.utils.HibernateUtil;
import org.hibernate.Session;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();

            Integer tno = 101;
            Employee employee = (Employee)session.get(Employee.class, tno);

            if(employee != null)
                System.out.println(employee.toString());
            else
                System.out.println("Record not found !");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session != null) session.close();
            HibernateUtil.shutdown();
        }
    }
}
