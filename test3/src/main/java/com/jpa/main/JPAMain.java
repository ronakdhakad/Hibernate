package com.jpa.main;
import java.util.list;


import com.jpa.model.car;
import com.jpa.model.Truck;
import com.jpa.model.Vehicle;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class JPAMain{
	public static void main(String  args[]) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypunit");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Vehicle vec = new Vehicle();
		vec.setVehicle_name("Vehicle");
		Car car = new Car();
		car.setVehicle_name("Baleno ");
		Truck truck = new Truck();
		truck.setVehicle_name("Eicher");
		try {
			em.persist(vec);
			em.persist(car);
			em.persist(truck);
			 
			Tx.commit();
					List<Vehicle> vList = em.createQuery("from Vehicle", Vehicle.class).getResultList(); 
			for(Vehicle v :  vList)
				System.out.println("Vehicle Id : "+v.getVid()+"VehicleName : "+v.getVehicle_name()+"Class Name : "+v.getClass().getSimpleName());
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			System.out.println("Rollback takes place..!!");
		}
		em.close();
	emf.close();
			
			
		}
	}
}