package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Test;
import project.Project;

/**
 * FXML Controller class
 *
 * @author lizhayes-golding
 */
public class FXMLStartPageController implements Initializable {

  @FXML private ChoiceBox cbNumQuestions, cbTestType;
  @FXML private Button btStart, btAccount;
  @FXML private Label lbNumQuestions, lbTestType;
  @FXML private Label helloMessage;

  /**
   * Starts the test with the number of questions the user requests
   *
   * @param event indicates that the Start Test button has been pressed
   * @throws IOException thrown when I/O error occurs
   */
  @FXML
  public void handleStartButton(ActionEvent event) throws IOException {
    Project.setNumOfQuestions((int) cbNumQuestions.getValue());
    startNewTest(event, (String) cbTestType.getValue());
  }

  public void handleAccountButton(ActionEvent event) throws IOException {
    showUserAccountPage(event);
  }

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cbNumQuestions.getItems().addAll(10, 20, 30, 40, 50);
    cbNumQuestions.setValue(10);
    cbTestType.getItems().addAll("Recorded", "Practice");
    cbTestType.setValue("Recorded");
    helloMessage.setText("Hello, " + Project.getUsersName());
  }

  /**
   * Starts a new test
   *
   * @param event indicates that a button has been pressed
   * @param testType determines if the test is recorded or practice
   * @throws IOException thrown if I/O error occurs
   */
  public void startNewTest(ActionEvent event, String testType) throws IOException {
    Test.setTestType(testType);
    Parent documentParent = FXMLLoader.load(getClass().getResource("/project/FXMLDocument.fxml"));
    Scene documentScene = new Scene(documentParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(documentScene);
    window.setTitle("Take Test");
    window.show();
  }

  /**
   * Shows the user account screen
   *
   * @param event indicates that a button has been pressed
   * @throws IOException thrown if I/O error occurs
   */
  public void showUserAccountPage(ActionEvent event) throws IOException {
    Parent testPageParent =
        FXMLLoader.load(getClass().getResource("/project/FXMLUserAccountPage.fxml"));
    Scene userAccountScene = new Scene(testPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(userAccountScene);
    window.setTitle("User Account");
    window.show();
  }
}
