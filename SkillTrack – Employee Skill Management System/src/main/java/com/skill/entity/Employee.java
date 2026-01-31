package com.skill.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80)
    private String name;

    @Column(nullable=false, unique=true, length=120)
    private String email;

    @Column(nullable=false, length=60)
    private String department;

    public Employee() {}

    public Employee(String name, String email, String department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Email: " + email +
                " | Dept: " + department;
    }
}
