
// aggregate functionality
package com.jpa.main;
import java.util.List;
import com.jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JPAMain{
	public static void main(String args[]){
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mypunit");
	 	EntityManager em = emf.createEntityManager();
	 	EntityTransaction tx = null;
	 	try {
		 	tx = em.getTransaction();
		 	tx.begin();

		 	CriteriaBuilder criteriaBuilder =  em.getCriteriaBuilder();
		 	
		 	CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		 	Root<User> root = query.from(User.class);
		 	query.select(criteriaBuilder.sum(root.get("salary").as(Long.class)));

		 	TypedQuery<Long> q = em.createQuery(query);
		 	Long sumSalary = q.getSingleResult();

		 	System.out.println("Sum salary : " + sumSalary);	 
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
