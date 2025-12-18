package com.jpa.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="dept_jpql")
public class Department{ 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int did;
	
	@Column(name="dept_name")
	String deptName;
	
	@OneToMany(mappedBy = "department")
	Set<Employee> employee = new HashSet<Employee>();

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	
	public void addDepartment(Employee e) {
		e.setDepartment(this);
		employee.add(e);
		this.setEmployee(employee);
    }
}