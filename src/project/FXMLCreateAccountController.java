/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    
    /**
     * 
     * @param event indicates that the Submit button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    @FXML
    public void handleSubmit(ActionEvent event) throws IOException {
        nameAst.setVisible(false);
        emailAst.setVisible(false);
        usernameAst.setVisible(false);
        passwordAst.setVisible(false);
        password2Ast.setVisible(false);
        errorMessage.setVisible(false);
        
        User newuser;
        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String password2 = verifyPasswordField.getText();
        
        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()
                || password2.isEmpty()){
            
            if (name.isEmpty()) nameAst.setVisible(true);
            if (email.isEmpty()) emailAst.setVisible(true);
            if (username.isEmpty()) usernameAst.setVisible(true);
            if (password.isEmpty()) passwordAst.setVisible(true);
            if (password2.isEmpty()) password2Ast.setVisible(true);
            
            errorMessage.setText("must complete all fields!");
            errorMessage.setVisible(true);
        }
        else if (Project.existingUser(username)){
            usernameAst.setVisible(true);
            errorMessage.setText("username already exists");
            errorMessage.setVisible(true);
        }
        else if(!(password.equals(password2))){
            errorMessage.setText("passwords do not match");
            errorMessage.setVisible(true);
            passwordAst.setVisible(true);
            password2Ast.setVisible(true);
        }
        else{
            
            
            newuser = new User(name,email,username,password);
            Project.addNewUser(newuser);
            Project.setUsername(username);
            Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
            Scene startPageScene = new Scene(startPageParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(startPageScene);
            window.show();
            
        }
        
    }
    
    
    /**
     *
     * @param event indicates that the Return button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        Parent logInParent = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb

     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
