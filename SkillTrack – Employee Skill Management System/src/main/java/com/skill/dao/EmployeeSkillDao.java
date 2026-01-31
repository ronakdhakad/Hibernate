package com.skill.dao;

import com.skill.config.JPAUtil;
import com.skill.entity.Employee;
import com.skill.entity.EmployeeSkill;
import com.skill.entity.Skill;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EmployeeSkillDao {

    public boolean assignOrUpdate(Employee emp, Skill skill, int prof, double years) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        em.getTransaction().begin();

        Employee managedEmp = em.find(Employee.class, emp.getId());
        Skill managedSkill = em.find(Skill.class, skill.getId());

        if (managedEmp == null || managedSkill == null) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }

        List<EmployeeSkill> existing = em.createQuery(
                        "from EmployeeSkill es where es.employee.id=:eid and es.skill.id=:sid",
                        EmployeeSkill.class)
                .setParameter("eid", managedEmp.getId())
                .setParameter("sid", managedSkill.getId())
                .getResultList();

        if (existing.isEmpty()) {
            em.persist(new EmployeeSkill(managedEmp, managedSkill, prof, years));
        } else {
            EmployeeSkill es = existing.get(0);
            es.setProficiency(prof);
            es.setYears(years);
        }

        em.getTransaction().commit();
        em.close();
        return true;
    }

    public List<EmployeeSkill> skillsOfEmployee(Long empId) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        List<EmployeeSkill> list = em.createQuery(
                        "select es from EmployeeSkill es join fetch es.skill where es.employee.id=:id order by es.proficiency desc",
                        EmployeeSkill.class)
                .setParameter("id", empId)
                .getResultList();
        em.close();
        return list;
    }

    public List<Employee> searchEmployeesBySkillName(String skillLike) {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        List<Employee> list = em.createQuery(
                        "select distinct es.employee from EmployeeSkill es where lower(es.skill.name) like :q order by es.employee.id",
                        Employee.class)
                .setParameter("q", "%" + skillLike.toLowerCase() + "%")
                .getResultList();
        em.close();
        return list;
    }


    public List<Object[]> topSkills() {
        EntityManager em = JPAUtil.getFactory().createEntityManager();
        List<Object[]> rows = em.createQuery(
                        "select es.skill.name, count(distinct es.employee.id) " +
                                "from EmployeeSkill es group by es.skill.name order by count(distinct es.employee.id) desc",
                        Object[].class)
                .getResultList();
        em.close();
        return rows;
    }
}
