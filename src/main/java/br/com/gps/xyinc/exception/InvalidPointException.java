package br.com.gps.xyinc.exception;

public class InvalidPointException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1336868742619226221L;

	public InvalidPointException() {
		
	}
	
	public InvalidPointException(String message) {
		super(message);
	}
	
	public InvalidPointException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidPointException(Throwable cause) {
		super(cause);
	}

}
