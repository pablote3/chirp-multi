package com.rossotti.chirp.store;

import com.rossotti.chirp.model.User;

import java.util.Deque;

public interface UsersStore {

  void clear();
  User createUser(String username, String fullName);
  User getUser(String username);

//  Deque<User> getUsers();

  void updateUser(User vader);
}
