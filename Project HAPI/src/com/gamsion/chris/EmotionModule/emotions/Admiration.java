package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is proud of themselves. They feel like they had
 * accomplished something.
 * </p>
 * <p>
 * pentID = "admir"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Admiration extends STDEmotion {

	private static final String PENTID = "admir";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Admiration";
	}

}
