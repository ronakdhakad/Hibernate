package com.jpa.entity;

@Entity
public class Car extends Vehicle{
	@Column(name="door_number")
	int doors;

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}
	
	
}