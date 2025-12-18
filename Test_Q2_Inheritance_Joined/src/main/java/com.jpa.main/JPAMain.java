package com.jpa.main;

public class JPAMain{
	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypunit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
		Vehicle vehical = new Vehicle();
		vec.setVehicle_name("vehical");
		
		Car car = new Car();
		car.setDoors(6);
		car.setVehicle_name("Tata");
		
		Truck truck = new Truck();
		truck.setContainer(4);
		truck.setVehicle_name("Fortuner");
		
		try {	
			em.persist(vehical);
			em.persist(car);
			em.persist(truck);
			tx.commit();
			List<Vehicle> list = em.createQuery("from Vehicle", Vehicle.class).getResultList(); 
			for(Vehicle vehi :  list)
				System.out.println("Vehicle Id : "+vehi.getVid()+"VehicleName : "+vehi.getVehicle_name()+"Class Name : "+vehi.getName());
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();	
			}
	}
}