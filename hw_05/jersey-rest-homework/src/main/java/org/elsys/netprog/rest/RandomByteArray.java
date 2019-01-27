package org.elsys.netprog.rest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RandomByteArray {

	protected static int length = 3;
	protected static byte[] byteArr = CreateRandomByteArray(length);
	protected static String hash = MD5Hashing(byteArr);

	public RandomByteArray() {
		super();
		byteArr = CreateRandomByteArray(length);
		hash = MD5Hashing(byteArr);
	}

	public static byte[] CreateRandomByteArray(int len) {

		byte[] arr = new byte[len];
		new Random().nextBytes(arr);
		return arr;
	}

	public static String MD5Hashing(byte[] input) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input);
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e);
		}
	}
}
