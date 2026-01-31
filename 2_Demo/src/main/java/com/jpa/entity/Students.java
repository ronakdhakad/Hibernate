package com.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="stud_many_many_bi")
public class Students{ // owning side
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	int sid;
	
	@Column(name="studname")
	String studname;

	@ManyToMany
	@JoinTable(name="student_course",
			   joinColumns = @JoinColumn(name="stud_id"),
			   inverseJoinColumns = @JoinColumn(name="course_id")
	)
	List<Course> course = new ArrayList<Course>();

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getStudname() {
		return studname;
	}

	public void setStudname(String studname) {
		this.studname = studname;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}
	
	public void addCourse(Course course) {
		this.course.add(course);
		course.getStudents().add(this);
	}
	
	
}