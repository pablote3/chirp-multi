package com.rossotti.chirp.app.resources;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rossotti.chirp.model.User;

@Path("/hello")
public class HelloResource {

	@GET
	@Path("/{name}/{age}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloTextPathParam(@PathParam("name") String name, @PathParam("age") int age) {
		return "Hello " + name + ", welcome to " + age;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHelloTextQueryParam(@QueryParam("name") String name, @QueryParam("age") String ageString) {
		try {
			int age = (ageString == null) ? 0 : Integer.valueOf(ageString);
			String msg = (name == null) ? "Hello!" : "Hello " + name + " welcome to " + age;
			return Response.ok(msg).build();

		} catch (NumberFormatException e) {
			throw new BadRequestException("I need an int silly", e);
		}
	}
	
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHelloUserPathParam(@PathParam("name") String name){
    	User user = new User(name, "Mr. " + name);
        return Response.status(200).entity(user).build();
    } 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHelloUserQueryParam(@QueryParam("name") String name){
    	User user = new User(name, "Mr. " + name);
        return Response.status(200).entity(user).build();
    } 
}