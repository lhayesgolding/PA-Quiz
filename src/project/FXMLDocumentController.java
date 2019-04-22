/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import jsonparser.JSONReader;
import jsonparser.JSONUserQuestionObject;
import jsonparser.JSONUserTestObject;

/**
 * @author jthre_000
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lbQuestionText, lbChoiceA, lbChoiceB, lbChoiceC, lbChoiceD, lbQuestionNum;
    @FXML
    private Button btOptionA, btOptionB, btOptionC, btOptionD;
    @FXML
    private Button btPause, btPrevious, btNext, btFinishTest;
    private Integer questionNumber = 0; // track which question user is currently on
    private Test test;
    @FXML
    private Label lbTimer;
    private Timeline timeline;
    private int seconds;

    /**
     * Sets user answer to the answer that is selected
     * @param event indicates that the user has pressed a button
     * for an answer
     */
    @FXML
    public void handleAnswerChoice(ActionEvent event) {
        Button target = (Button) event.getTarget();
        recordAnswer(target);
    }

    /**
     * Switches to next question, and changes color of the answer
     * the user selects
     * @param event indicates that the Next button has been pressed
     */
    @FXML
    public void handleNext(ActionEvent event) {
        goToQuestion("next");
        getInfoToShow();
    }

    /**
     * Switches to previous question, and changes color of the answer
     * the user selects
     * @param event indicates that the Previous button has been pressed
     */
    @FXML
    public void handlePrevious(ActionEvent event) {
        goToQuestion("previous");
        getInfoToShow();
    }

    /**
     * Displays user score and end screen
     * @param event indicates that the Finish button has been pressed
     * @throws IOException thrown when I/O error occurs
     */
    @FXML
    public void handleFinishTest(ActionEvent event) throws IOException, Exception {
        test.calculateScore();
        if (Test.getTestType().equals("Recorded")) {
            saveScore(Project.getCurrentUser());
            saveScore("all");
            saveTest();
        }
        showEndScreen(event);
        Test.resetTestType();
    }

    /**
     * Pauses the timer and hides testQuestionsArray until the user resumes
     * @param event indicates that the Pause button has been pressed
     */
    @FXML
    public void handlePause(ActionEvent event) {
        pauseTest();
    }

    /**
     * Shows the timer for the test, and ends the test if time reaches 0
     */
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
                                        lbQuestionText.setText("You have reached the end of the allocated time. "
                                                + "Click the Finish Test button to see your score");
                                        lbChoiceA.setText("");
                                        lbChoiceB.setText("");
                                        lbChoiceC.setText("");
                                        lbChoiceD.setText("");
                                        btOptionA.setDisable(true);
                                        btOptionB.setDisable(true);
                                        btOptionC.setDisable(true);
                                        btOptionD.setDisable(true);
                                        btNext.setDisable(true);
                                        btPrevious.setDisable(true);
                                        btPause.setDisable(true);
                                    }
                                }));
        lbTimer.setText(String.format("%d:%02d", seconds / 60, seconds % 60));
        timeline.play();
    }

    /**
     * Gets information for the question and answers
     */
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
    
    /**
     * Saves the answer that the user entered 
     * @param target the button that the user selected
     */
    public void recordAnswer(Button target) {
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
    
    /**
     * Switches to a new question, either the next or the previous
     * @param direction determines if the program should go to the next
     * or previous question
     */
    public void goToQuestion(String direction) {
        if (direction == "next") {
            if (questionNumber < test.getNumberOfQuestions() - 1) {
                questionNumber++;
            }
        }
        else if (direction == "previous") {
            if (questionNumber > 0) {
                questionNumber--;
            }
        }

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
    }

    /**
     * Saves the score that the user received for this test
     * @param user the username of the user
     */
    public void saveScore(String user) {
        String scoreFileString;
        if (user.equals("all"))
            scoreFileString = "src/datafiles/AllScores.txt";
        else
            scoreFileString = "src/datafiles/" + user + ".txt";
        File scoreFile = new File(scoreFileString);
        ArrayList<String> scores = new ArrayList<String>();
        String currentScore;
        try {
            FileWriter writer = new FileWriter(scoreFile, true);
            writer.append(String.valueOf(test.getScorePercent()));
            writer.append("\n");
            // if list of scores > 50, remove first score
            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            while ((currentScore = br.readLine()) != null) {
                scores.add(currentScore);
            }
            scores.add(String.valueOf(test.getScorePercent())); //having to add this separetely because it's not getting added in the loop.
            br.close();
            System.out.println("size: " + scores.size());
            writer.close();
            
            if (scores.size() > 50) {
                // new FileWriter object - not appendable to clear out AllScores file
                FileWriter writer2 = new FileWriter(scoreFile, false);
                writer2.write("");
                writer2.close();
                scores.remove(0);
                // another new FileWrite object to make appendable again
                FileWriter writer3 = new FileWriter(scoreFile, true);
                for (int i = 0; i < 50; i++) {
                    writer3.append(scores.get(i));
                    writer3.append("\n");
                }
                writer3.close();
            }                
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Writes the user's test to a file in json format
     * Saves the following fields: userAnswer, questionID
     * @throws java.lang.Exception
     */    
    public void saveTest() throws Exception{
      File testsFile = new File("src/datafiles/"+Project.getUserID()+"Tests.json");
      ArrayList<JSONUserTestObject> testsArray = new ArrayList();
      
      if(testsFile.exists()){
        JSONReader jReader = new JSONReader();
        testsArray = jReader.readJSONUserTestFile(testsFile);
      }
      
      GsonBuilder gson = new GsonBuilder();
      int questionID = 0, userAnswer = 0;
      JSONUserQuestionObject question = new JSONUserQuestionObject();
      JSONUserTestObject newTest = new JSONUserTestObject();
      ArrayList<JSONUserQuestionObject> testQuestionsArray = new ArrayList();

      for(int i = 0; i < test.getNumberOfQuestions(); i++){
        questionID = test.getQuestion(i).getQuestionID();
        userAnswer = test.getQuestion(i).getUserAnswer();
        question = new JSONUserQuestionObject();
        question.setQuestionID(questionID);
        question.setUserAnswer(userAnswer);
        testQuestionsArray.add(question);
      }
      
      newTest.setQuestions(testQuestionsArray);
      testsArray.add(newTest);
      
      FileWriter writer = new FileWriter(testsFile, false);
      writer.write(gson.setPrettyPrinting().create().toJson(testsArray));
      writer.append("\n");
      writer.close();
    }
    
    /**
     * Pauses the timer and hides the question and answers
     */
    public void pauseTest() {
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
    
    /**
     * Goes to the end screen after the test is completed
     * @param event indicates that the user has pressed a button
     * @throws IOException thrown when I/O error occurs
     */
    public void showEndScreen(ActionEvent event) throws IOException {
        Project.setTest(test);
        Parent endPageParent = FXMLLoader.load(getClass().getResource("FXMLEndPage.fxml"));
        Scene endPageScene = new Scene(endPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(endPageScene);
        window.setTitle("Test Results");
        window.show();
    }
}
