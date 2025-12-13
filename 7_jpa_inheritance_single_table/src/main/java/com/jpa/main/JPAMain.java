package com.jpa.main;
import com.jpa.entity.*;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
public class JPAMain {
	public static void main(String args[]) {
		
		
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("mypunit");
	EntityManager em=emf.createEntityManager();
	EntityTransaction tx=em.getTransaction();	
	
	tx.begin();
	
	Vehical vehical =new Vehical();
	vehical.setVehicalName("TATA motors");
	vehical.setVid(101);
	Car car =new Car();
	car.setCarName("fortuner");
	car.setCarPrice("10000000");
	
	Truck truck =new Truck();
	truck.set
}
}
