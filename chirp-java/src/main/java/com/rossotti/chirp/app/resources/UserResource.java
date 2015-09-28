package com.rossotti.chirp.app.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.rossotti.chirp.app.MemoryStoreUtil;
import com.rossotti.chirp.model.User;
import com.rossotti.chirp.store.UserStore;

@Path("/users")
public class UserResource {

	UserStore userStore = MemoryStoreUtil.userStore;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserJson(@PathParam("username") String username) {
    	User user = userStore.getUser(username);
        return Response.status(200).entity(user).build();
    } 
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUserJson(User user) {	
		userStore.createUser(user.getUsername(), user.getRealname());
		URI newLocation = UriBuilder.fromResource(UserResource.class).path(user.getUsername()).build();
		return Response.created(newLocation).build();
	}	
}