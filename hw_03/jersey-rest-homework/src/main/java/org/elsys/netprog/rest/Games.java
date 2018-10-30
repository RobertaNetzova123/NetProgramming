package org.elsys.netprog.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Games {

	String ID;
	String secretNum;
	int bullsCount;
	int cowsCount;
	boolean gameStatus;
	int attemptsCount;

	public Games(String ID) {
		super();
		this.ID = ID;
		secretNum = randomNum();
		bullsCount = 0;
		cowsCount = 0;
		gameStatus = false;
		attemptsCount = 0;
		
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
		ArrayList<Character> num = new ArrayList<Character>();
		for (int i = 0; i < secretNum.length(); i++) {
			num.add(secretNum.charAt(i));
		}

		return secretNum;

	}

	private ArrayList stringToArrayConvertor(String num) {

		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 0; i < num.length(); i++) {
			list.add(num.charAt(i));
		}

		return list;

	}

	public void contains(String guessNum) {
		bullsCount = 0;
		cowsCount = 0;
		attemptsCount ++;
		if (guessNum.matches(secretNum)) {
			gameStatus = true;
		}
		ArrayList<Character> guessNumber = stringToArrayConvertor(guessNum);
		ArrayList<Character> secretNumber = stringToArrayConvertor(secretNum);
		
		for (int i = 0; i < guessNumber.size(); i ++) {
			 
			if (secretNumber.contains(guessNumber.get(i))) {
				if (secretNumber.get(i).equals(guessNumber.get(i))) {
					bullsCount++;
				}
				else cowsCount++;
			}
		}
	}
	
	public int getCowsCount() {
		return cowsCount;
	}
	
	public int getBullsCount() {
		return bullsCount;
	}

	public int getAttemptsCount() {
		return attemptsCount;
	}

	
	public boolean getGameStatus() {
		return gameStatus;
	}
}

