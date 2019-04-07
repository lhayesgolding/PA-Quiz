package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

  private String name;
  private String email;
  private String userID;
  private String password;
  private ArrayList<Double> scores = new ArrayList<>();

  User() {
    name = "Guest";
    email = "None";
    userID = "Guest";
    password = "None";
  }

  User(String n, String e, String u, String p) throws FileNotFoundException {
    name = n;
    email = e;
    userID = u;
    password = p;
    
    File file = new File(userID + "Scores");
    //System.out.println("FILE: " + file.toString());
    //System.out.println("exists: " + file.exists());
    if (file.exists()) {
      Scanner s = new Scanner(file);
      while (s.hasNextLine()) {
          scores.add(Double.parseDouble(s.nextLine()));
      }
    }
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
  
  public void addScore(Test t) {
      double score = t.getScore();
      scores.add(score);
  }
  
  public void saveScores() throws FileNotFoundException, IOException {
      File file = new File(userID + "Scores");
      file.createNewFile();
      PrintWriter pw = new PrintWriter(new FileOutputStream(file));
      for (double s : scores)
      {
          pw.println(s);
      }
      pw.close();
  }
}
