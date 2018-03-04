package program.exception;

/**
 * Invalid Plate Number
 * @author german
 *
 */
public class InvalidPlateNumberException extends Exception {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor
	 */
	public InvalidPlateNumberException(String message) {
		super(message);
	}

}
