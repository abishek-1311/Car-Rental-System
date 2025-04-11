package dao;
import java.util.*;
import entity.*;
import exception.*;

public interface ICarLeaseRepository {
	
	//Car...!
	public void addCar(Vehicle vehicle);
	
	void removeCar(int vehicleId);
	
	public List<Vehicle> listAvailCars();
	
	public List<Vehicle> listRentedCars();
	
	public List<Vehicle> findCarById(int vehicleId) throws CarNotFoundException;
	
	
	//Customer...!
	void addCustomer(Customer customer);
	
	void removeCustomer(int customerId);
	
	List<Customer> listCustomers();
	
	Customer findCustomerById(int customerId) throws CustomerIdNotFoundException;
	
	//lease...!
	Lease createLease(int customerId , int vehicleId , String startDate , String endDate , String type);
	
	void returnCar(int leaseId) throws LeaseNotFoundException;
	
	List<Lease> listActiveLeases();
	
	List<Lease> listLeaseHistroy();
	
	//payment
	void recordPayment(Lease lease , double amount);
	
	
	
	
}
