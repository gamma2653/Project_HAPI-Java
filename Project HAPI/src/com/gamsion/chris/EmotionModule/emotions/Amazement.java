package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is positively surprised. Something has happened
 * beyond their expectation.
 * </p>
 * <p>
 * pentID = "amaze"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Amazement extends STDEmotion {

	private static final String PENTID = "amaze";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Amazement";
	}

}
