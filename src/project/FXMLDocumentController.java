/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private int questionNumber = 0; // track which question user is currently on
    private Test test;
    
    

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
    public void handleFinishTest(ActionEvent event) throws IOException {
        test.calculateScore();
        System.out.println("Your score: " + test.getScore());
        Project.setTest(test);
        Parent endPageParent = FXMLLoader.load(getClass().getResource("FXMLEndPage.fxml"));
        Scene endPageScene = new Scene(endPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        
        Alert pause = new Alert(AlertType.NONE, "Test paused", ButtonType.CLOSE);
        Optional<ButtonType> result = pause.showAndWait();
        if(result.get() == ButtonType.CLOSE) {
            lbQuestionText.setText(test.getQuestion(questionNumber).getQuest());
            lbChoiceA.setText((String)test.getQuestion(questionNumber).getChoices().get(0));
            lbChoiceB.setText((String)test.getQuestion(questionNumber).getChoices().get(1));
            lbChoiceC.setText((String)test.getQuestion(questionNumber).getChoices().get(2));
            lbChoiceD.setText((String)test.getQuestion(questionNumber).getChoices().get(3));
        }
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
        
        test = new Test(Project.getNumOfQuestions());
        getInfoToShow();
  
    }    
   
}
