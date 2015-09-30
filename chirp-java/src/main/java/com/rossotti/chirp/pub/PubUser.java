package com.rossotti.chirp.pub;

import java.net.URI;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

//ignore anything the client sends that I don't know about
@JsonIgnoreProperties(ignoreUnknown=true)
public class PubUser {

	private final URI self;
  	private final String username;
  	private final String realName;

//  	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//  	private final List<URI> chirpLinks = new ArrayList<>();
//  
//  	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//  	private final List<PubChirp> chirps = new ArrayList<>();

  	@JsonCreator
  	public PubUser(@JsonProperty("self") URI self, 
		  		   @JsonProperty("username") String username, 
		  		   @JsonProperty("realName") String realName) {
	  	super();
			this.self = self;
			this.username = username;
			this.realName = realName;
  		}
  
//  		public PubUser(URI self, String username, String realName, PubChirp... chirps) {
//	  		this(self, username, realName);
//	
//			if (chirps != null) {
//				Collections.addAll(this.chirps, chirps);
//			}
//  		}
  
//  		public PubUser(URI self, String username, String realName, URI... chirpLinks) {
//	  		this(self, username, realName);
//	
//			if (chirpLinks != null) {
//				Collections.addAll(this.chirpLinks, chirpLinks);
//			}
//  		}

//  		public List<URI> getChirpLinks() {
//  			return chirpLinks;
//  		}
//
//	  	public List<PubChirp> getChirps() {
//	  		return chirps;
//	  	}

		public URI getSelf() {
			return self;
		}
		
		public String getUsername() {
			return username;
		}
		
		public String getRealName() {
			return realName;
		}
	
	  
	}
