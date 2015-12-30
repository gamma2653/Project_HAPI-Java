package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is paying extra attention to outide details. It
 * is the idea of someone feeling like they see the whole situation and that
 * they are in control.
 * </p>
 * <p>
 * pentID = "vigil"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Vigilance extends STDEmotion {
	private static final String PENTID = "vigil";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Vigilance";
	}

}
