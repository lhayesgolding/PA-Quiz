/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
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

    @FXML
    private Label setName;
    @FXML
    private Label setUsername;
    @FXML
    private Label setEmail;
    @FXML
    private ListView<String> lvTestScores;
    private ArrayList<Test> tests = new ArrayList();
    private int testIndex;
    private ObservableList<String> listItems = FXCollections.observableArrayList();

    public void handleLogoutButton(ActionEvent event) throws IOException {
        showLogInPage(event);
    }

    public void handleTestButton(ActionEvent event) throws IOException {
        startNewTest(event);
    }
    
    public void handleCompareScoresButton(ActionEvent event) throws IOException {
        showCompareScoresPage(event);
    }
    
    public void handleReviewTestButton(ActionEvent event) throws IOException{
      goToReviewTest(event);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateUserFields();
      try {
        handlePastUserTest();
      } catch (IOException ex) {
        Logger.getLogger(FXMLUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
        populateTests();
      } catch (Exception ex) {
        Logger.getLogger(FXMLUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public void showLogInPage(ActionEvent event) throws IOException {
        Parent logInParent = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
    
    public void startNewTest(ActionEvent event) throws IOException {
        Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
        Scene startPageScene = new Scene(startPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(startPageScene);
        window.show();
    }
    
    public void showCompareScoresPage(ActionEvent event) throws IOException {
        Parent compareScoresParent = FXMLLoader.load(getClass().getResource("FXMLComparePage.fxml"));
        Scene compareScoresScene = new Scene(compareScoresParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(compareScoresScene);
        window.show();
    }
    
    public void goToReviewTest(ActionEvent event) throws IOException{
        Project.setTest(tests.get(testIndex));
        Parent reviewPageParent = FXMLLoader.load(getClass().getResource("FXMLReviewTest.fxml"));      
        Scene startPageScene = new Scene(reviewPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(startPageScene);
        window.show();      
    }

    public void populateUserFields() {
        setName.setText(Project.getUsersName());
        setUsername.setText(Project.getUserID());
        setEmail.setText(Project.getUserEmail());
    }
  
    public void populateTests() throws Exception{
    String userID = Project.getUserID();
    File testsFile = new File("src/datafiles/"+userID+"Tests.json");
    
    if(testsFile.exists()){
      JSONReader jsonReader = new JSONReader();
      ArrayList<JSONUserTestObject> userTests = jsonReader.readJSONUserTestFile(testsFile);
      int testCount = 1;
      for(int i = 0; i < userTests.size(); i++){
        Test test = new Test(userTests.get(i).getQuestions().size());
        test = test.retrievePastTest((JSONUserTestObject) userTests.get(i));
        tests.add(test);
        listItems.add("Test " + testCount);
        testCount++;
      }
      lvTestScores.setItems(listItems);
    }
    else
      lvTestScores.getItems().add("No past attempts");
  }
    
  public void handlePastUserTest() throws IOException{
    
    lvTestScores.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, 
                    String old_val, String new_val) {
                        testIndex = lvTestScores.getSelectionModel().getSelectedIndex();
                        System.out.println("Test Index: " + testIndex);
            }
        });
  }
}
