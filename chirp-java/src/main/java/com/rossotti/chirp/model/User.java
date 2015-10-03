package com.rossotti.chirp.model;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.rossotti.chirp.pub.PubUser;

/**
 * Entity representing a user of the "chirp" service. A user logically owns a
 * collection of chirps, indexed by id.
 */
public class User {

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

	public PubUser toPubUser(UriInfo uriInfo) {		
		URI userLink = uriInfo.getBaseUriBuilder().path("users").path(username).build();
		return new PubUser(userLink, null, this.username, this.realname);
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("username: " + this.username)
			.toString();
	}
}
