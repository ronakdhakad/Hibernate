package com.jpa.main;
import java.util.List;

import com.jpa.model.Car;
import com.jpa.model.Truck;
import com.jpa.model.Vehical;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAMain{
	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypunit");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
		Vehical vech = new Vehical();
		vech.setVehicle_name("Vehicle");
		
		Car car = new Car();
		car.setDoors(4);
		car.setVehicle_name("Kia");
		
		Truck truck = new Truck();
		truck.setContainer(4);
		truck.setVehicle_name("ForceMotors");
		
			try {	
				em.persist(vech);
				em.persist(car);
				em.persist(truck);
				
				tx.commit();
				
				List<Vehicle> vList = em.createQuery("from Vehicle", Vehicle.class).getResultList(); 
				for(Vehicle v :  vList)
					System.out.println("\nVehicle Id : "+v.getVid()+"\nVehicleName : "+v.getVehicle_name()+"\nClass Name : "+v.getClass().getSimpleName());
				
			}catch(Exception e) {
				if(tx!=null)
					tx.rollback();
			}
			em.close();
		emf.close();
	}
}