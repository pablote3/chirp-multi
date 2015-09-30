package com.rossotti.chirp.model;

import java.net.URI;
import java.util.Arrays;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rossotti.chirp.pub.PubUser;

/**
 * Entity representing a user of the "chirp" service. A user logically owns a
 * collection of chirps, indexed by id.
 */
public class User {
	
	public static enum Variant {summary, full, abbreviated};

	private String username;
	private String realname;
	
	public User(){}
	
	public User(String username, String realname) {
		this.username = username;
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

	public PubUser toPubUser(String variantString, UriInfo uriInfo) {
		
		URI userLink = uriInfo.getBaseUriBuilder().path("users").path(username).build();
		
		Variant variant;
		
		// this would clean up a lot with a custom exception like HttpBadRequestException
		// and an exception mapper to handle it.
		
		try {
			// force the string to lower case to  be as liberal as possible
			variant = (variantString == null) ? null : Variant.valueOf(variantString.toLowerCase());
		
		} catch (IllegalArgumentException e) {
			String msg = String.format("The variant %s is not supported. Must be one of %s.", 
					variantString, 
					Arrays.asList(Variant.values()));
			Response response = Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
			throw new WebApplicationException(response);
		}
		
		if (variant == null || Variant.summary == variant) {
			return new PubUser(userLink, this.username, this.realname);

		} 
//		else if (Variant.full == variant) {
//			List<PubChirp> chirps = new ArrayList<>();
//			for (Chirp chirp : getChirps()) {
//				// TODO use a reference to uriInfo to build this link
//				URI self = URI.create("http://localhost:8080/chirps/" + chirp.getId());
//				chirps.add(chirp.toPubChirp(uriInfo));
//			}
//			PubUser user = new PubUser(userLink, this.username, this.realname, chirps.toArray(new PubChirp[0]));
//			return user; 			
//		} else if (Variant.abbreviated == variant) {
//			List<URI> links = new ArrayList<>();
//			for (Chirp chirp : getChirps()) {
//				// TODO use a reference to uriInfo to build this link
//				URI self = URI.create("http://localhost:8080/chirps/" + chirp.getId());
//				links.add(self);
//			}
//			PubUser user = new PubUser(userLink, this.username, this.realname, links.toArray(new URI[0]));
//			return user; 			
//		} 
		else {
			String msg = String.format("The variant %s is not yet supported.", variantString);
			Response response = Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
			throw new WebApplicationException(response);
		}
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("username: " + this.username)
			.toString();
	}
}
