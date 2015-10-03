package com.rossotti.chirp.store;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rossotti.chirp.model.User;
import com.rossotti.chirp.store.exceptions.DuplicateEntityException;
import com.rossotti.chirp.store.exceptions.NoSuchEntityException;

/**
 * Data access object for users. Note that the user repository also manages bulk
 * deletion of users, which will be covered when modeling RESTful transactions.
 * This class is thread-safe.
 */
public class UserStore {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Map<String, User> users;

	public UserStore(boolean seedDatabase) {
		users = new ConcurrentHashMap<>();
		logger.trace("Created new UserRepository with new Users Map");

		if (seedDatabase) {
			UserStoreUtils.resetAndSeedRepository(this);
		}
	}

	/**
	 * Use this method to empty the current UserRepository.
	 */
	public synchronized void clear() {
		users = new ConcurrentHashMap<>();
	}

	public final User createUser(String username, String realName) {
		if (users.containsKey(username))
			throw new DuplicateEntityException(username);

		User user = new User(username, realName);
		users.put(username, user);
		return user;
	}

	public final void updateUser(User user) {
		users.put(user.getUsername(), user);
	}

	public final Deque<User> getUsers() {
		return new LinkedList<>(users.values());
	}

	public final User getUser(String username) {
		User user = users.get(username);
		if (user == null)
			throw new NoSuchEntityException();

		return user;
	}

	public final void deleteUser(String username) {
		if (users.remove(username) == null)
			throw new NoSuchEntityException();
	}
}
