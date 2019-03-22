/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/** @author jthre_000 */
public class FXMLDocumentController implements Initializable {

  @FXML private Label lbQuestionText, lbChoiceA, lbChoiceB, lbChoiceC, lbChoiceD, lbQuestionNum;
  @FXML private Button btOptionA, btOptionB, btOptionC, btOptionD;
  @FXML private Button btPause, btPrevious, btNext, btFinishTest;
  private int questionNumber = 0; // track which question user is currently on
  private Test test;
  @FXML private Label lbTimer;
  private Timeline timeline;
  private int seconds;

  @FXML
  public void handleAnswerChoice(ActionEvent event) {
    Button target = (Button) event.getTarget();

    lbChoiceA.setTextFill(Color.BLACK);
    lbChoiceB.setTextFill(Color.BLACK);
    lbChoiceC.setTextFill(Color.BLACK);
    lbChoiceD.setTextFill(Color.BLACK);

    if (target == btOptionA) {
      test.getQuestion(questionNumber).setUserAnswer(0);
      lbChoiceA.setTextFill(Color.ORANGE);
    } else if (target == btOptionB) {
      test.getQuestion(questionNumber).setUserAnswer(1);
      lbChoiceB.setTextFill(Color.ORANGE);
    } else if (target == btOptionC) {
      test.getQuestion(questionNumber).setUserAnswer(2);
      lbChoiceC.setTextFill(Color.ORANGE);
    } else if (target == btOptionD) {
      test.getQuestion(questionNumber).setUserAnswer(3);
      lbChoiceD.setTextFill(Color.ORANGE);
    }
  }

  @FXML
  public void handleNext(ActionEvent event) {
    if (questionNumber < test.getNumberOfQuestions() - 1) questionNumber++;

    lbChoiceA.setTextFill(Color.BLACK);
    lbChoiceB.setTextFill(Color.BLACK);
    lbChoiceC.setTextFill(Color.BLACK);
    lbChoiceD.setTextFill(Color.BLACK);
    switch (test.getQuestion(questionNumber).getUserAnswer()) {
      case (0):
        lbChoiceA.setTextFill(Color.ORANGE);
        break;
      case (1):
        lbChoiceB.setTextFill(Color.ORANGE);
        break;
      case (2):
        lbChoiceC.setTextFill(Color.ORANGE);
        break;
      case (3):
        lbChoiceD.setTextFill(Color.ORANGE);
    }

    getInfoToShow();
  }

  @FXML
  public void handlePrevious(ActionEvent event) {

    if (questionNumber > 0) questionNumber--;

    lbChoiceA.setTextFill(Color.BLACK);
    lbChoiceB.setTextFill(Color.BLACK);
    lbChoiceC.setTextFill(Color.BLACK);
    lbChoiceD.setTextFill(Color.BLACK);
    switch (test.getQuestion(questionNumber).getUserAnswer()) {
      case (0):
        lbChoiceA.setTextFill(Color.ORANGE);
        break;
      case (1):
        lbChoiceB.setTextFill(Color.ORANGE);
        break;
      case (2):
        lbChoiceC.setTextFill(Color.ORANGE);
        break;
      case (3):
        lbChoiceD.setTextFill(Color.ORANGE);
    }

    getInfoToShow();
  }

  @FXML
  public void handleFinishTest(ActionEvent event) throws IOException {
    test.calculateScore();
    System.out.println("Your score: " + test.getScore());
    Project.setTest(test);
    Parent endPageParent = FXMLLoader.load(getClass().getResource("FXMLEndPage.fxml"));
    Scene endPageScene = new Scene(endPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(endPageScene);
    window.show();
  }

  @FXML
  public void handlePause(ActionEvent event) {
    lbQuestionText.setText("");
    lbChoiceA.setText("");
    lbChoiceB.setText("");
    lbChoiceC.setText("");
    lbChoiceD.setText("");
    timeline.pause();

    Alert pause = new Alert(AlertType.NONE, "Test paused", ButtonType.CLOSE);
    Optional<ButtonType> result = pause.showAndWait();
    if (result.get() == ButtonType.CLOSE) {
      lbQuestionText.setText(test.getQuestion(questionNumber).getQuest());
      lbChoiceA.setText((String) test.getQuestion(questionNumber).getChoices().get(0));
      lbChoiceB.setText((String) test.getQuestion(questionNumber).getChoices().get(1));
      lbChoiceC.setText((String) test.getQuestion(questionNumber).getChoices().get(2));
      lbChoiceD.setText((String) test.getQuestion(questionNumber).getChoices().get(3));
      timeline.play();
    }
  }

  public void displayTime() {
    timeline = new Timeline();
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline
        .getKeyFrames()
        .add(
            new KeyFrame(
                Duration.seconds(1),
                event -> {
                  seconds--;
                  lbTimer.setText(String.format("%d:%02d", seconds / 60, seconds % 60));
                  if (seconds <= 0) {
                    timeline.stop();
                  }
                }));
    lbTimer.setText(String.format("%d:%02d", seconds / 60, seconds % 60));
    timeline.play();
  }

  public void getInfoToShow() {
    lbQuestionText.setText(test.getQuestion(questionNumber).getQuest());
    lbChoiceA.setText((String) test.getQuestion(questionNumber).getChoices().get(0));
    lbChoiceB.setText((String) test.getQuestion(questionNumber).getChoices().get(1));
    lbChoiceC.setText((String) test.getQuestion(questionNumber).getChoices().get(2));
    lbChoiceD.setText((String) test.getQuestion(questionNumber).getChoices().get(3));
    lbQuestionNum.setText("Question " + (questionNumber + 1) + "/" + test.getNumberOfQuestions());
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {

    test = new Test(Project.getNumOfQuestions());
    seconds = test.getNumberOfQuestions() * 60;
    getInfoToShow();
    displayTime();
  }
}
