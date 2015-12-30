package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This emotion is when someone is frightened. It isn't the measure of how
 * frightened they are of someone, rather them being in a state of fear.
 * </p>
 * <p>
 * pentID = "terro"
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @see STDEmotion
 */
public class Terror extends STDEmotion {

	private static final String PENTID = "terro";

	@Override
	public String getPentID() {
		return PENTID;
	}

	@Override
	public String getName() {
		return "Terror";
	}

}
