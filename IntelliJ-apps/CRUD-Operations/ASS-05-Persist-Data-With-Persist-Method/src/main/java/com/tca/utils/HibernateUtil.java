package com.tca.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class to manage SessionFactory
 * <p>
 *     SessionFactory should be built only once in the application,
 *     while startup. This class builds SessionFactory in it's static block.
 * </p>
 */
public class HibernateUtil {

    /**
     * SessionFactory instance which holds global session factory instance
     * in the entire application
     */
    public static final SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Session");
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the global instance of SessionFactory
     * @return sessionFactory {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /**
     * closes the SessionFactory , when application is about to shutdown
     */
    public static void shutdown(){
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
