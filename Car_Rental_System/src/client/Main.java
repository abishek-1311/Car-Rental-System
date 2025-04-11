package client;

import java.util.List;
import java.util.Scanner;

import dao.ICarLeaseRepositoryImpl;
import entity.Customer;
import entity.Lease;
import entity.Vehicle;
import exception.CustomerIdNotFoundException;

public class Main {
	
	public static void main(String[] args) {
		ICarLeaseRepositoryImpl carLease  = new ICarLeaseRepositoryImpl();
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("******MENU*******");
				System.out.println("1.ADD CAR.\n2.REMOVE CAR.\n3.AVAILABLE CARS.\n4.RENTED CARS.\n5.FIND CAR.\n6.ADD CUSTOMER.\n7.REMOVE CUSTOMER\n8.CUSTOMER LIST.\n9.FIND CUSTOMER\n10.CREATE LEASE.\n11.RETURN CAR.\n12.ACTIVE LEASE.\n13.LEASE HISTROY\n14.EXIT");
				System.out.println("*****************");
				System.out.print("Enter your choice:");
				int choice  = sc.nextInt();
				sc.nextLine();
				
				switch(choice) {
				case 1:
					Vehicle vehicle ;
					System.out.print("Enter vehicle ID :");
					int vehicleId = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter vehicle make :");
					String make = sc.nextLine();
					System.out.print("Enter vehicle model");
					String model = sc.nextLine();
					System.out.print("Enter vehicle make year :");
					int year = sc.nextInt();
					System.out.print("Enter daily Rate :");
					double dailyRate = sc.nextDouble();
					sc.nextLine();
					System.out.print("Enter status 'Available' or 'Not Available' :");
					String status = sc.nextLine();
					System.out.print("Enter passenger Capacity :");
					int passCap = sc.nextInt();
					System.out.print("Enter Engine capacity :");
					int engineCap = sc.nextInt();
					
					vehicle = new Vehicle(vehicleId, make, model, year, dailyRate, status, passCap, engineCap);
					carLease.addCar(vehicle);
					
					break;
					
				case 2 :
					System.out.print("Enter Car Id to REMOVE :");
					carLease.removeCar(sc.nextInt());
					
					break;
					
				case 3:
					List<Vehicle> arr = carLease.listAvailCars();
					for(Vehicle veh : arr) {								
						System.out.println(veh.getMake()+"   "+veh.getModel());
					}
					
					break;
					
				case 4:
					List<Vehicle> arr2 = carLease.listRentedCars();
					for(Vehicle veh : arr2) {									
						System.out.println(veh.getMake()+"   "+veh.getModel());
					}
				
					break;
					
				case 5:
					try {
						System.out.print("Enter car Id :");
						List<Vehicle> arr3 = carLease.findCarById(sc.nextInt());
						System.out.println("-----------Car Details---------");
						System.out.println(arr3.get(0).toString());				
						System.out.println("-------------------------------");
					}
					catch(Exception e) {
						System.out.println(e.toString());
					}
					
					break;
					
				case 6:{
					
					System.out.print("Enter the customer Id :");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter the first Name:");
					String fName = sc.nextLine();
					System.out.print("Enter the Second Name :");
					String sName = sc.nextLine();
					System.out.print("Enter the email :");
					String email = sc.nextLine();
					System.out.print("Enter Phone No:");
					String phone = sc.nextLine();
					
					Customer customer = new Customer(id, fName, sName, email, phone);
					carLease.addCustomer(customer);
					
					break;
				}
				
				case 7:
					System.out.print("Enter the customer Id to remove :");
					carLease.removeCustomer(sc.nextInt());
					
					break;
				
				case 8:
					System.out.println("***Customer List***");
					List<Customer> list = carLease.listCustomers();
					for(Customer customers : list) {
						System.out.println(customers.toString());
					}
					System.out.println("******************");
					
					break ;
				
				case 9:
					
					try {
						System.out.print("Enter the Id of the Customer :");
						Customer customer = carLease.findCustomerById(sc.nextInt());
						System.out.println("--------customer details---------");
						System.out.println(customer.toString());
					}
					catch( CustomerIdNotFoundException  e) {
						System.out.println(e.toString());
					}
					
					break;
					
				case 10:
					System.out.print("Enter Car Id:");
					int carID = sc.nextInt();
					System.out.print("Enter Cust Id:");
					int custID = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter startDate:");
					String startDate = sc.nextLine();
					System.out.print("Enter endDate:");
					String endDate = sc.nextLine();
					System.out.print("Enter type:");
					String type = sc.nextLine();
					
					carLease.createLease(custID, carID, startDate, endDate, type);
					
					break;
					
				case 11:
					System.out.print("Enter the Lease Id :");
					carLease.returnCar(sc.nextInt());
					
					break;
					
				case 12:
					
					System.out.println("List of Active Lease");
					List<Lease> listOfActiveLease = carLease.listActiveLeases();
					for(Lease l : listOfActiveLease) {
						System.out.println(l.toString());
					}
					break;
					
				case 13:
					System.out.println("Lease Histroy.");
					List<Lease> list2 = carLease.listLeaseHistroy();
					for(Lease l : list2) {
						System.out.println(l.toString());
					}
					
					break;
					
				case 14:
					
					System.out.println("System going to shut...!");
					System.exit(0);
				
				default:
					System.out.print("Invalid...! Enter valid choice");
				}
				
			}
			catch(Exception e) {
				e.toString();
			}
		}
	}
}
