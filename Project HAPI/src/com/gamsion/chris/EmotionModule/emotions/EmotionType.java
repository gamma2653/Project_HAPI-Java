package com.gamsion.chris.EmotionModule.emotions;

public enum EmotionType {

	admiration(0, 1000000, 50000, 50000), amazement(0, 1000000, 50000, 50000), grief(0, 1000000, 50000,
			50000), happiness(0, 1000000, 50000, 50000), loathing(0, 1000000, 50000, 50000), rage(0, 1000000, 50000,
					50000), terror(0, 1000000, 50000, 50000), vigilance(0, 1000000, 50000, 50000);

	int minValue, maxValue, startValue, balancedValue;

	EmotionType(int minValue, int maxValue, int startValue, int balancedValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.startValue = startValue;
		this.balancedValue = balancedValue;
	}
}
