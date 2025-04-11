package exception;

public class CustomerIdNotFoundException extends Exception {
	String msg;
	
	public CustomerIdNotFoundException(String msg) {
		this.msg = msg ;
	}

	@Override
	public String toString() {
		return "CustomerIdNotFoundException [ "+ msg + "]";
	}

}
