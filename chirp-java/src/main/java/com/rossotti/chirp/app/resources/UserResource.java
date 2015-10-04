package com.rossotti.chirp.app.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.rossotti.chirp.app.MemoryStoreUtil;
import com.rossotti.chirp.model.User;
import com.rossotti.chirp.pub.PubUser;
import com.rossotti.chirp.pub.PubUsers;
import com.rossotti.chirp.store.UserStore;

@Path("/users")
public class UserResource {

	UserStore userStore = MemoryStoreUtil.userStore;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getUser(@Context UriInfo uriInfo,
    						@PathParam("username") String username) {

    	User user = userStore.getUser(username);
    	
    	URI self = uriInfo.getAbsolutePath();
    	URI parent = uriInfo.getAbsolutePathBuilder().path("..").build();
    	
    	PubUser pubUser = user.toPubUser(self, parent);
        return Response.ok(pubUser).build();
    }
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@Context UriInfo uriInfo) {
		Deque<User> que = userStore.getUsers();
		
		URI self = uriInfo.getAbsolutePath();
		URI parent = uriInfo.getAbsolutePathBuilder().path("..").build();
		
		List<PubUser> users = new ArrayList<>();
		for (User user : que) {
			PubUser pubUser = user.toPubUser(self, parent);
			users.add(pubUser);
		}
		
		PubUsers pubUsers = new PubUsers(self, users);
		return Response.ok(pubUsers).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {	
		userStore.createUser(user.getUsername(), user.getRealname());
		URI newLocation = UriBuilder.fromResource(UserResource.class).path(user.getUsername()).build();
		return Response.created(newLocation).build();
	}	
}
