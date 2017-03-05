package com.gamsion.chris.PersonalityModule.traits;

public enum TraitType {
	agreeableness(0, 100000, 50000), conscientousness(0, 100000, 50000), extraversion(0,
			100000, 50000), neuroticism(0, 100000, 50000), openness(0, 100000, 50000);

	int minValue, maxValue, balancedValue;

	TraitType(int minValue, int maxValue, int balancedValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.balancedValue = balancedValue;
	}
}
