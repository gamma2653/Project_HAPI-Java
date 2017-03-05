package com.gamsion.chris.PersonalityModule.traits;

/**
 * <p>
 * These classes are used to create objects that would store values that are
 * manipulated by the provided methods. 
 * </p>
 * 
 * @author gamma2626 a.k.a. Christopher De Jesus
 *
 */
public class Trait implements Cloneable{
	/**
	 * It is based on a 100000 value cap with a minimum of 0. For example if
	 * Agreeableness is at 100000 that person is as agreeable as Yes Man from Fallout New Vegas.
	 */
	private double value;
	private final double maxValue;
	private final double minValue;
	private final double balancedValue;
	
	/**
	 * Used to identify trait.
	 */
	private TraitType traitID;
	/**
	 * Constructor for creating a trait.
	 * @param emotionID - Necessary to identify trait.
	 */
	public Trait(TraitType type, double value){
		this.traitID = type;
		this.minValue = traitID.minValue;
		this.maxValue = traitID.maxValue;
		this.value = value;
		this.balancedValue = traitID.balancedValue;
		
	}


	/**
	 * Checks the value. If it is above 1000, ground it to 1000. If it is
	 * below 0, raise it to 0.
	 * 
	 * @return - By how much was the original value off from the boundaries.
	 */
	private double checkValue() {
		if (value > maxValue) {
			double returnnumb = maxValue - value;
			value = 1000000;
			return returnnumb;
		} else if (value < minValue) {
			double returnnumb = value;
			value = minValue;
			return returnnumb;
		}
		return 0;
	}

	/**
	 * Adds the parameter to the value (then checks the value) then returns the
	 * new value in case you wanted it. (Got that idea from Lua)
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


	
	@Override
	public String toString(){
		StringBuilder strb = new StringBuilder(this.traitID.toString());
		strb.append("=");
		strb.append(this.getValue());
		return strb.toString();
	}
	
	public Trait clone(){
		Trait t;
		try {
			t = (Trait) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		return t;
	}

	/**
	 * Deprecated not since it's old... it's not yet fully usable.
	 */
	@Deprecated
	public void tick(){
		if(value>balancedValue){
			value-=1;
		}else if(value<balancedValue){
			value+=1;
		}
	}
}
