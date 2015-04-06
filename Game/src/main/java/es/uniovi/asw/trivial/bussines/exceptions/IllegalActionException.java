package es.uniovi.asw.trivial.bussines.exceptions;

/**
 * Exception launched by the API when an Illegal action is requested.
 * @author Montero Hernández, José Antonio
 *
 */
public class IllegalActionException extends Exception {
	private static final long serialVersionUID = 8939352352883180679L;

	public IllegalActionException () {
		super();
	}

	public IllegalActionException(String message) {
		super(message);
	}

	public IllegalActionException(Throwable cause) {
		super(cause);
	}

	public IllegalActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
