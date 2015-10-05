package com.rossotti.chirp.pub;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PubUser {

	private final URI self;
  	private final String username;
  	private final String realname;
  	
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<URI> chirpLinks = new ArrayList<>();
  	
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<PubChirp> chirps = new ArrayList<>();

    public PubUser() {
  	  self = null;
  	  username = null;
  	  realname = null;
    }  	
  	
  	public PubUser(URI self, String username, String realname) {
  		super();
  		this.self = self;
  		this.username = username;
  		this.realname = realname;
  	}
  	
    public PubUser(URI self, String username, String realName, PubChirp... chirps) {
    	this(self, username, realName);
    	
    	if (chirps != null) {
    		Collections.addAll(this.chirps, chirps);
    	}
      }
      
      public PubUser(URI self, String username, String realName, URI... chirpLinks) {
    	this(self, username, realName);
    	
    	if (chirpLinks != null) {
    		Collections.addAll(this.chirpLinks, chirpLinks);
    	}
      }
  	
	public URI getSelf() {
		return self;
	}
		
	public String getUsername() {
		return username;
	}
		
	public String getRealname() {
		return realname;
	}	  
}