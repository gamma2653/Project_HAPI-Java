package com.gamsion.chris.utility.EmotionModule.emotions;

public abstract class STDEmotion{

	
	/**It is based on a 1000000 value cap with a minimum of 0.
	 * For example if Happy is at 1000000 that person is as happy as he can physically possibly be. */
	int value = 500000;
	
	/**
	 * Checks the value. If it is above 1000000, ground it to 1000000. If it is below 0, raise it to 0.
	 * @return - By how much was the original value off from the boundries.
	 */
	private int checkValue(){
		if(value>1000000){
			int returnint = 1000000-value;
			value=1000000;
			return returnint;
		}else if(value<0){
			int returnint = value;
			value=0;
			return returnint;
		}
		return 0; 
	}
	/**
	 * Adds the parameter to the value (then checks the value) then returns the new value incase you wanted it. (Got that idea from lua)
	 * @param numb - How much to add.
	 * @return - the new value.
	 */
	public int incrementValue(int numb){
		value+=numb;
		checkValue();
		return value;
	}
	/**
	 * @return - The current stored value.
	 */
	public int getValue(){
		return value;
	}
	/**
	 * @return - The current inverse of the value.
	 */
	public int getNValue(){
		return 1000000-value;
	}
	/**
	 * @param numb - What value to set value to.
	 */
	public void setValue(int numb){
		value = numb;
		checkValue();
	}
	/**
	 * @param numb - Sets the NValue to the requested value.
	 */
	public void setNValue(int numb){
		value = 1000000-numb;
		checkValue();
	}		
	/**
	 * Do not use if precision is important.
	 * 
	 * @param numb The percentage you want to make.
	 * @return
	 */
	public int percentValue(int numb){
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
	
}
