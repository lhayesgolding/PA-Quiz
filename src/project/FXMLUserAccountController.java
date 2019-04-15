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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import jsonparser.JSONReader;
import jsonparser.JSONUserTestObject;

/**
 * FXML Controller class
 *
 * @author ConorLaptop
 */
public class FXMLUserAccountController implements Initializable {
  
  @FXML private Label setName;
  @FXML private Label setUsername;
  @FXML private Label setEmail;
  @FXML private ListView lvTests;
  @FXML private Button btReviewTest;
  
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
  
//  @FXML
//  public void handlePastUserTest(ActionEvent event) throws IOException{
//    lvTestScores.getSelectionModel().selectedItemProperty().addListener(
//            new ChangeListener<String>() {
//                public void changed(ObservableValue<? extends String> ov, 
//                    String old_val, String new_val) {
//                        int index = lvTestScores.getSelectionModel().getSelectedIndex();
//                        System.out.println(new_val);
//            }
//        });
//
//    System.out.println(lvTestScores.getSelectionModel().getSelectedIndex());
//  }
  
    public void populateTests() throws Exception{
    String userID = Project.getUserID();
    File testsFile = new File("src/datafiles/"+userID+"Tests.json");
    File scoresFile = new File("src/datafiles/"+userID+".txt");
    ObservableList<Test> listItems = FXCollections.observableArrayList();
    
    if(testsFile.exists() && scoresFile.exists()){
      JSONReader jsonReader = new JSONReader();
      ArrayList<JSONUserTestObject> userTests = jsonReader.readJSONUserTestFile(testsFile);
      for(int i = 0; i < userTests.size(); i++){
        Test test = new Test(userTests.get(i).getQuestions().size());
        test = test.retrievePastTest((JSONUserTestObject) userTests.get(i));
        listItems.add(test);
      }
      lvTests.setItems(listItems);
    }
    else
      lvTests.getItems().add("No past scores");
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
      populateTests();
    } catch (Exception ex) {
      Logger.getLogger(FXMLUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }  
  
}
