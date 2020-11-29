package com.gateway.demo;

import java.util.UUID;

public class User {
  String userName;
  String password;//todo hash it with jwt and bcrypt
  int id;

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
    id=0;
  }

  public String getUserName() {
    return userName;
  }

  public int getId() {
    return id;
  }
}

