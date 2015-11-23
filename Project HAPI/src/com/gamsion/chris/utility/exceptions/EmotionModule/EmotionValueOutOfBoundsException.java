package com.gamsion.chris.utility.exceptions.EmotionModule;

/**
 * Thrown when the value is out of bounds. (Often within the save file).
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class EmotionValueOutOfBoundsException extends Exception {
	public static final String STANDARD_CAUSE = "The value of the emotion are out of bounds.";

	private static final long serialVersionUID = 1L;

	public EmotionValueOutOfBoundsException() {
		super();
	}

	public EmotionValueOutOfBoundsException(String message) {
		super(message);
	}

	public EmotionValueOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmotionValueOutOfBoundsException(Throwable cause) {
		super(cause);
	}

}
