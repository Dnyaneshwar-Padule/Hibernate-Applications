package com.tca;

import com.tca.entity.Certificate;
import com.tca.entity.Student;
import com.tca.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Main {
    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Student student = new Student();
            student.setName("Aman");
            student.setPer(90.90);
            student.setRno(1);

            Certificate certificate = new Certificate();
            certificate.setCno(1);
            certificate.setTitle("Hibernate (ORM) Framework");
            certificate.setStudent(student);

            student.setCertificate(certificate);

            session.persist(student);

            transaction.commit();
            System.out.println("Done.");
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null)
                session.close();
            HibernateUtil.shutdown();
        }

    }
}