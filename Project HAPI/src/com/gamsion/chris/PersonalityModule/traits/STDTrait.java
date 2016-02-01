package com.gamsion.chris.PersonalityModule.traits;

/**
 * <p>
 * This is the template class for all traits. All traits should extends this
 * class.
 * </p>
 * <p>
 * These classes are used to create objects that would store values that are
 * manipulated by the provided methods. The methods could be overridden to
 * reflect unique properties of each trait. The default provided traits do
 * no make any such overrides.
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public abstract class STDTrait {

	/**
	 * It is based on a 1000000 value cap with a minimum of 0. For example if
	 * Happy is at 1000000 that person is as happy as he can physically possibly
	 * be.
	 */
	private double value = 100;
	private double maxValue = 200;
	private double lowestValue = 0;

	/**
	 * Checks the value. If it is above 1000000, ground it to 1000000. If it is
	 * below 0, raise it to 0.
	 * 
	 * @return - By how much was the original value off from the boundries.
	 */
	private double checkValue() {
		if (value > maxValue) {
			double returnnumb = maxValue - value;
			value = 1000000;
			return returnnumb;
		} else if (value < lowestValue) {
			double returnnumb = value;
			value = lowestValue;
			return returnnumb;
		}
		return 0;
	}

	/**
	 * Adds the parameter to the value (then checks the value) then returns the
	 * new value incase you wanted it. (Got that idea from lua)
	 * 
	 * @param numb
	 *            - How much to add.
	 * @return - the new value.
	 */
	public double incrementValue(double numb) {
		value += numb;
		checkValue();
		return value;
	}

	/**
	 * @return - The current stored value.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return - The current inverse of the value.
	 */
	public double getNValue() {
		return maxValue - value;
	}

	/**
	 * @param numb
	 *            - What value to set value to.
	 */
	public void setValue(double numb) {
		value = numb;
		checkValue();
	}

	/**
	 * @param numb
	 *            - Sets the NValue to the requested value.
	 */
	public void setNValue(double numb) {
		value = maxValue - numb;
		checkValue();
	}

	/**
	 * Sets the personality's value to the percentage of the original value
	 * 
	 * @param numb
	 *            The percentage you want to make.
	 * @return
	 */
	public double percentValue(double numb) {
		value *= numb;
		value /= 100;
		checkValue();
		return value;
	}

	/**
	 * @return - the String id of the emotion.
	 */
	public abstract String getPentID();

	/**
	 * @return - the String name of the emotion.
	 */
	public abstract String getName();
	
	
	@Override
	public String toString(){
		StringBuilder strb = new StringBuilder(this.getPentID());
		strb.append("=");
		strb.append(this.getValue());
		return strb.toString();
	}
}
