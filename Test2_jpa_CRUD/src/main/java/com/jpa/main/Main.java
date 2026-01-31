package com.jpa.main;

import com.jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
public class Main{
	public static void main(String args[]){
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mypunit");
	 	EntityManager em = emf.createEntityManager();
	 	EntityTransaction tx = null;
	 	try {
		 	tx = em.getTransaction();
		 	tx.begin();
		 	User user = new User();
	 		user.setUsername("mohit");
	 		user.setEmail("mohit@gmail.com");
	 		user.setPassword("1234");
	 		
	 		em.persist(user);
		 	String query = "update User set username=:username, password=:password where email=:email";
		 	Query q = em.createQuery(query);
		 	q.setParameter("username", "vaibhav");
		 	q.setParameter("password", "11111111");
		 	q.setParameter("email", "vaibhav@gmail.com");
		 	
		 	int affect= q.executeUpdate();
		 	System.out.println(affect);
		 	
		 	String query1 = "delete from User where email=:email";
		 	Query q1 = em.createQuery(query1);
		 	q1.setParameter("email", "vaib@gmail.com");
		 	int affect1= q1.executeUpdate();
		 	System.out.println(affect1);
		 	
		 	String query2 = "select u from User u";
		 	TypedQuery<User> q2 = em.createQuery(query2,User.class);
		 	int affect2= q2.executeUpdate();
		 	System.out.println(affect2);
		 	
		 	tx.commit();
	 	}catch(Exception e) {
	 		if(tx!=null)
	 			tx.rollback();
	 		System.out.println("Exception : "+e);
	 	}
	 	em.close();
	 	emf.close();
	}
}