/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** @author jthre_000 */
public class Project extends Application {
    
    private static int numOfQuestions = 10;
    private static Test test = new Test();
    private static String Username;
    final static private File userfile = new File("src/datafiles/userstorage.txt");
    private static HashMap<String,User> usermap = new HashMap<String,User>();
 
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception thrown if there is an exception 
     * during runtime
     */
    public static void main(String[] args) throws Exception {
       launch(args);
    }
    
    /**
     * Returns numOfQuestions
     * @return the number of questions
     */
    public static int getNumOfQuestions(){
        return numOfQuestions;
    }
    
    /**
     * Sets numOfQuestions
     * @param number the number of questions
     */
    public static void setNumOfQuestions(int number){
        numOfQuestions = number;
    }
    
    /**
     * Returns test
     * @return the test
     */
    public static Test getTest() {
        return test;
    }
    
    /**
     * Sets test
     * @param test0 the test to set test to
     */
    public static void setTest(Test test0) {
        test = test0;
    }
    
    public static void setUsername(String username){
        Username = username;
    }
    
    public static String getCurrentUser(){
        return Username;
    }
    
    public static void addNewUser(User newUser){
        try {
            try (FileWriter writer = new FileWriter(userfile,true)) {
                writer.append(newUser.getName());
                writer.append('\n');
                writer.append(newUser.getEmail());
                writer.append('\n');
                writer.append(newUser.getUserID());
                writer.append('\n');
                writer.append(newUser.getPassword());
                writer.append('\n');
            }
        } catch (IOException ex) {
            System.out.println("error writing to userstorage.txt");
        }
        User usertemp;
        try {
            usertemp = new User(newUser.getName(), newUser.getEmail(), newUser.getUserID(), newUser.getPassword());
            usermap.put(newUser.getUserID(), usertemp);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initializeUserMap(){
        try{
        Scanner scan = new Scanner(userfile);
            String name;
            String email;
            String userID;
            String password;
            
            while (scan.hasNext()){
                name = scan.nextLine();
                System.out.println("name: " + name);
                email = scan.nextLine();
                System.out.println("email: " + email);
                userID = scan.nextLine();
                System.out.println("userID: " + userID);
                password = scan.nextLine();
                System.out.println("password: " + password);
                
                User usertemp = new User(name,email,userID,password);
                usermap.put(userID, usertemp);
                
            }
        
        
        }
        catch (IOException except){
            System.out.println("there was an IOException when trying to access the userstorage file");
        }
    }
    
    public static boolean valid(String username, String password){
        if (usermap.get(username).getPassword().equals(password)) return true;
        else return false;
    }
    public static HashMap getusermap(){
        return usermap;
    }
    public static boolean existingUser(String possibleUser){
        boolean answer = (usermap.containsKey(possibleUser));
        return answer;
    }
    public static String getUsersName(){
        String name = usermap.get(Username).getName();
        return name;
    }
    public static String getUserID(){
      String userID = usermap.get(Username).getUserID();
      return userID;
    }
    public static String getUserEmail(){
      String email = usermap.get(Username).getEmail();
      return email;
    }
}
    

