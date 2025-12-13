package com.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("Truck")
public class Truck extends Vehical{
	
	
	@Column(name="Truck_Name")
	String truckName;
	
	@Column(name="Truck_Price")
	String truckPrice;

	public String getTruckName() {
		return truckName;
	}

	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}

	public String getTruckPrice() {
		return truckPrice;
	}

	public void setTruckPrice(String truckPrice) {
		this.truckPrice = truckPrice;
	}
	
}