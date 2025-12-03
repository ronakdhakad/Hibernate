import org.hibernate.Session;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExists();
		
		User user =new User();
		user.setUsername("Andrew anderson");
		user.setEmail("andrew325@gmail.com");
		user.setPassword("andrew@123");
		user.setAddress("Lig, Indore,M.P.");
		
		Session session =Utils.getS	essionFactory().openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.persist(user);
			session.flush();
			tx.commit();
			System.out.println("Commited!");
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback.!");
			}
		}
		
	}
		public static void createDatabaseIfNotExists() {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/";
			String username="root";
			String password="root@123";
			String database="hiber_4";
			
			try {
				Class.forName(driver);
				Connection con =DriverManager.getConnection(url,username, password);
				String query ="create database if not exists "+database;
				Statement smt = con.createStatement();
				smt.execute(query);
			}catch(Exception e) {
				System.out.println("Exception: "+e);
			}
		}
}