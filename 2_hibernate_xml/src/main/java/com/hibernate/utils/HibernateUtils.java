package com.hibernate.utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory=null;
		
		try {
			Configuration configuration=new Configuration();
			sessionFactory =configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception: "+ e);
		}
		return sessionFactory;
		
	}
}