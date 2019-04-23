/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PasswordHashing;
import model.User;
import project.Project;

/**
 * FXML Controller class
 *
 * @author lizri
 */
public class FXMLCreateAccountController implements Initializable {

  @FXML Button btSubmit, btReturn;
  @FXML TextField nameField;
  @FXML TextField emailField;
  @FXML TextField usernameField;
  @FXML PasswordField passwordField;
  @FXML Text errorMessage;
  @FXML PasswordField verifyPasswordField;
  @FXML Text nameAst;
  @FXML Text emailAst;
  @FXML Text usernameAst;
  @FXML Text passwordAst;
  @FXML Text password2Ast;
  private User newuser;
  private PasswordHashing ph = new PasswordHashing();

  /**
   * @param event indicates that the Submit button has been pressed
   * @throws IOException thrown when I/O error occurs
   * @throws java.security.NoSuchAlgorithmException
   */
  @FXML
  public void handleSubmit(ActionEvent event) throws IOException, NoSuchAlgorithmException {
    nameAst.setVisible(false);
    emailAst.setVisible(false);
    usernameAst.setVisible(false);
    passwordAst.setVisible(false);
    password2Ast.setVisible(false);
    errorMessage.setVisible(false);

    String name = nameField.getText();
    String email = emailField.getText();
    String username = usernameField.getText();
    String password = passwordField.getText();
    String password2 = verifyPasswordField.getText();

    boolean errorExists = errorCheck(name, email, username, password, password2);

    if (!errorExists) {
      addNewUser(name, email, username, password);
      startNewTest(event);
    }
  }

  /**
   * @param event indicates that the Return button has been pressed
   * @throws IOException thrown when I/O error occurs
   */
  @FXML
  public void handleReturn(ActionEvent event) throws IOException {
    showLogInPage(event);
  }

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  /**
   * Checks if the entered information is valid
   *
   * @param name the name of the user
   * @param email the email of the user
   * @param username the username of the user
   * @param password the password of the user
   * @param password2 confirms that the password was entered correctly
   * @return true or false based on if information entered is valid
   */
  public boolean errorCheck(
      String name, String email, String username, String password, String password2) {
    if (name.isEmpty()
        || email.isEmpty()
        || username.isEmpty()
        || password.isEmpty()
        || password2.isEmpty()) {

      if (name.isEmpty()) {
        nameAst.setVisible(true);
      }
      if (email.isEmpty()) {
        emailAst.setVisible(true);
      }
      if (username.isEmpty()) {
        usernameAst.setVisible(true);
      }
      if (password.isEmpty()) {
        passwordAst.setVisible(true);
      }
      if (password2.isEmpty()) {
        password2Ast.setVisible(true);
      }

      errorMessage.setText("must complete all fields!");
      errorMessage.setVisible(true);
      return true;
    } else if (Project.existingUser(username)) {
      usernameAst.setVisible(true);
      errorMessage.setText("username already exists");
      errorMessage.setVisible(true);
      return true;
    } else if (!(password.equals(password2))) {
      errorMessage.setText("passwords do not match");
      errorMessage.setVisible(true);
      passwordAst.setVisible(true);
      password2Ast.setVisible(true);
      return true;
    }
    return false;
  }

  /**
   * @param name the name of the user
   * @param email the email of the user
   * @param username the username of the user
   * @param password the password of the user
   * @throws NoSuchAlgorithmException thrown if the hashPassword function fails
   * @throws FileNotFoundException thrown if there is now file found
   */
  public void addNewUser(String name, String email, String username, String password)
      throws NoSuchAlgorithmException, FileNotFoundException {
    password = ph.hashPassword(password);
    newuser = new User(name, email, username, password);
    Project.addNewUser(newuser);
    Project.setUsername(username);
  }

  /**
   * Starts a new test for the newly entered user
   *
   * @param event indicates that the user has pressed a button
   * @throws IOException thrown when I/O error occurs
   */
  public void startNewTest(ActionEvent event) throws IOException {
    Parent startPageParent = FXMLLoader.load(getClass().getResource("/project/FXMLStartPage.fxml"));
    Scene startPageScene = new Scene(startPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(startPageScene);
    window.setTitle("Start Test");
    window.show();
  }

  /**
   * Returns to the log in page
   *
   * @param event indicates that the user has pressed a button
   * @throws IOException thrown when I/O error occurs
   */
  public void showLogInPage(ActionEvent event) throws IOException {
    Parent logInParent = FXMLLoader.load(getClass().getResource("/project/FXMLLogIn.fxml"));
    Scene logInScene = new Scene(logInParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logInScene);
    window.setTitle("Log In");
    window.show();
  }
}
