package com.gamsion.chris.resources.exceptions.EmotionModule;

/**
 * Thrown when no data is found within the save.
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class EmotionNotFoundInSaveException extends Exception {
	public static final String STANDARD_CAUSE = "The emotion is nonexistant: ";

	private static final long serialVersionUID = 1L;

	public EmotionNotFoundInSaveException() {
		super();
	}

	public EmotionNotFoundInSaveException(String message) {
		super(message);
	}

	public EmotionNotFoundInSaveException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmotionNotFoundInSaveException(Throwable cause) {
		super(cause);
	}

}
