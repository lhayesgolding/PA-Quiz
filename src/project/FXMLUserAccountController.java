/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ConorLaptop
 */
public class FXMLUserAccountController implements Initializable {
  
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
  
  public void populatePastTests(ArrayList userScores){
    
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
