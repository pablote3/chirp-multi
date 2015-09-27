package com.rossotti.chirp.app;

import com.rossotti.chirp.store.UserStore;

public class MemoryStoreUtil {
	public static final UserStore usersStore = new UserStore(true);	
}