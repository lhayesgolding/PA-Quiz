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
    @FXML private TextField passwordfield;
    @FXML private TextField userfield;
    @FXML private Text invalidlogin;
    private HashMap<String,User> usermap = new HashMap<String,User>();
    final private File userfile = new File("src/datafiles/userstorage.txt");
    //private FileInputStream FIS;
    //private FileOutputStream FOS;
    
    @FXML
    public void handleLogIn(ActionEvent event) throws IOException {
        
            String username = "";
            String password = "";
            if (userfield.getText() != null) username = userfield.getText();
            System.out.println("username: " + username);
            if (passwordfield.getText() != null) password = passwordfield.getText();
            System.out.println("password: " + password);
            if(username.equals(""))
                invalidlogin.setVisible(true);
            if(!username.equals("") && !password.equals("")){
                if (usermap.containsKey(username)) {
                    if (valid(usermap.get(username),password)){
                        Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
                        Scene startPageScene = new Scene(startPageParent);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(startPageScene);
                        window.show();

                        //FIS.close();
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
        try{
            //FIS = new FileInputStream(userfile);
            //FOS = new FileOutputStream(userfile);
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
    
    private boolean valid(User tempuser, String password){
        
        if (tempuser.getPassword().equals(password)) return true;
        else return false;
    }
    
}
