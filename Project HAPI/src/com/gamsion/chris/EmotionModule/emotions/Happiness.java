package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is happy. You should all know what this entails.
 * Not to be used as pleasure.
 * </p>
 * <p>
 * pentID = "happy"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Happiness extends STDEmotion {

	private static final String PENTID = "happy";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Happiness";
	}

}
