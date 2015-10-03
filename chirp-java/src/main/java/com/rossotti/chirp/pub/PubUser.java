package com.rossotti.chirp.pub;

import java.net.URI;

public class PubUser {

	private final URI self;
	private final URI parent;
  	private final String username;
  	private final String realname;

    public PubUser() {
  	  self = null;
  	  parent = null;
  	  username = null;
  	  realname = null;
    }  	

  	public PubUser(URI self, URI parent, String username, String realname) {
  		super();
  		this.self = self;
  		this.parent = parent;
  		this.username = username;
  		this.realname = realname;
  	}
  	
	public URI getSelf() {
		return self;
	}
		
	public URI getParent() {
		return parent;
	}
		
	public String getUsername() {
		return username;
	}
		
	public String getRealname() {
		return realname;
	}	  
}