package org.elsys.netprog.rest;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/game")
public class GameController {

	private byte[] array = RandomByteArray.byteArr;
	private String hashedMsg = RandomByteArray.hash;
	

	
	@POST
	@Path("/s")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame(String information) throws URISyntaxException, JSONException {
		System.out.println("Answer from post");
		
		JSONObject obj = new JSONObject(information);
		
		byte[] decodedArr = decode(obj.getString("encodedBytes"));
		if(obj.getString("hash").equals(hashedMsg) &&
				Arrays.equals(decodedArr, array))  {
			System.out.println("Hashed strings are equal");
			RandomByteArray arrayGenerator = new RandomByteArray();
			System.out.println("New array generated");

			return Response.status(200).entity("ok").build();
		}
		else {
			System.out.println("HASH FALSE");
			return Response.status(406).entity("error").build();
		}
			
	}
	
	
	@GET
	@Path("/g")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() throws JSONException {
		
		InfoGET requestInfo =  new InfoGET();
		
		System.out.println(array);
		System.out.println(hashedMsg);

		
		
		requestInfo.setHash(hashedMsg);
		requestInfo.setLength(RandomByteArray.length);
		System.out.println(requestInfo.toString());
		return Response.status(200).entity(requestInfo).build();
	}

public  static byte[] decode(String string) {
		
		byte[] decode = Base64.getUrlDecoder().decode(string.getBytes());
		System.out.println(decode);
		
		return decode;
	}
}
