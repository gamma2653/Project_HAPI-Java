package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is very angry. It is an emotion so realize it is
 * not towards any particular person.
 * </p>
 * <p>
 * pentID = "Grage"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Rage extends STDEmotion {

	private static final String PENTID = "Grage";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Rage";
	}

}
