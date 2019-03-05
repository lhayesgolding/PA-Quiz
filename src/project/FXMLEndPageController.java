/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lizri
 */
public class FXMLEndPageController implements Initializable {

    @FXML private Button btReviewTest, btReviewPrevious, btNewTest, btLogOut;
    @FXML private Label lbNumQuestions, lbNumQuestionsCorrect, lbPercentCorrect;
    Test test = new Test();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        test = Project.getTest();
        lbNumQuestions.setText(String.valueOf(test.getNumberOfQuestions()));
        lbNumQuestionsCorrect.setText(String.valueOf(test.getScore()));
        int percent = 100 * test.getScore() / test.getNumberOfQuestions();
        lbPercentCorrect.setText(String.valueOf(percent) + "%");
    }    
    
}
