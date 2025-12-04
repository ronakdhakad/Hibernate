package com.hibernate.utils;
import org.hibernate.SessionFactory;
import com.hibernate.model.*;
import org.hibernate.cfg.Configuration;
public class Utils{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(User.class);
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("SessionFactory: "+sessionFactory);
		}catch(Exception e) {
			System.out.println("Exception: "+e);
		}
		return sessionFactory;
	}
}