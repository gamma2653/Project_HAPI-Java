package com.gamsion.chris.resources.exceptions.EmotionModule;

/**
 * Thrown when the save did not exist before it was verified, however once
 * verified, it was successfully created with default values.
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class SuccessfulSaveCreationException extends Exception {
	public static final String STANDARD_CAUSE = "Emotion Module has created a new save.";

	private static final long serialVersionUID = 1L;

	public SuccessfulSaveCreationException() {
		super();
	}

	public SuccessfulSaveCreationException(String message) {
		super(message);
	}

	public SuccessfulSaveCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SuccessfulSaveCreationException(Throwable cause) {
		super(cause);
	}

}
