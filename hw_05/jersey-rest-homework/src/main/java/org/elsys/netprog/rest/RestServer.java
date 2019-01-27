package org.elsys.netprog.rest;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/request")
public class RestServer {

	private byte[] array = RandomByteArray.byteArr;
	private String hashedMsg = RandomByteArray.hash;

	@POST
	@Path("/post")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response recieveInformation(String information)
			throws URISyntaxException, JSONException {
		System.out.println("Information recieved");

		JSONObject obj = new JSONObject(information);

		byte[] decodedArr = decode(obj.getString("encodedBytes"));
		if (obj.getString("hash").equals(hashedMsg)
				&& Arrays.equals(decodedArr, array)) {

			System.out.println("Answer from post:\n");
			System.out.println("Hashed strings are equal");
			RandomByteArray arrayGenerator = new RandomByteArray();
			System.out.println("New array generated");

			return Response.status(200).entity("ok").build();

		} else {

			System.out.println("Error in matching.");
			return Response.status(406).entity("Not Acceptable").build();
		}

	}

	@GET
	@Path("/get")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response sendInformation() throws JSONException {

		JSONParser requestInfo = new JSONParser();

		requestInfo.setHash(hashedMsg);
		requestInfo.setLength(RandomByteArray.length);

		System.out.println(requestInfo.toString());

		return Response.status(200).entity(requestInfo).build();
	}

	public static byte[] decode(String string) {

		byte[] decode = Base64.getUrlDecoder().decode(string.getBytes());
		System.out.println("Array is decoded");
		return decode;
	}
}
