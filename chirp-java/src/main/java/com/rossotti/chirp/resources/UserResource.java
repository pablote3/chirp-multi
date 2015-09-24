package com.rossotti.chirp.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.rossotti.chirp.app.MemoryStoreUtil;
import com.rossotti.chirp.store.UsersStore;

@Path("/users")
public class UserResource {

	UsersStore usersStore = MemoryStoreUtil.usersStore;
	
	@POST
	public Response createUser(@FormParam("username") String username, 
							   @FormParam("realName") String realName) {
		
		usersStore.createUser(username, realName);
		
		return Response.noContent().build();
	}
	
}