/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;
import jsonparser.JSONQuestionObject;
import jsonparser.JSONReader;

/**
 *
 * @author jthre_000
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label lbQuestionText, lbChoiceA, lbChoiceB, lbChoiceC, lbChoiceD, lbQuestionNum;
    @FXML private Button btOptionA, btOptionB, btOptionC, btOptionD;
    @FXML private Button btPause, btPrevious, btNext, btFinishTest;
    @FXML private Label lbTimer;
    private int questionNumber = 0; // track which question user is currently on
    private Test test = new Test(15);
    private Timeline timeline;
    private int numberOfQuestions = test.getNumberOfQuestions();
    private IntegerProperty timeMinutes = new SimpleIntegerProperty(numberOfQuestions);

    
    

    @FXML
    public void handleA(ActionEvent event) {
        // to do: record whether question was correct, 
        // put checkmark graphic next to chosen answer or something else to show it 
        //         -> needs to be part of the question object to stay there if user leaves question and comes back
        System.out.println("You clicked choice A");
        test.getQuestion(questionNumber).setUserAnswer(0);
        
        if (test.getQuestion(questionNumber).isCorrect()) {
            System.out.println("The answer selected is correct!");
        }
        else {
            System.out.println("The answer selected is incorrect.");
        }
    } 
    
    @FXML
    public void handleB(ActionEvent event) {
        System.out.println("You clicked choice B");
        test.getQuestion(questionNumber).setUserAnswer(1);
        
        if (test.getQuestion(questionNumber).isCorrect()) {
            System.out.println("The answer selected is correct!");
        }
        else {
            System.out.println("The answer selected is incorrect.");
        }
    }
    
     @FXML
    public void handleC(ActionEvent event) {
        System.out.println("You clicked choice C");
        test.getQuestion(questionNumber).setUserAnswer(2);
        
        if (test.getQuestion(questionNumber).isCorrect()) {
            System.out.println("The answer selected is correct!");
        }
        else {
            System.out.println("The answer selected is incorrect.");
        }
    }
    
     @FXML
    public void handleD(ActionEvent event) {
        System.out.println("You clicked choice D");
        test.getQuestion(questionNumber).setUserAnswer(3);
        
        if (test.getQuestion(questionNumber).isCorrect()) {
            System.out.println("The answer selected is correct!");
        }
        else {
            System.out.println("The answer selected is incorrect.");
        }
    }
    
    @FXML
    public void handleNext(ActionEvent event) {
        if (questionNumber < test.getNumberOfQuestions() - 1) 
            questionNumber ++;
        getInfoToShow();
    }
    
    @FXML
    public void handlePrevious(ActionEvent event) {
        if (questionNumber > 0) 
            questionNumber --;
        getInfoToShow();
    }
    
    @FXML
    public void handleFinishTest(ActionEvent event) {
        test.calculateScore();
        System.out.println("Your score: " + test.getScore());
    }
    
    @FXML
    public void handlePause(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Click OK to resume test", ButtonType.OK);
            alert.setHeaderText("Test paused");
            alert.show();
//            timeline.pause();
    }
    
    @FXML
    public void displayTime(ActionEvent event){    
        timeMinutes.set(numberOfQuestions);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.minutes(numberOfQuestions), 
            new KeyValue(timeMinutes, 0)));
        timeline.playFromStart();
        
    }
    
    public void getInfoToShow() {
        lbQuestionText.setText(test.getQuestion(questionNumber).getQuest());
        lbChoiceA.setText((String)test.getQuestion(questionNumber).getChoices().get(0));
        lbChoiceB.setText((String)test.getQuestion(questionNumber).getChoices().get(1));
        lbChoiceC.setText((String)test.getQuestion(questionNumber).getChoices().get(2));
        lbChoiceD.setText((String)test.getQuestion(questionNumber).getChoices().get(3));
        lbQuestionNum.setText("Question " + (questionNumber + 1) + "/" + test.getNumberOfQuestions());
  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbTimer.textProperty().bind(timeMinutes.asString());
        getInfoToShow();  
    }    
   
}
