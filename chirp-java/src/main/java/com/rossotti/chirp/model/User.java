package com.rossotti.chirp.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.UriInfo;

import com.rossotti.chirp.pub.PubChirp;
import com.rossotti.chirp.pub.PubUser;
import com.rossotti.chirp.store.exceptions.DuplicateEntityException;
import com.rossotti.chirp.store.exceptions.NoSuchEntityException;

/**
 * Entity representing a user of the "chirp" service. A user logically owns a
 * collection of chirps, indexed by id.
 */
public class User {

	private String username;
	private String realname;
	private final Map<ChirpId, Chirp> chirps = new TreeMap<ChirpId, Chirp>();
	
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
	
	public Deque<Chirp> getChirps() {
		return new LinkedList<Chirp>(chirps.values());
	}

	public Chirp getChirp(ChirpId id) {
		Chirp chirp = chirps.get(id);
		if (chirp == null)
			throw new NoSuchEntityException();

		return chirp;
	}
	
	public Chirp createChirp(String content) {
		ChirpId chirpId = new ChirpId();
		if (chirps.containsKey(chirpId))
			throw new DuplicateEntityException(chirpId.toString());

		Chirp chirp = new Chirp(chirpId, content, this);
		chirps.put(chirpId, chirp);
		return chirp;
	}

	public Chirp createChirp(String content, String id) {
		ChirpId chripId = new ChirpId(id);
		if (chirps.containsKey(chripId)) {
			throw new DuplicateEntityException(id);
		}
		return addChirp(new Chirp(chripId, content, this));
	}
	
	public Chirp addChirp(Chirp chirp) {
		this.chirps.put(chirp.getId(), chirp);
		return chirp;
	}

	public PubUser toPubUser(UriInfo uriInfo) {
		URI userUri = uriInfo.getBaseUriBuilder().path("users").path(username).build();
		
		List<PubChirp> chirps = new ArrayList<>();
		for (Chirp chirp : getChirps()) {
			chirps.add(chirp.toPubChirp(uriInfo));
		}
		PubUser user = new PubUser(userUri, this.username, this.realname, chirps.toArray(new PubChirp[0]));
		return user; 
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("username: " + this.username)
			.toString();
	}
}
