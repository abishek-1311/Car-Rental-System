package entity;


public class Payment {
	private int paymentId ; 
	private int LeaseId;
	private String date;
	private double amount;
	
	public Payment(int paymentId, int leaseId, String date, double amount) {
		
		this.paymentId = paymentId;
		this.LeaseId = leaseId;
		this.date = date;
		this.amount = amount;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	public int getLeaseId() {
		return LeaseId;
	}


	public void setLeaseId(int leaseId) {
		LeaseId = leaseId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
}
