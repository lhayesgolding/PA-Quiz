/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.Project;

/**
 * FXML Controller class
 *
 * @author jthre_000
 */
public class FXMLComparePageController implements Initializable {

    @FXML
    private Label setAverageAll, setAverageUser;
    @FXML
    private Text txtUserScores;
    @FXML
    private ListView lvUserScores;
    @FXML
    private ListView lvPastScores;
    
    /**
     * Goes back to the previous screen
     * @param event indicates that the user has pressed a button
     * @throws IOException thrown when I/O error occurs
     */
    public void handleBackButton(ActionEvent event) throws IOException {
        showUserAccountPage(event);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populatePastTests();
            populateAllTests();
            populateAverage(Project.getUserID());
            populateAverage("all");
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     * Displays the user account page
     * @param event indicates that the user has pressed a button
     * @throws IOException thrown when I/O error occurs
     */
    public void showUserAccountPage(ActionEvent event) throws IOException {
        Parent testPageParent = FXMLLoader.load(getClass().getResource("/project/FXMLUserAccountPage.fxml"));
        Scene userAccountScene = new Scene(testPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(userAccountScene);
        window.setTitle("User Account");
        window.show();
    }
    
    /**
     * Gets the current user's past test scores and displays them, up to the last 
     * 50 tests
     * @throws FileNotFoundException thrown when the file is not found
     * @throws IOException thrown when I/O error occurs
     */
    public void populatePastTests() throws FileNotFoundException, IOException {
        String userID = Project.getUserID();
        File scoreFile = new File("src/datafiles/" + userID + ".txt");
        if (scoreFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(scoreFile));
            String score = reader.readLine();
            while (score != null) {
                score = score + "%";
                lvUserScores.getItems().add(score);
                score = reader.readLine();
            }
        } else {
            lvUserScores.getItems().add("No past scores");
        }
    }
    
    /**
     * Gets the 50 most recent test scores and displays them
     * @throws FileNotFoundException thrown when the file is not found
     * @throws IOException thrown when I/O error occurs
     */
    public void populateAllTests() throws FileNotFoundException, IOException {
        File file = new File("src/datafiles/AllScores.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String score = reader.readLine();
            while (score != null) {
                score = score + "%";
                lvPastScores.getItems().add(score);
                score = reader.readLine();
            }
        } else {
            lvPastScores.getItems().add("No past scores");
        }
    }
    
    /**
     * Shows the average of the user's last tests, as well as the past tests
     * of every user
     * @param user the currently logged in user
     */
    public void populateAverage(String user) {
        Double average = 0.0;
        ArrayList<String> scores = new ArrayList<String>();
        String currentScore;
        File scoreFile;
        if (user.equals("all")) {;
            scoreFile = new File("src/datafiles/AllScores.txt");
        }
        else
            scoreFile = new File("src/datafiles/" + user + ".txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            while ((currentScore = br.readLine()) != null) {
                scores.add(currentScore);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLComparePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLComparePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int total = 0;
        for (int i = 0; i < scores.size(); i++) {
            total += Integer.valueOf(scores.get(i));
        }
        average = total / Double.valueOf(scores.size());
        if (user.equals("all"))
            setAverageAll.setText(String.format("%.2f", average) + "%");
        else {
            setAverageUser.setText(String.format("%.2f", average) + "%");
            txtUserScores.setText(user + "'s Scores");
        }
    }
}
