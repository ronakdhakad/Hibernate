package com.jpa.main;

import java.util.List;

import com.jpa.entity.Course;
import com.jpa.entity.Students;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAMain{
	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypunit");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
			Students s1 = new Students();
			s1.setStudname("Andrew Anderson");
			
			Students s2 = new Students();
			s2.setStudname("Peter Parker");
			
			Course c1 = new Course();
			c1.setCoursename("C Programming Language");
			
			Course c2 = new Course();
			c2.setCoursename("Java Programming Language");
			
			s1.addCourse(c1);
			s1.addCourse(c2);
			
			s2.addCourse(c1);
			try {	
				em.persist(s1);
				em.persist(s2);
				em.persist(c1);
				em.persist(c2);
				
				tx.commit();
				
				Course course = em.find(Course.class, 1);
				List<Students> students = course.getStudents();
				for(Students stud : students)
					System.out.println("Student Name : "+stud.getStudname());
				
			}catch(Exception e) {
				if(tx!=null)
					tx.rollback();
				System.out.println("Rollback takes place..!!");
			}
			em.close();
		emf.close();
	}
}