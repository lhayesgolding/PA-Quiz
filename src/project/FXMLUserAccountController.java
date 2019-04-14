/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import jsonparser.JSONReader;

/**
 * FXML Controller class
 *
 * @author ConorLaptop
 */
public class FXMLUserAccountController implements Initializable {
  
  @FXML private Label setName;
  @FXML private Label setUsername;
  @FXML private Label setEmail;
  @FXML private ListView lvTestScores;
  
  public void handleLogoutButton(ActionEvent event) throws IOException{
    Parent testPageParent = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
    Scene testPageScene = new Scene(testPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(testPageScene);
    window.show();  
  }
  
  public void handleTestButton (ActionEvent event) throws IOException{
    Parent testPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
    Scene testPageScene = new Scene(testPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(testPageScene);
    window.show();  
  }
  
  public void populateUserTests() throws Exception{
    String userID = Project.getUserID();
    File testsFile = new File("src/datafiles/"+userID+"Tests.json");
    File scoresFile = new File("src/datafiles/"+userID+".txt");
    ArrayList userScores = new ArrayList();
    
    if(testsFile.exists() && scoresFile.exists()){
      JSONReader jReader = new JSONReader();
      ArrayList userTests = jReader.readJSONUserTestFile(testsFile);
      BufferedReader bReader = new BufferedReader(new FileReader(scoresFile));
      String score = bReader.readLine();
      while(score != null){
        userScores.add(score);
        score = bReader.readLine();
      }
      
      int testCount = 1;
      for(int i = 0; i < userTests.size(); i++){
        StringBuilder builder = new StringBuilder();
        builder.append("Test").append(testCount).append(": ").toString();
        builder.append("   ").append("Score: ").append(userScores.get(i));
        String listRow = builder.toString();
        lvTestScores.getItems().add(listRow);
        testCount++;
      }
    }
    else
      lvTestScores.getItems().add("No past scores");
  }
  
  public void populateUserFields(){
    setName.setText(Project.getUsersName());
    setUsername.setText(Project.getUserID());
    setEmail.setText(Project.getUserEmail());
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    populateUserFields();
    try {
      populateUserTests();
    } catch (Exception ex) {
      Logger.getLogger(FXMLUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }  
  
}
