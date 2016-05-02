package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone saddened. It is a form of depression and is
 * associated to anything that would sadden someone.
 * </p>
 * <p>
 * pentID = "grief"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Grief extends STDEmotion {

	private static final String PENTID = "grief";

	@Override
	public String getPentID() {
		return PENTID;

	}

	@Override
	public String getName() {
		return "Grief";
	}
	public Grief clone() {
		return (Grief) super.clone();
	}

}
