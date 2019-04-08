/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
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
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * FXML Controller class
 *
 * @author lizri
 */
public class FXMLLogInController implements Initializable {
    
    @FXML private Button btLogIn, btCreateAccount;
    @FXML private PasswordField passwordfield;
    @FXML private TextField userfield;
    @FXML private Text invalidlogin;
    final private File userfile = new File("src/datafiles/userstorage.txt");
   
    
    @FXML
    public void handleLogIn(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        
        String username = "";
        String password = "";
        PasswordHashing ph = new PasswordHashing();
        if (userfield.getText() != null) username = userfield.getText();
        System.out.println("username: " + username);
        if (passwordfield.getText() != null) password = ph.hashPassword(passwordfield.getText());
        System.out.println("password: " + password);
        if(username.equals("") || password.equals(""))
            invalidlogin.setVisible(true);
        if(!username.equals("") && !password.equals("")){
            if (Project.getusermap().containsKey(username)) {
                if (Project.valid(username, password)){
                    Project.setUsername(username);
                    Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
                    Scene startPageScene = new Scene(startPageParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(startPageScene);
                    window.show();  
                }
                else
                    invalidlogin.setVisible(true);
            }
            else
                invalidlogin.setVisible(true);
        }
    }
    
    @FXML
    public void handleCreateAccount(ActionEvent event) throws IOException {
        Parent createAccountParent = FXMLLoader.load(getClass().getResource("FXMLCreateAccount.fxml"));
        Scene createAccountScene = new Scene(createAccountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(createAccountScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Project.initializeUserMap();
        }    
    
    
}
