package com.rossotti.chirp.resources;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

public class HelloResourceTest {
	
	@Before
	 public void setUp(){
		RestAssured.baseURI = "http://localhost:8080/chirp-web/rest";
	 }

	@Test
	public void testTextPathParam() {
		expect().
			statusCode(200).
		when().
			get("/hello/html/Eric%20Melvin/40");
	}
	
	@Test
	public void testTextQueryParam() {
		given().
			queryParam("name", "Eric Melvin").
			queryParam("age", "40").
		expect().
			statusCode(200).
		when().
			get("/hello/html");
	}
	
	@Test
	public void testJsonUserPathParam() {
		expect().
			statusCode(200).
			defaultParser(Parser.JSON).
			body("username", equalTo("Eric%20Melvin")).
			body("realname", equalTo("Dr. Eric%20Melvin")).
		when().
			get("/hello/json/Eric%20Melvin");
	}
	
	@Test
	public void testJsonUserQueryParam() {
		given().
			queryParam("name", "Eric Melvin").
		expect().
			statusCode(200).
			defaultParser(Parser.JSON).
			body("username", equalTo("Eric Melvin")).
			body("realname", equalTo("Mr. Eric Melvin")).
		when().
			get("/hello/json");
	}
}
