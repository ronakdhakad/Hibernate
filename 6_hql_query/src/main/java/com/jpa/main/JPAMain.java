package com.jpa.main;

import java.util.Scanner;

import com.jpa.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAMain{
	public static void main(String args[]){
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mypunit");
	 	EntityManager em = emf.createEntityManager();
	 	EntityTransaction tx = null;
	 	try {
		 	tx = em.getTransaction();
		 	tx.begin();
		 		Scanner sc = new Scanner(System.in);
		 		System.out.println("Enter Username : ");
		 		String username = sc.nextLine();
		 		
		 		System.out.println("Enter Email: ");
		 		String email = sc.nextLine();
		 		
		 		System.out.println("Enter Password: ");
		 		String password = sc.nextLine();
		 		
		 		System.out.println("Enter Address : ");
		 		String address = sc.nextLine();
		 		
		 		System.out.println("Enter Salary: ");
		 		int salary = sc.nextInt();
		 		
		 		User user = new User();
		 		user.setUsername(username);
		 		user.setEmail(email);
		 		user.setPassword(password);
		 		user.setAddress(address);
		 		user.setSalary(salary);
		 		
		 		em.persist(user);
		 		System.out.println("Student Added Successfully..!!");
		 		
		 	tx.commit();
	 	}catch(Exception e) {
	 		if(tx!=null)
	 			tx.rollback();
	 		System.out.println("Rollback takes place..!!");
	 		System.out.println("Exception : "+e);
	 	}
	 	em.close();
	 	emf.close();
	}
}