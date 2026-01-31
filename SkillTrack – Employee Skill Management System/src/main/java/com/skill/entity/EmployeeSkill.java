package com.skill.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_skills",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","skill_id"}))
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(optional=false)
    @JoinColumn(name="skill_id")
    private Skill skill;

    @Column(nullable=false)
    private int proficiency; 

    @Column(nullable=false)
    private double years; 

    public EmployeeSkill() {}

    public EmployeeSkill(Employee employee, Skill skill, int proficiency, double years) {
        this.employee = employee;
        this.skill = skill;
        this.proficiency = proficiency;
        this.years = years;
    }

    public Long getId() { return id; }
    public Employee getEmployee() { return employee; }
    public Skill getSkill() { return skill; }
    public int getProficiency() { return proficiency; }
    public double getYears() { return years; }

    public void setProficiency(int proficiency) { this.proficiency = proficiency; }
    public void setYears(double years) { this.years = years; }
}
