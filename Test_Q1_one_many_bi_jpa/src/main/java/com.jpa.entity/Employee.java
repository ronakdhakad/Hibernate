package com.jpa.entity;

@Entity
@Table(name="emp_one_many_bi")
public class Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eid")
	int eid;
	
	@Column(name="empname")
	String empname;

	@ManyToOne
	@JoinColumn(name="dept_id")
	Department department;
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
}