package com.jpa.entity;

import jakarta.persistence.GenerationType;
import jakarta.persistence.InheritanceType;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Id;

@Entity
@Table(name="inherit_single_table")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Vehical_type",discriminatorType=DiscriminatorType.STRING)
public class Vehical{

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	int vid;
	
	@Column(name="Vehical_Name")
	String vehicalName;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVehicalName() {
		return vehicalName;
	}

	public void setVehicalName(String vehicalName) {
		this.vehicalName = vehicalName;
	}
	
	
}
