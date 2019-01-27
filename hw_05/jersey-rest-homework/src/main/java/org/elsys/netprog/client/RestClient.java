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
import org.elsys.netprog.rest.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {

	public RestClient() {
		super();
	}

	private static final String GET_URL = "http://localhost:8080/jersey-rest-homework/request/get";
	private static final String POST_URL = "http://localhost:8080/jersey-rest-homework/request/post";
	private static JSONParser information = new JSONParser();
	private static JSONObject json = new JSONObject();

	public void start() throws JSONException {

		cilentGET();
		clientPOST();

	}

	public static void cilentGET() throws JSONException {

		String output;
		try {

			URL url = new URL(GET_URL);
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

				json = new JSONObject(output);
				System.out.println(json);

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

			URL url = new URL(POST_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);

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

	public static String guessing() throws JSONException {

		long count = 0;
		int length = json.getInt("length");
		String sentHash = json.getString("hash");

		long createdMillis = System.currentTimeMillis();
		byte[] byteArr = RandomByteArray.CreateRandomByteArray(length);
		String hash = RandomByteArray.MD5Hashing(byteArr);

		while (!sentHash.equals(hash)) {

			// System.out.println(count);
			byteArr = RandomByteArray.CreateRandomByteArray(length);
			hash = RandomByteArray.MD5Hashing(byteArr);
			count++;
		}

		long nowMillis = System.currentTimeMillis();
		double time = ((nowMillis - createdMillis) / 1000);

		int avgHashes = (int) (count / time);

		System.out.println();
		System.out.println("Match Found");
		System.out.println("All hashes  = " + count);
		System.out.println("Time needed = " + time + " s");
		System.out.println("Hashes per second = " + avgHashes);

		information.setHash(hash);
		information.setEncodedByteArray(encode(byteArr));
		System.out.println("Information to post:");
		System.out.println(information.toString());

		return information.toString();

	}

	public static String encode(byte[] arr) {

		String encode = Base64.getUrlEncoder().encodeToString(arr);
		System.out.println("Array is encoded");
		return encode;

	}

}