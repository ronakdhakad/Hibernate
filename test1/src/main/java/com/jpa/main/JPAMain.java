package com.jpa.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import com.jpa.model.*;
import com.jpa.model.Employee;

public class JPAMain {

    public static void main(String[] args) {

        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mypersistenceunit");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Department dept = new Department();
            dept.setDeptName("CS");

            Employee e1 = new Employee();
            e1.setEmpname("Andrew Anderson");

            Employee e2 = new Employee();
            e2.setEmpname("Peter Parker");

            dept.addEmployee(e1);
            dept.addEmployee(e2);

            em.persist(dept);

            tx.commit();
            System.out.println("Data inserted successfully");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                System.out.println("Rollback happened");
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
