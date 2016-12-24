package com.gamsion.chris.EmotionModule.emotions;

public enum EmotionType {
	
	admiration(0, 1000000), amazement(0, 1000000), grief(0, 1000000), happiness(0, 1000000), loathing(0,
			1000000), rage(0, 1000000), terror(0, 1000000), vigilance(0, 1000000);
	
	
	int minValue, maxValue;
	EmotionType(int minValue, int maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
}
