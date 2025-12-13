package com.hibernate.utils;

@Entity
@Table(name="annotation_table")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int uid;
	
	@column(name="userName")
	String userName;
	
	@column(name="MOB")
	int MOB;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getMOB() {
		return MOB;
	}

	public void setMOB(int mOB) {
		MOB = mOB;
	}
	
	
	
}
