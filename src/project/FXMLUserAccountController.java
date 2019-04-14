/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
    private ListView lvTestScores;

    public void handleLogoutButton(ActionEvent event) throws IOException {
        showLogInPage(event);
    }

    public void handleTestButton(ActionEvent event) throws IOException {
        startNewTest(event);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateUserFields();
        try {
            populatePastTests();
        } catch (IOException ex) {
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
    
    public void populatePastTests() throws FileNotFoundException, IOException {
        String userID = Project.getUserID();
        File scoreFile = new File("src/datafiles/" + userID + ".txt");
        if (scoreFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(scoreFile));
            String score = reader.readLine();
            while (score != null) {
                lvTestScores.getItems().add(score);
                score = reader.readLine();
            }
        } else {
            lvTestScores.getItems().add("No past scores");
        }
    }

    public void populateUserFields() {
        setName.setText(Project.getUsersName());
        setUsername.setText(Project.getUserID());
        setEmail.setText(Project.getUserEmail());
    }
}
