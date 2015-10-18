package com.rossotti.chirp.resources;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import com.rossotti.chirp.app.MemoryStoreUtil;
import com.rossotti.chirp.model.User;
import com.rossotti.chirp.store.UserStore;

public class ResourceTestSupport {

	UserStore userStore = MemoryStoreUtil.userStore;
	
	public UserStore getUserStore() {
		return userStore;
	}
	
	public static JsonObject buildJsonUser(User user) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject value = factory.createObjectBuilder()
			.add("username", user.getUsername())
			.add("realname", user.getRealname())
//	     .add("address", factory.createObjectBuilder()
//	        .add("streetAddress", "21 2nd Street")
//	        .add("city", "New York")
//	        .add("state", "NY")
//	        .add("postalCode", "10021"))
//	     .add("phoneNumber", factory.createArrayBuilder()
//	        .add(factory.createObjectBuilder()
//	        .add("type", "home")
//	        .add("number", "212 555-1234"))
//	        .add(factory.createObjectBuilder()
//	        .add("type", "fax")
//	        .add("number", "646 555-4567")))
	    .build();
		return value;
	}
}