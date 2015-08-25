package com.gamsion.chris.resources.exceptions.EmotionModule;

/**
 * Thrown when Emotion Module's save is unreachable.
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class EmotionModuleFileException extends Exception {
	public static final String STANDARD_CAUSE = "Emotion Module file malfunctioned.";
	public static final String FILE_CAUSE = "Emotion Module failed to load the requested file.";

	private static final long serialVersionUID = 1L;

	public EmotionModuleFileException() {
		super();
	}

	public EmotionModuleFileException(String message) {
		super(message);
	}

	public EmotionModuleFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmotionModuleFileException(Throwable cause) {
		super(cause);
	}

}
