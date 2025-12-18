package com.jpa.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
//import javax.persistence.*;

@Entity
@Table(name="inh_joined")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehical{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int vid;
	
	@Column(name="vehicle_name")
	String vehicle_name;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVehicle_name() {
		return vehicle_name;
	}

	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	
	
}