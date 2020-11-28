package com.gateway.demo;

import java.util.UUID;

public class User {
  String userName;
  String password;//todo hash it with jwt and bcrypt
  UUID userID;

  public User(String userName, String password, UUID userID) {
    this.userName = userName;
    this.password = password;
    this.userID = userID;
  }

  public String getUserName() {
    return userName;
  }

  public UUID getUserID() {
    return userID;
  }
}

