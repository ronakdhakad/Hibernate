package com.skill.dao;

import com.skill.config.JPAUtil;
import com.skill.entity.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EmployeeDao {

    public void add(Employee e) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    public List<Employee> findAll() {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        List<Employee> list = em.createQuery("from Employee e order by e.id", Employee.class)
                .getResultList();
        em.close();
        return list;
    }

    public Employee findById(Long id) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        Employee e = em.find(Employee.class, id);
        em.close();
        return e;
    }

    public boolean update(Long id, String name, String email, String dept) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Employee e = em.find(Employee.class, id);
        if (e == null) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
        e.setName(name);
        e.setEmail(email);
        e.setDepartment(dept);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean delete(Long id) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Employee e = em.find(Employee.class, id);
        if (e == null) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
        em.remove(e);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
