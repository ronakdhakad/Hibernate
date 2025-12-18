package com.jpa.entity;

@Entity
@Table(name="inheritance_joined")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle{	
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