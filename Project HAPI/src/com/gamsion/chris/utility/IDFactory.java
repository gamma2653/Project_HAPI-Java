package com.gamsion.chris.utility;

public class IDFactory {
	private static int dispatchedIDs = 0;

	public static String getID() {
		dispatchedIDs++;
		StringBuilder id = new StringBuilder(String.valueOf(dispatchedIDs));
		while (id.length() < 7) {
			id.insert(0, 0);
		}
		if (id.length() > 7)
			System.out
					.println("WARNING! DISPATCHED ID OVER 9999999! NO LONGER 7 CHARACTERS!");
		return id.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 999999; i++) {
			System.out.println(IDFactory.getID());
		}
	}
}
