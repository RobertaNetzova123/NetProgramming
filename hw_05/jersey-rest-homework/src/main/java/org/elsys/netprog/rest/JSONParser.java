package org.elsys.netprog.rest;

public class JSONParser {

	String hash;
	String encodedBytes;
	Integer length;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getEncodedBytes() {
		return encodedBytes;
	}

	public void setEncodedByteArray(String encodedBytes) {
		this.encodedBytes = encodedBytes;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public String toString() {
		String string = String.format("{\"encodedBytes\":\"%s\","
				+ "\"length\":%d," + "\"hash\":\"%s\"}", encodedBytes, length,
				hash);
		return string;
	}

}
