package org.elsys.netprog.client;

import org.json.JSONException;

public class Main {

	
	public static void main(String[] args) throws JSONException {
		RestClient client = new RestClient(); 
		client.start();
	}
}
