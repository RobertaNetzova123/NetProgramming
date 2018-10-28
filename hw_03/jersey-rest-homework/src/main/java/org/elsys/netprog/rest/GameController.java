package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
public class GameController {

	ConcurrentHashMap map = new ConcurrentHashMap<String, String>();
	static private HashMap hmap = new HashMap<String, String>();

	@POST
	@Path("/startGame")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response startGame() throws URISyntaxException {
		String uniqueID = UUID.randomUUID().toString();
		Games newGame = new Games(uniqueID);
		hmap.put(newGame.getID(), newGame.getSecretNumber());

		return Response
				.status(201)
				.entity(System.lineSeparator() + "size:   " + hmap.size()
						+ System.lineSeparator() + "ID:  " + newGame.getID()
						+ System.lineSeparator() + "Num:   "
						+ newGame.getSecretNumber()).build();

	}

	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response guess(@PathParam("id") String gameId,
			@PathParam("guess") String guess) throws Exception {
		// TODO: Add your code here
//		if (!hmap.containsKey(gameId))
////			return Response.status(404).entity(System.lineSeparator() + "ID ----WRONG ").build();
	
//		if (!gameId.equals("30")) return Response.status(404).entity(System.lineSeparator() + "ID ----WRONG ").build();
		
		HashSet<Character> uniquecharset = new HashSet();
		String number = hmap.get(gameId).toString();
		boolean result = false;
		for (int i = 0; i < number.length(); i++) {
			result = uniquecharset.add(number.charAt(i));
			if (result == false) {
				break;
			}
		}
//		if (UniqueNumber((String) hmap.get(gameId)) == false)
		if (result  == false) return Response.status(400).entity(System.lineSeparator() + "NUMBER -----WRONG").build();
		return Response.status(200)
				.entity(System.lineSeparator() + "OK---------- ")
				.build();

	}

 public boolean UniqueNumber(String number) {

		HashSet<Character> uniquecharset = new HashSet();
		boolean result = true;
		for (int i = 0; i < number.length(); i++) {
			result = uniquecharset.add(number.charAt(i));
			if (result == false) {
				break;
			}
		}
		return result;
	}

	@GET
	@Path("/games")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getGames() {
		// TODO: Add your code here
		return Response.status(404).build();
	}
}
