
package com.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Truck extends Vehicle{
	
	@Column(name="container")
	int container;

	public int getContainer() {
		return container;
	}

	public void setContainer(int container) {
		this.container = container;
	}
	
	
}
