package exception;

public class LeaseNotFoundException extends Exception {
	
	String msg ;
	public LeaseNotFoundException(String msg) {
		this.msg = msg ;
	}
	
	@Override
	public String toString() {
		return "LeaseNotFoundException [" + msg + "]";
	}
}

