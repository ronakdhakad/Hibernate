package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception : "+e);
			System.out.println("sessionFactory null... ");
		}
		return sessionFactory;
	}
}