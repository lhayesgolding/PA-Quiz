/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author lizri
 */
public class FXMLEndPageController implements Initializable {

  @FXML private Button btReviewTest, btReviewPrevious, btNewTest, btLogOut;
  @FXML private Label lbNumQuestions, lbNumQuestionsCorrect, lbPercentCorrect;
  @FXML private ScrollPane scrollPane;
  @FXML private TextArea reviewTextArea;

  Test test = new Test();

  @FXML
  public void handleReviewTest(ActionEvent event) {
    String reviewText = "";
    String questionString = "";
    for (int i = 0; i < test.getNumberOfQuestions(); i++) {
      questionString = (i + 1) + ". " + test.getQuestion(i).getQuest();
      if (test.getQuestion(i).getUserAnswer() != -1) {
        questionString =
            questionString
                + "\n\nYou answered: "
                + test.getQuestion(i).getChoices().get(test.getQuestion(i).getUserAnswer())
                + "\n";
        if (test.getQuestion(i).getAnswer() == test.getQuestion(i).getUserAnswer())
          questionString = questionString + "Correct: ";
        else {
          questionString =
              questionString
                  + "Incorrect. The answer is "
                  + test.getQuestion(i).getChoices().get(test.getQuestion(i).getAnswer())
                  + ".\n";
        }
        questionString =
            questionString + test.getQuestion(i).getHints().get(test.getQuestion(i).getAnswer());
      } else questionString = questionString + "\n\nYou did not answer this question.";
      reviewText = reviewText + questionString + "\n\n";
    }
    reviewTextArea.setText(reviewText);
    scrollPane.setVisible(true);
  }

  /** Initializes the controller class. */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    test = Project.getTest();
    lbNumQuestions.setText(String.valueOf(test.getNumberOfQuestions()));
    lbNumQuestionsCorrect.setText(String.valueOf(test.getScore()));
    int percent = 100 * test.getScore() / test.getNumberOfQuestions();
    lbPercentCorrect.setText(String.valueOf(percent) + "%");
  }
}
