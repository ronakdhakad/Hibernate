public class Utils{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration = new Configuartion();
			configuration.addAnnotation(User.class);
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("SessionFactory: "+sessionFactory);
		}catch(Exception e) {
			System.out.println("Exception: "+e);
		}
	}
}