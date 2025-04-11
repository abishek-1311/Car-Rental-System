package testing;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import dao.ICarLeaseRepositoryImpl;
import entity.*;
import exception.CarNotFoundException;
import exception.CustomerIdNotFoundException;
import exception.LeaseNotFoundException;

class CarRentalSystemTesting {

	ICarLeaseRepositoryImpl car ;
	
	  
	@BeforeEach
	void objCreation(){
		car = new ICarLeaseRepositoryImpl();
	}
	
	@Test
	@Disabled
	void test() throws Exception {
		Vehicle vehicle = new Vehicle();

        

        vehicle.setVehicleId(12);;

        vehicle.setMake("Tesla");

        vehicle.setModel("Suv");

        vehicle.setYear(2022);

        vehicle.setDailyRate(50.0);

        vehicle.setStatus("Available");

        vehicle.setPasssengerCapacity(4);

        vehicle.setEngineCapacity(1200);
        
       
        
        assertDoesNotThrow(() -> car.addCar(vehicle));
		
        
	}
	
	@Test
	@Disabled
    public void testCreateLeaseSuccess() {
        Lease lease = car.createLease(1, 10, "2025-04-11", "2025-04-15", "Daily");
        assertNotNull(lease);
        assertEquals(1, lease.getCustomerId());
        assertEquals(10, lease.getVehicleId());
        assertEquals("Daily", lease.getType());
    }
	
	 @Test
	 @Disabled
	    public void testReturnCarAndRetrieveLease() throws LeaseNotFoundException {
	        int leaseId = 12; 

	        assertDoesNotThrow(() -> car.returnCar(leaseId));
	    }
	 
	 @Test
	    public void testFindCustomerByIdThrowsException() {
	        assertThrows(CustomerIdNotFoundException.class, () -> {
	            car.findCustomerById(15); // customer 15 not exists.
	        });
	    }
	 
	 @Test
	    public void testFindCarByIdThrowsException() {
	        assertThrows(CarNotFoundException.class, () -> {
	            car.findCarById(20);  // Car 20 is not exists.
	        });
	    }
	 
	 @Test
	    public void testReturnCarThrowsException() {
	        assertThrows(LeaseNotFoundException.class, () -> {
	            car.returnCar(12);  // Lease Id not exists.
	        });
	    }

}
