package com.jpa.entity;

@Entity
public class Truck extends Vehicle{
	@Column(name="contains")
	int container;

	public int getContainer() {
		return container;
	}
	
	public void setContainer(int container) {
		this.container = container;
	}
}