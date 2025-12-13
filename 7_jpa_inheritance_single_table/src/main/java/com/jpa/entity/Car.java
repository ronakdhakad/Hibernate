package com.jpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
@DiscriminatorValue("Car")
public class Car extends Vehical{
	
@Column(name="Car_Name")
	String carName;

@Column(name="Car_Price")
String carPrice;

public String getCarName() {
	return carName;
}

public void setCarName(String carName) {
	this.carName = carName;
}

public String getCarPrice() {
	return carPrice;
}

public void setCarPrice(String carPrice) {
	this.carPrice = carPrice;
}



}