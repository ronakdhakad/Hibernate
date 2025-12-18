package com.jpa.entity;

@Entity
@Table(name="department_one_many")
public class Department{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int did;
	
	@Column(name="deptName")
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