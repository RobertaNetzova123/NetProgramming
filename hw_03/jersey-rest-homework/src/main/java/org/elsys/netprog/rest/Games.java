package org.elsys.netprog.rest;

import java.util.HashSet;
import java.util.Random;

public class Games {

	String ID;
	String secretNum;

	public Games(String ID) {
		super();
		this.ID = ID;
		secretNum = randomNum();
	}

	private String randomNum() {

		boolean charIsUnique = false;
		String number = null;
		while (!charIsUnique) {
			charIsUnique = true;
			number = Integer.toString(randomNumberInRange(1000, 9999));
			charIsUnique = GameController.UniqueNumber(number);
		}

		return number;
	
	}

	public static int randomNumberInRange(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	public String getID() {
		return ID;
	}

	public String getSecretNumber() {
		return secretNum;
	}
}
