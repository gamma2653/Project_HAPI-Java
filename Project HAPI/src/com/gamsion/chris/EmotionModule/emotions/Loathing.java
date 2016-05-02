package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is feeling hateful. Since it is an emotional
 * instance, it isn't exactly towards anyone.
 * </p>
 * <p>
 * pentID = "loath"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Loathing extends STDEmotion {

	private static final String PENTID = "loath";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Loathing";
	}
	public Loathing clone() {
		return (Loathing) super.clone();
	}

}
