package org.elsys.netprog.rest;

import java.util.Base64;

public class InfoPOST {

	String hash;
	String encodedByteArray;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getEncodedByteArray() {
		return encodedByteArray;
	}

	public void setEncodedByteArray(String encodedByteArray) {
		this.encodedByteArray = encodedByteArray;
	}

	@Override
	public String toString() {
		String string = String.format("{\"hash\":\"%s\",\"encodedBytes\":\"%s\"}", hash,encodedByteArray);
		return string;
	}

}
