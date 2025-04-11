package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import entity.*;
import exception.*;
import util.DBConnection;

import java.awt.Taskbar.State;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {
	
	public static Connection con ;
	 
	
	public ICarLeaseRepositoryImpl(){
		
		try {
		con=DBConnection.getConnection();
//		System.out.println("Connection Done");
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@Override
	public void addCar(Vehicle vehicle) {
		
		int vehicleId = vehicle.getVehicleId();
		String make = vehicle.getMake(); 
		String model = vehicle.getModel();
		int year = vehicle.getYear();
		double dailyRate = vehicle.getDailyRate();
		int status = vehicle.getStatus().equals("Available") ? 1:0 ;
		int passsengerCapacity = vehicle.getPasssengerCapacity() ;
		int engineCapacity = vehicle.getEngineCapacity() ;
		
		String query = "insert into vehicle(car_id,make,model,year,daily_rate,status,pass_cap,engine_cap) values(?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = con.prepareStatement(query) ;
			pst.setInt(1, vehicleId);
			pst.setString(2, make);
			pst.setString(3, model);
			pst.setInt(4, year);
			pst.setDouble(5, dailyRate);
			pst.setInt(6, status);
			pst.setInt(7, passsengerCapacity);
			pst.setInt(8, engineCapacity);
			int res = pst.executeUpdate();
			
			System.out.println("Car Added Successfully..!");
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void removeCar(int vehicleId) {
		try {
			Statement stmt = con.createStatement();
			int res = stmt.executeUpdate("delete from vehicle where car_id ="+vehicleId);
			
			if(res >=1) {
				System.out.println("Car Id "+vehicleId+" Deleted successfully..!");
			}
			else {
				System.out.println(vehicleId+" Not Found...!");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public List<Vehicle> listAvailCars()  {
		List<Vehicle> arr= new ArrayList<Vehicle>();
		try {
		Statement stmt  = con.createStatement();
		ResultSet set = stmt.executeQuery("select car_id, make, model from vehicle where status = 1 ");
		while(set.next()) {
			Vehicle veh= new Vehicle();
			veh.setVehicleId(set.getInt(1));
			veh.setMake(set.getString(2));
			veh.setModel(set.getString(3));
			arr.add(veh);
		}
		
		}
		catch(SQLException e) {
			e.toString();
		}
		return arr;
	}

	@Override
	public List<Vehicle> listRentedCars() {
		List<Vehicle> arr= new ArrayList<Vehicle>();
		try {
			Statement stmt  = con.createStatement();
			ResultSet set = stmt.executeQuery("select make, model from vehicle where status = 0 ");
			while(set.next()) {
				Vehicle veh= new Vehicle();
				veh.setMake(set.getString(1));
				veh.setModel(set.getString(2));
				arr.add(veh);
			}
		}
		catch(SQLException e) {
			e.toString();
		}
		return arr;
	}

	@Override
	public List<Vehicle> findCarById(int vehicleId) throws CarNotFoundException {
		List<Vehicle> arr= new ArrayList<Vehicle>();
		Vehicle veh= new Vehicle();
		
		arr.add(veh);
		
		try {
			Statement stmt  = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from vehicle where car_id="+vehicleId);
			
			if(!set.next()) {
				throw new CarNotFoundException("Car Id :"+vehicleId+" Not Found..!");
			}
			
				arr.get(0).setVehicleId(set.getInt(1));
				arr.get(0).setMake(set.getString(2));
				arr.get(0).setModel(set.getString(3));
				arr.get(0).setYear(set.getInt(4));
				arr.get(0).setDailyRate(set.getDouble(5));
				arr.get(0).setStatus(set.getInt(6)==1 ? "Available" : "Not Available");
				arr.get(0).setPasssengerCapacity(set.getInt(7));
				arr.get(0).setEngineCapacity(set.getInt(8));
			
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		return arr;
		
	}

	@Override
	public void addCustomer(Customer customer) {
		int id = customer.getCustomerId();
		String f_name = customer.getFirstName();
		String l_name = customer.getLastName();
		String email = customer.getEmail();
		String phone = customer.getPhoneNumber();
		
		String query = "Insert into customers values(?, ? , ? , ? , ?)";
		
		try {
			PreparedStatement pst =con.prepareStatement(query) ;
			pst.setInt(1, id);
			pst.setString(2, f_name);
			pst.setString(3, l_name);
			pst.setString(4, email);
			pst.setString(5, phone);
			int res = pst.executeUpdate();
			System.out.println("Customer Added Succcessfully..!");
		}
		catch(Exception e) {
			e.toString();
		}
		
		
	}

	@Override
	public void removeCustomer(int customerId) {
		try {
			Statement stmt = con.createStatement();
			int res= stmt.executeUpdate("delete from customers where cust_id ="+customerId);
			if(res>=1) {
				System.out.println("Customer ID :"+customerId+" Deleted Successfully..!");
			}
			else {
				System.out.println("Customer ID :"+customerId+" Not Found...!");
			}
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public List<Customer> listCustomers() {
		List<Customer> cust = new ArrayList<Customer>();
		try {
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from customers");
			
			while(set.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(set.getInt(1));
				customer.setFirstName(set.getString(2));
				customer.setLastName(set.getString(3));
				customer.setEmail(set.getString(4));
				customer.setPhoneNumber(set.getString(5));
				cust.add(customer);
			}
		}
		catch(Exception e) {
			e.toString();
		}
		return cust;
	}

	@Override
	public Customer findCustomerById(int customerId) throws CustomerIdNotFoundException {
		
		Customer customer = null;
		
		try {
			Statement stmt  = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from customers where cust_id="+customerId);
			
			if(set.next()){
				customer = new Customer();
				customer.setCustomerId(set.getInt(1));
				customer.setFirstName(set.getString(2));
				customer.setLastName(set.getString(3));
				customer.setEmail(set.getString(4));
				customer.setPhoneNumber(set.getString(5));
			}
			else {
				throw new CustomerIdNotFoundException("customer Id "+customerId+" Not Found, Enter valid Customer Id.");
			}
			
			
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		return customer;
	}
	

	@Override
	public Lease createLease(int customerId, int vehicleId, String startDate, String endDate, String type) {
		
		Lease lease = new Lease();
		Date start =null, end=null;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("Select max(lease_id) from lease");
			res.next();
			int leaseId = res.getInt(1)+1;
			
			
			//converting java date format to sql date format.
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				start=format.parse(startDate);
				end = format.parse(endDate);
			}
			catch(Exception e) {
				System.out.println("invalid date..!");
			}
			
			java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());
			//--------------------------------------------------------------------//
			
			ICarLeaseRepositoryImpl carlease = new ICarLeaseRepositoryImpl();
			
			 if(carlease.findCustomerById(customerId).getCustomerId()!=0 && carlease.findCarById(vehicleId).get(0).getVehicleId()!=0 ){
				 
				 boolean isCarAvail = false;
					List<Vehicle> avail = carlease.listAvailCars();
					for(Vehicle veh : avail) {
						if(veh.getVehicleId()==vehicleId) {
							isCarAvail = true;
						}
					}
				 
				if(isCarAvail) {
					 String query = "insert into lease values(?,?,?,?,?,?)";
					 PreparedStatement pst = con.prepareStatement(query);
					 pst.setInt(1, leaseId);
					 pst.setInt(2, vehicleId);
					 pst.setInt(3,customerId);
					 pst.setDate(4, sqlStartDate);
					 pst.setDate(5, sqlEndDate);
					 pst.setString(6, type);
					 int result = pst.executeUpdate();
					 
					 if(result >= 1) {
						 System.out.println("Lease Created Successfully...!");
						 System.out.println("[LeaseID :"+leaseId+" custId :"+customerId+" vehicleId :"+vehicleId+" startDate:"+startDate+" endDate :"+endDate+" Type:"+type+"]");
					 }
					
					 Statement statusUpdate  = con.createStatement();
					 statusUpdate.executeUpdate("update vehicle set status = 0 where car_id="+vehicleId);
					 lease.setLeaseId(leaseId);
					 lease.setCustomerId(customerId);
					 lease.setVehicleId(vehicleId);
					 lease.setStartDate(startDate);
					 lease.setEndDate(endDate);
					 lease.setType(type);
					
					 
				 }
				 else {
					 System.out.println("Car Not Available..!");
				 }
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return lease;
	}

	@Override
	public void returnCar(int leaseId) throws LeaseNotFoundException {
		Lease lease = new Lease();
		
		ICarLeaseRepositoryImpl carlease = new  ICarLeaseRepositoryImpl();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select lease_id from lease where lease_id="+leaseId);
			if(!rs.next()) {
				throw new LeaseNotFoundException("Lease Id Not Found...!");
			}
			
			else {
				//get the lease info...!
				Statement stmt = con.createStatement();
				ResultSet set = stmt.executeQuery("select * from lease where lease_id ="+leaseId);
				set.next();
				lease.setLeaseId(set.getInt(1));
				lease.setVehicleId(set.getInt(2));
				lease.setCustomerId(set.getInt(3));
				lease.setStartDate(set.getDate(4).toString());
				lease.setEndDate(set.getDate(5).toString());
				//System.out.println(lease.getVehicleId());
				//System.out.println(set.getDate(4).toString());
				
				//change the availablity status of the car..!
				Statement stmt2 = con.createStatement();
				int res = stmt2.executeUpdate("update vehicle set status = 1 where car_id="+lease.getVehicleId());
				
				//update the end date in lease if they return the car before the end date..!
				LocalDate curDate = LocalDate.now();
				
				String currentDate = curDate.toString();
				if(!currentDate.equals(lease.getEndDate())) {
					java.sql.Date sqlEndDate = java.sql.Date.valueOf(curDate);
					
					Statement statement = con.createStatement();
					int r = statement.executeUpdate("update lease set end_date ='"+sqlEndDate+"' where lease_id ="+lease.getLeaseId());
					lease.setEndDate(currentDate); //also update the end date in lease obj.
				}
				
				//get the daily_rate 
				Statement stmt3 = con.createStatement();
				ResultSet set2 = stmt3.executeQuery("select daily_rate from vehicle where car_id ="+lease.getVehicleId());
				set2.next();
				double amount = set2.getDouble(1);
				System.out.println(amount);
				carlease.recordPayment(lease, amount);
			}
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public List<Lease> listActiveLeases() {
		List<Lease> activeLeaseList = new ArrayList<Lease>();
		List<Integer> leaseIdList = new ArrayList<Integer>();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs =  st.executeQuery("select lease_id from payment");
			while(rs.next()) {
				leaseIdList.add(rs.getInt(1));
			}
			
			Statement stmt = con.createStatement();
			ResultSet set =stmt.executeQuery("select * from lease where curdate() <= end_date");
			
			while(set.next()) {
				int leaseID = set.getInt(1);
				if(!leaseIdList.contains(leaseID)) {
					Lease lease = new Lease();
					lease.setLeaseId(set.getInt(1));
					lease.setVehicleId(set.getInt(2));
					lease.setCustomerId(set.getInt(3));
					lease.setStartDate(set.getDate(4).toString());
					lease.setEndDate(set.getDate(5).toString());
					lease.setType(set.getString(6));
					activeLeaseList.add(lease);
				}
			}
		}
		catch(Exception e) {
			e.toString();
		}
		return activeLeaseList;
	}

	@Override
	public List<Lease> listLeaseHistroy() {
		List<Lease> histroyLeaseList = new ArrayList<Lease>();
		List<Integer> leaseIdList = new ArrayList<Integer>();
		
		try {
			//get the lease id from payment table to check weather the cust return car.
			Statement st = con.createStatement();
			ResultSet rs =  st.executeQuery("select lease_id from payment");
			while(rs.next()) {
				leaseIdList.add(rs.getInt(1));
			}
			
			//get the Lease Histroy.
			Statement stmt = con.createStatement();
			ResultSet set =stmt.executeQuery("select * from lease where curdate() >= end_date");
			while(set.next()) {
				int leaseId= set.getInt(1);
				if(leaseIdList.contains(leaseId)) {   //to check enddate is today
					Lease lease = new Lease();
					lease.setLeaseId(set.getInt(1));
					lease.setVehicleId(set.getInt(2));
					lease.setCustomerId(set.getInt(3));
					lease.setStartDate(set.getDate(4).toString());
					lease.setEndDate(set.getDate(5).toString());
					lease.setType(set.getString(6));
					histroyLeaseList.add(lease);
				}
			}
		}
		catch(Exception e) {
			e.toString();
		}
		return histroyLeaseList;
	}

	@Override
	public void recordPayment(Lease lease, double amount) {
		
		try {
			
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery("select datediff((select curdate()),(select start_date from lease where lease_id ="+lease.getLeaseId()+"))");
			set.next();
			int noOfdays = set.getInt(1);
			
			double totalAmount = amount * noOfdays; 
			System.out.println("Amount to Paid : "+totalAmount);
			
			//get last payment id.
			Statement st2 = con.createStatement();
			ResultSet set2 = st2.executeQuery("Select max(pay_id) from payment");
			set2.next();
			
			int newPaymentId = set2.getInt(1) + 1;
			
			//System.out.println(newPaymentId);
			
			//converting java date to sql date.
			Date endJavaDate = null ;
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				endJavaDate=format.parse(lease.getEndDate());
				
			}
			catch(Exception e) {
				System.out.println("invalid date..!");
			}
			
			java.sql.Date sqlEndDate = new java.sql.Date(endJavaDate.getTime());
			
			//enter the new transaction in payment table.
			PreparedStatement pst = con.prepareStatement("insert into payment values(?,?,?,?)");
			pst.setInt(1, newPaymentId);
			pst.setInt(2, lease.getLeaseId());
			pst.setDate(3,sqlEndDate);
			pst.setDouble(4, totalAmount);
			pst.executeUpdate();
			
		}
		catch(Exception e) {
			e.toString();
		}
		
	}
	
}
