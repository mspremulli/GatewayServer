package user;

public class User {
  private String email;
  private String password;//todo hash it with jwt and bcrypt
  private int id;

  public User(String userName, String password, int id) {
    this.email = userName;
    this.password = password;
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }
}

