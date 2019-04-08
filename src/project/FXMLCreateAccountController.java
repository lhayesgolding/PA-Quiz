/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    
    @FXML
    public void handleSubmit(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        User newuser;
        PasswordHashing ph = new PasswordHashing();
        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText().trim();
        System.out.println("Create account unhashed password: " + password);
        password = ph.hashPassword(password).toString();
        System.out.println("Create account hashed password: " + password);
        
        if (!Project.existingUser(username)){
            
            newuser = new User(name,email,username,password);
            Project.addNewUser(newuser);
            Project.setUsername(username);
            Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
            Scene startPageScene = new Scene(startPageParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(startPageScene);
            window.show();
            
        }
        else
            errorMessage.setVisible(true);
        
    }

    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        Parent logInParent = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
    
//    public String hashPassword(String password) throws NoSuchAlgorithmException{
//      SecureRandom random = new SecureRandom();
//      byte[] salt = new byte[16];
//      random.nextBytes(salt);
//      
//      MessageDigest md = MessageDigest.getInstance("SHA-512");
//      md.update(salt);
//      
//      byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
//      
//      return hashedPassword.toString();
//    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
