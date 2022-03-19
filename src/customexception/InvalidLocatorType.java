package customexception;

public class InvalidLocatorType extends RuntimeException{
	
	public InvalidLocatorType(String message) {
		super(message);
	}
}
