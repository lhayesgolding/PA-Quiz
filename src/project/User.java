package project;

public class User {

  private String name;
  private String email;
  private String userID;
  private String password;

  User() {
    name = "Guest";
    email = "None";
    userID = "Guest";
    password = "None";
  }

  User(String n, String e, String u, String p) {
    name = n;
    email = e;
    userID = u;
    password = p;
  }
  

  public String getName() {
    return name;
  }

  public void setName(String x) {
    name = x;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String x) {
    email = x;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String x) {
    userID = x;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String x) {
    password = x;
  }
}
