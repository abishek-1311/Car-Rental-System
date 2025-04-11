package exception;

public class CarNotFoundException extends Exception {
	String msg ;
	public CarNotFoundException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "CarNotFoundException [" + msg + "]";
	}
	
	
}
