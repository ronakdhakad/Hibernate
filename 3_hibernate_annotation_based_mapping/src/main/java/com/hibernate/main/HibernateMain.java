package com.hibernate.main;
import org.hibernate.Session;
import com.hibernate.utils.Utils;
import com.hibernate.model.User;
import org.hibernate.Transaction;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
//import java.time.LocalDateTime;



public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExists();
		
		User user =new User();
		user.setName("Andrew anderson");
		user.setEmail("andrew325@gmail.com");
		user.setPassword("andrew@123");
		user.setAddress("Lig, Indore,M.P.");
		
		Session session =Utils.getSessionFactory().openSession();
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
		
		session = Utils.getSessionFactory().openSession();
		// read data
		try {
			List<User> list = session.createQuery("from User", User.class).getResultList();
			// here we are dealing with hibernate configuration thats why "from User" is considered as HQL
			System.out.println("######### User Data #########");
			for(User userObj : list) {
				System.out.println("\nUid : "+userObj.getUid());
				System.out.println("Username : "+userObj.getName());
				System.out.println("Email : "+userObj.getEmail());
				System.out.println("Password : "+userObj.getPassword());
//				System.out.println("CreatedAt : "+userObj.getCreatedat());
//				System.out.println("UpdatedAt : "+userObj.getUpdatedat());
			}
		}catch(Exception e) {
			System.out.println("Exception : "+e);
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