package exception;

public class NotSupportOSException extends Exception{
	
	public NotSupportOSException() {
		
	}
	
	@Override
	public String toString() {
		return "Your OS is not support!!";
	}

}
