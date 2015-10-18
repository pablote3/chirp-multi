package com.rossotti.chirp.resources;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.rossotti.chirp.model.User;
import com.rossotti.chirp.store.UserStoreUtils;

public class UserResourceTest extends ResourceTestSupport {

	@Before
	public void beforeTest() {
		getUserStore().clear();
		RestAssured.baseURI = "http://localhost:8080/chirp-web/rest";
	}
	
	@Test
	public void getUser() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		
		expect().
			statusCode(200).
			defaultParser(Parser.JSON).
			body("realname", equalTo("Darth Vader")).
		when().
			get("/users/vader");
	}
	
	@Test
	public void getUser_NotFound() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		
		expect().
			statusCode(404).
		when().
			get("/users/lando");
	}
	
	@Test
	public void getUsers() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		
		expect().
			statusCode(200).
			defaultParser(Parser.JSON).
			body("users.username", hasItems("maul", "yoda")).
		when().
			get("/users");
	}
	
	@Test
	public void createUser() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		JsonObject json = ResourceTestSupport.buildJsonUser(new User("leia", "Leia Organa"));
		
		given().
			contentType(ContentType.JSON).
			body(json.toString()).
		expect().
			statusCode(201).
		when().
			put("/users");
	}
	
	@Test
	public void createUser_Duplicate() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		JsonObject json = ResourceTestSupport.buildJsonUser(new User("han", "Han Solo"));
		
		given().
			contentType(ContentType.JSON).
			body(json.toString()).
		when().
			put("/users");
		
		given().
			contentType(ContentType.JSON).
			body(json.toString()).
		expect().
			statusCode(403).
			when().
			put("/users");
	}

	@Test
	public void getChirpsForUser_Offset_None() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		
		expect().
			statusCode(200).
			defaultParser(Parser.JSON).
			body("chirps.content", hasItems("I spake!", "Ooh mooey mooey I love you!")).
			body("chirps", hasSize(4)).
		when().
			get("/users/jarjar/chirps");
	}
	
	@Test
	public void getChirpsForUser_Offset_20() {
		UserStoreUtils.resetAndSeedRepository(getUserStore());
		
		given().
			queryParam("offset", "20").
			queryParam("size", "10").
		expect().
			statusCode(200).
			defaultParser(Parser.JSON).
			body("chirps.content", hasItems("Yousa might'n be sayin dat", "Hello boyos.")).
			body("chirps", hasSize(4)).
		when().
			get("/users/jarjar/chirps");
	}
	
	@Test
	public void createUserChirp() {
		 UserStoreUtils.resetAndSeedRepository(getUserStore());
		
		given().
			contentType(ContentType.JSON).
			body("Feel the force!").
		expect().
			statusCode(201).
		when().
			post("/users/yoda/chirps");
	}
}