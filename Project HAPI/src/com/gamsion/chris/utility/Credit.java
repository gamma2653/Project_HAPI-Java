package com.gamsion.chris.utility;

public class Credit {
	private double credit;

	public <U extends Number> Credit(U credit) {

	}

	public double Euro(double value) {
		return value;
	}

	public double USD(double value) {
		return value * 1.10;
	}

	public double AustralianDollar(double value) {
		return value * 1.40;
	}

	public double BritishPound(double value) {
		return value * 0.71;
	}

	public double CanadianDollar(double value) {
		return value * 1.35;
	}

}
