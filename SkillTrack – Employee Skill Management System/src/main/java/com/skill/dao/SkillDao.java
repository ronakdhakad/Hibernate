package com.skill.dao;

import com.skill.config.JPAUtil;
import com.skill.entity.Skill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class SkillDao {
	
    public void add(Skill skill) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(skill);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    public List<Skill> findAll() {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        List<Skill> list = em.createQuery("from Skill s order by s.id", Skill.class)
                .getResultList();
        em.close();
        return list;
    }

    public Skill findById(Long id) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        Skill s = em.find(Skill.class, id);
        em.close();
        return s;
    }

    public boolean update(Long id, String newName) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Skill s = em.find(Skill.class, id);
        if (s == null) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
        s.setName(newName);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean delete(Long id) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Skill s = em.find(Skill.class, id);
        if (s == null) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
        em.remove(s);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
