package org.elsys.netprog.rest;

public class InfoGET {
	
	String hash;
	Integer length;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "Info [hashedArray=" + hash + ", arrayLength=" + length.toString() + "]";
	}

}
