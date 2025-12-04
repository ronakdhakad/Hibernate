package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
import utils.HibernateUtils;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		User user = new User();
		user.setUsername("SimonAgain");
		user.setEmail("simonAgain@gmail.com");
		user.setPassword("simonAgain@123");
		user.setAddress("Indore, Madhya Pradesh");
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(user);
//			if(true)
//				throw new RuntimeException("Exception occured");
//			session.flush();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) {
				System.out.println("Rollback occurs");
				tx.rollback();
			}
		}
	}
	public static void createDatabaseIfNotExist() {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/";
		String USER = "root";
		String PASS = "root@123";
		String DATABASE = "hiber_2";
		
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = con.createStatement();
			String query = "create database if not exists "+DATABASE;
			stmt.execute(query);
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception : "+e);
		}
	}
}