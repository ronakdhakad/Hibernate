package com.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Car extends Vehicle{
	
	@Column(name="no_of_doors")
	int doors;

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}
	
	
}
