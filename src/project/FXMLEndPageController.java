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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lizri
 */
public class FXMLEndPageController implements Initializable {

    @FXML
    private Button btReviewTest, btViewAccount, btNewTest, btLogOut;
    @FXML
    private Label lbNumQuestions, lbNumQuestionsCorrect, lbPercentCorrect, lbTestResults;
    @FXML
    private TextArea reviewTextArea;

    Test test = new Test();

    /**
     * Shows the user the results of the test, with their answers and correct
     * answers
     *
     * @param event indicates that the Review Test button has been pressed
     */
    @FXML
    public void handleReviewTest(ActionEvent event) {
        String reviewText = createReviewText();
        reviewTextArea.setText(reviewText);
        reviewTextArea.setVisible(true);
    }

    /**
     * Starts new test
     *
     * @param event indicates that the Take New Test button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    @FXML
    public void handleNewTest(ActionEvent event) throws IOException {
        startNewTest(event);
    }

    /**
     * Logs the user out of the program
     *
     * @param event indicates that the Log Out button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        showLogInPage(event);
    }

    /**
     * Shows the user's account page
     * @param event indicates that a button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    @FXML
    public void handleUserAccount(ActionEvent event) throws IOException {
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
        lbTestResults.setText(Project.getUsersName() + "'s Test Results");
        test = Project.getTest();
        lbNumQuestions.setText(String.valueOf(test.getNumberOfQuestions()));
        lbNumQuestionsCorrect.setText(String.valueOf(test.getScore()));
        int percent = test.getScorePercent();
        lbPercentCorrect.setText(String.valueOf(percent) + "%");
    }

    /**
     * Creates a review of the test that the user took
     * @return the review of the test
     */
    public String createReviewText() {
        String reviewText = "";
        String questionString = "";
        for (int i = 0; i < test.getNumberOfQuestions(); i++) {
            questionString = (i + 1) + ". " + test.getQuestion(i).getQuest();
            if (test.getQuestion(i).getUserAnswer() != -1) {
                questionString
                        = questionString
                        + "\n\nYou answered: "
                        + test.getQuestion(i).getChoices().get(test.getQuestion(i).getUserAnswer())
                        + "\n";
                if (test.getQuestion(i).getAnswer() == test.getQuestion(i).getUserAnswer()) {
                    questionString = questionString + "Correct: ";
                } else {
                    questionString
                            = questionString
                            + "Incorrect. The answer is "
                            + test.getQuestion(i).getChoices().get(test.getQuestion(i).getAnswer())
                            + ".\n";
                }
                questionString
                        = questionString + test.getQuestion(i).getHints().get(test.getQuestion(i).getAnswer());
            } else {
                questionString = questionString + "\n\nYou did not answer this question.";
            }
            reviewText = reviewText + questionString + "\n\n";
        }
        return reviewText;
    }

    /**
     * Starts a new test and displays the test screen
     * @param event indicates that a button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    public void startNewTest(ActionEvent event) throws IOException {
        Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
        Scene startPageScene = new Scene(startPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(startPageScene);
        window.setTitle("Start Test");
        window.show();
    }

    /**
     * Shows the log in screen
     * @param event indicates that a button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    public void showLogInPage(ActionEvent event) throws IOException {
        Parent logInParent = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.setTitle("Log In");
        window.show();
    }

    /**
     * Shows the user account screen
     * @param event indicates that a button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    public void showUserAccountPage(ActionEvent event) throws IOException {
        Parent testPageParent = FXMLLoader.load(getClass().getResource("FXMLUserAccountPage.fxml"));
        Scene userAccountScene = new Scene(testPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(userAccountScene);
        window.setTitle("User Account");
        window.show();
    }
    
}