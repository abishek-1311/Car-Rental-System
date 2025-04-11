package entity;

public class Vehicle {
	private int vehicleId;
	private String make;
	private String model;
	private int year;
	private double dailyRate ;
	private String status;
	private int passsengerCapacity;
	private int engineCapacity;
	
	public Vehicle() {
		
	}
	
	public Vehicle(int vehicleId, String make, String model, int year, double dailyRate, String status,
			int passsengerCapacity, int engineCapacity) {
		this.vehicleId = vehicleId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.dailyRate = dailyRate;
		this.status = status;
		this.passsengerCapacity = passsengerCapacity;
		this.engineCapacity = engineCapacity;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", make=" + make + ", model=" + model + ", year=" + year
				+ ", dailyRate=" + dailyRate + ", status=" + status + ", passsengerCapacity=" + passsengerCapacity
				+ ", engineCapacity=" + engineCapacity + "]";
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPasssengerCapacity() {
		return passsengerCapacity;
	}

	public void setPasssengerCapacity(int passsengerCapacity) {
		this.passsengerCapacity = passsengerCapacity;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

}
