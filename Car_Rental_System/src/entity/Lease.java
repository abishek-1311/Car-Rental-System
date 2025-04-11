package entity;


public class Lease {
	private int LeaseId;
	private int vehicleId;
	private int customerId ;
	private String startDate;
	private String endDate;
	private String type;
	
	public Lease() {
		
	}
	
	public Lease(int leaseId, int vehicleId, int customerId, String startDate, String endDate, String type) {
		LeaseId = leaseId;
		this.vehicleId = vehicleId;
		this.customerId = customerId;
		this.startDate = startDate;
		this.endDate = endDate ;
		this.type = type;
	}



	public int getLeaseId() {
		return LeaseId;
	}

	public void setLeaseId(int leaseId) {
		LeaseId = leaseId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Lease [LeaseId=" + LeaseId + ", vehicleId=" + vehicleId + ", customerId=" + customerId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", type=" + type + "]";
	}
}
