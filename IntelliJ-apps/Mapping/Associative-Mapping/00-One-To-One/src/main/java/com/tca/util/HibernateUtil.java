package com.tca.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static{
        try{
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static  SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }

    public static void shutdown(){
        if(SESSION_FACTORY != null)
                SESSION_FACTORY.close();
    }
}
