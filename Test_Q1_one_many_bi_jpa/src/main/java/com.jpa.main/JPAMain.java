package com.jpa.main;

public class JPAMain{
	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypunit");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		
		Department dept = new Department();
		dept.setDeptName("Computer Science");
		
		Employee e1 = new Employee();
		e1.setEmpname("sohan tirole");
		
		Employee e2 = new Employee();
		e2.setEmpname("anmol");
		
		dept.getEmployee().add(e1);
		dept.getEmployee().add(e2);
	
		try {
			
			em.persist(dept);
			em.persist(e1);
			em.persist(e2);
			
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
		}
	
	}
	}

}