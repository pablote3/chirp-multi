package com.rossotti.chirp.app;

import com.rossotti.chirp.store.memory.InMemoryUsersStore;

public class MemoryStoreUtil {
	public static final InMemoryUsersStore usersStore = new InMemoryUsersStore(true);	
}