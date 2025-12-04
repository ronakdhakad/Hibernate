package com.hibernate.main;
import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;

public class HibernateMain{
	public static void main(String args[]) {
		Scanner sc =new Scanner(System.in);
		createDatabaseIfNotExist();
		User user =new User();
		
		System.out.println("Enter user name...");
		String name=sc.nextLine();
		System.out.println("Enter emial...");
		String email=sc.nextLine();
		System.out.println("Enter password...");
		String pass=sc.nextLine();
		System.out.println("Enter address...");
		String addrs=sc.nextLine();
		
		user.setUsername(name);
		user.setPassword(pass);
		user.setEmail(email);
		user.setAddress(addrs);
		
		Session session =HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try {
			tx =session.beginTransaction();
			session.persist(user);
			tx.commit();
			System.out.println("Transaction commited...");
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback occurs");
			}
		}
	}
	public static void createDatabaseIfNotExist() {
		String DRIVER="com.mysql.cj.jdbc.Driver";
		String URL="jdbc:mysql://localhost:3306/";
		String USER="root";
		String PASS="root@123";
		String DATABASE="hiber_3";
		
		try {
			Class.forName(DRIVER);
			Connection con =DriverManager.getConnection(URL,USER,PASS);
			String query="create database if not exists "+DATABASE;
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception: "+e);
		}
	}
}