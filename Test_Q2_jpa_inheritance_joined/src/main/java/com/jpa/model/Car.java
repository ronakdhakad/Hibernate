package com.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Car extends Vehical{
	
	@Column(name="number_of_doors")
	int doors;

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

}