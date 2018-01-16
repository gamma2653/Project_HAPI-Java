package com.gamsion.chris.EmotionModule.emotions;

/**
 * @author Christopher De Jesus a.k.a. gamma2626
 * @author <b>Gamsion Developers</b>
 *
 */
public enum EmotionType {

	admiration(0, 1000000, 50000, 50000), ecstasy(0, 1000000, 50000, 50000), rage(0, 1000000, 50000,
			50000), vigilance(0, 1000000, 50000, 50000);

	int minValue, maxValue, startValue, balancedValue;

	EmotionType(int minValue, int maxValue, int startValue, int balancedValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.startValue = startValue;
		this.balancedValue = balancedValue;
	}
}
