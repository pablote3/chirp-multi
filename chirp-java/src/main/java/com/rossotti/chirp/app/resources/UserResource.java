package com.rossotti.chirp.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rossotti.chirp.app.MemoryStoreUtil;
import com.rossotti.chirp.model.User;
import com.rossotti.chirp.store.memory.InMemoryUsersStore;

@Path("/users")
public class UserResource {

	InMemoryUsersStore usersStore = MemoryStoreUtil.usersStore;

    @GET
    @Path("/print")
    @Produces(MediaType.APPLICATION_JSON)
    public Response produceJsonUser(@QueryParam("username") String username,
    								@QueryParam("realname") String realname) {
    	User user = new User(username, realname);
        return Response.status(200).entity(user).build();
    } 
	
	@PUT
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJsonUser(User user) {		
		String output = user.toString();
		return Response.status(200).entity(output).build();
	}	
}