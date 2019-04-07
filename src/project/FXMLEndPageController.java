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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lizri
 */
public class FXMLEndPageController implements Initializable {

    @FXML
    private Button btReviewTest, btReviewPrevious, btNewTest, btLogOut;
    @FXML
    private Label lbNumQuestions, lbNumQuestionsCorrect, lbPercentCorrect;
    @FXML
    private TextArea reviewTextArea;

    Test test = new Test();
    @FXML
    private Label lbTestResults;

    @FXML
    public void handleReviewTest(ActionEvent event) {
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
        reviewTextArea.setText(reviewText);
        reviewTextArea.setVisible(true);
    }
    
    @FXML
    public void handleReviewPrevious(ActionEvent event) {
        reviewTextArea.setText("Information about previous tests will show up here.");
        reviewTextArea.setVisible(true);
    }
    
    
    
    @FXML
    public void handleNewTest(ActionEvent event) throws IOException {
        Parent startPageParent = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
        Scene startPageScene = new Scene(startPageParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(startPageScene);
        window.show();
    }
    
    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        Parent logInParent = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbTestResults.setText(Project.getUsersName() + "'s Test Results");
        test = Project.getTest();
        lbNumQuestions.setText(String.valueOf(test.getNumberOfQuestions()));
        lbNumQuestionsCorrect.setText(String.valueOf(test.getScore()));
        int percent = 100 * test.getScore() / test.getNumberOfQuestions();
        lbPercentCorrect.setText(String.valueOf(percent) + "%");
    }
}
