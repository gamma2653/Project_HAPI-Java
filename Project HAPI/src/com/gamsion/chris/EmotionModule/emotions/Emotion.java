package com.gamsion.chris.EmotionModule.emotions;

/**
 * <p>
 * This is the template class for all emotions. All emotions should extends this
 * class.
 * </p>
 * <p>
 * These classes are used to create objects that would store values that are
 * manipulated by the provided methods. The methods could be overridden to
 * reflect unique properties of each emotion. The default provided emotions do
 * no make any such overrides.
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 * @author <b>Gamsion Developers</b>
 */
public class Emotion implements Cloneable {
	/**
	 * It is based on a 1000000 value cap with a minimum of 0. For example if Happy
	 * is at 1000000 that person is as happy as he can physically possibly be.
	 */
	private double value;
	private double deltaValue;
	private final double maxValue;
	private final double minValue;
	private final double balancedValue;
	/**
	 * Used to identify emotion
	 */
	private EmotionType emotionID;

	/**
	 * Constructor for creating an emotion.
	 * 
	 * @param emotionID
	 *            - Necessary to identify emotion
	 */
	public Emotion(EmotionType emotionID) {
		this.emotionID = emotionID;
		this.minValue = emotionID.minValue;
		this.maxValue = emotionID.maxValue;
		this.value = emotionID.startValue;
		this.balancedValue = emotionID.balancedValue;

	}

	/**
	 * @return the type of emotion
	 * @see EmotionType
	 */
	public EmotionType getType() {
		return emotionID;
	}

	/**
	 * Checks the value. If it is above 1000000, ground it to 1000000. If it is
	 * below 0, raise it to 0.
	 * 
	 * @return - By how much was the original value off from the boundaries
	 */
	public double checkValue() {
		if (value > maxValue) {
			double returndouble = maxValue - value;
			value = maxValue;
			return returndouble;
		} else if (value < minValue) {
			double returndouble = value;
			value = minValue;
			return returndouble;
		}
		return 0;
	}

	/**
	 * Adds the parameter to the value (then checks the value) then returns the new
	 * value incase you wanted it. (Got that idea from lua)
	 * 
	 * @param numb
	 *            - How much to add
	 * @return - the new value
	 */
	public double incrementValue(double numb) {
		double initialVal = value;
		value += numb;
		checkValue();
		deltaValue = value - initialVal;
		return value;
	}

	/**
	 * @return - The current stored value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return - The current inverse of the value
	 */
	public double getNValue() {
		return maxValue - value;
	}

	/**
	 * @param numb
	 *            - What value to set value to
	 */
	public void setValue(double numb) {
		double initialVal = value;
		value = numb;
		checkValue();
		deltaValue = value - initialVal;
	}

	/**
	 * @param numb
	 *            - Sets the NValue to the requested value
	 */
	public void setNValue(double numb) {
		double initialVal = value;
		value = maxValue - numb;
		checkValue();
		deltaValue = value - initialVal;
	}

	/**
	 * Do not use if precision is important.
	 * 
	 * @param numb
	 *            The percentage you want to make.
	 * @return the current emotion value
	 */
	public double percentValue(double numb) {
		double initialVal = value;
		value *= numb;
		value /= 100;
		checkValue();
		deltaValue = value - initialVal;
		return value;
	}
	
	/**
	 * @return the amount this emotion has last changed
	 */
	public double getDValue() {
		return deltaValue;
	}
	

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder(this.emotionID.toString());
		strb.append("=");
		strb.append(this.getValue());
		return strb.toString();
	}

	public Emotion clone() {
		Emotion em;
		try {
			em = (Emotion) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		return em;
	}

	/**
	 * Deprecated not since it's old... it's not yet fully usable.
	 */
	@Deprecated
	public void tick() {
		if (value > balancedValue) {
			value -= 1;
		} else if (value < balancedValue) {
			value += 1;
		}
	}

	/**
	 * Deprecated not since it's old... it's not yet fully usable.
	 */
	@Deprecated
	public void tick(int ticks) {
		for (int i = 0; i < ticks; i++) {
			if (value > balancedValue) {
				value -= 1;
			} else if (value < balancedValue) {
				value += 1;
			}
		}
	}
	
	
}
