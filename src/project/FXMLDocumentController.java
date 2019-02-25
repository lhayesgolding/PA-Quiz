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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import jsonparser.JSONQuestionObject;
import jsonparser.JSONReader;

/**
 *
 * @author jthre_000
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label lbQuestionText, lbChoiceA, lbChoiceB, lbChoiceC, lbChoiceD;
    @FXML private Button btOptionA, btOptionB, btOptionC, btOptionD;
    @FXML private Button btPause, btPrevious, btNext, btFinishTest;
    private int questionNumber = 0; // track which question user is currently on
    private Test test = new Test();
    
    // won't need this - will get the questions from the Test class instead (need to add  individual question getter in test class)
    private JSONReader jsonReader = new JSONReader();
        ArrayList<JSONQuestionObject> questionList = new ArrayList();
    
    
    @FXML
    public void handleA(ActionEvent event) {
        // to do: record whether question was correct, 
        // put checkmark graphic next to chosen answer or something else to show it 
        //         -> needs to be part of the question object to stay there if user leaves question and comes back
    } 
    
    @FXML
    public void handleB(ActionEvent event) {
        
    }
    
     @FXML
    public void handleC(ActionEvent event) {
        
    }
    
     @FXML
    public void handleD(ActionEvent event) {
        
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
    
    public void getInfoToShow() {
        lbQuestionText.setText(questionList.get(questionNumber).getQuestionText());
        lbChoiceA.setText(questionList.get(questionNumber).getPossibleAnswers().get(0));
        lbChoiceB.setText(questionList.get(questionNumber).getPossibleAnswers().get(1));
        lbChoiceC.setText(questionList.get(questionNumber).getPossibleAnswers().get(2));
        lbChoiceD.setText(questionList.get(questionNumber).getPossibleAnswers().get(3));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // won't need this - get questions from Test class instead
        try {
            questionList = jsonReader.readJSONFile();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getInfoToShow();
  
    }    
    
}
