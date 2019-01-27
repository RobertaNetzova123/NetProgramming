package org.elsys.netprog.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import javax.ws.rs.core.MediaType;
import org.elsys.netprog.rest.RandomByteArray;
import org.elsys.netprog.rest.InfoPOST;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {

	public RestClient() {
		super();
	}

	private static final String REST_URI = "http://localhost:8080/jersey-rest-homework/game/g";
	private static final String POST_URI = "http://localhost:8080/jersey-rest-homework/game/s";
	private static InfoPOST information = new InfoPOST();
	private static JSONObject json = new JSONObject();

	public void start() throws JSONException {

		cilentGET();
		System.out.println("ORIGINAL:");
		System.out.println(json);
		System.out.println("...........");
//		guessing();
		 clientPOST();

	}

	public static void cilentGET() throws JSONException {

		String output;
		try {

			URL url = new URL(REST_URI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);

				json = parse(output, json);

			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static void clientPOST() throws JSONException {
		try {

			URL url = new URL(POST_URI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String str = guessing();
			
			OutputStream os = conn.getOutputStream();
			os.write(str.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			// returning status
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	static JSONObject parse(String input, JSONObject json) throws JSONException {
		json = new JSONObject(input);
		System.out.println("JSON ........");
		System.out.println(json);
		int len = json.getInt("length");
		System.out.println(len);
		String h = json.getString("hash");
		System.out.println(h);
		return json;
	}

	public static String guessing() throws JSONException {
		long count = 0;
		int length = json.getInt("length");
		String sentHash = json.getString("hash");
		
		byte[] byteArr = RandomByteArray.CreateRandomByteArray(length);
		String hash = RandomByteArray.MD5Hashing(byteArr);
		
		while (!sentHash.equals(hash)) {
			
			System.out.println(count);
			byteArr = RandomByteArray.CreateRandomByteArray(length);
			hash = RandomByteArray.MD5Hashing(byteArr);
			count++;
		}
		
		System.out.println("Match Found");
		System.out.println("ORIGINAL");
		System.out.println(json);
		System.out.println("NEW");
		System.out.println(hash);
		System.out.println(count);
		
		System.out.println("arr");
		System.out.println(byteArr);
		System.out.println("encoding");
		
		
		information.setHash(hash);
		information.setEncodedByteArray(encode(byteArr));
		System.out.println("INFO");
		System.out.println(information.toString());
		
		JSONObject obj = new JSONObject();
		obj.put("hash", hash);
		return information.toString();
		
	}


	public static String encode(byte[] arr) {
		
		String encode = Base64.getUrlEncoder().encodeToString(arr);
		System.out.println(encode);
		return encode;

	}

}