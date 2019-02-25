/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jsonparser.JSONQuestionObject;
import jsonparser.JSONReader;

/**
 *
 * @author jthre_000
 */
public class Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       launch(args);
        
       /*
      //  Sample initialization of the JSON Reader        
        JSONReader jsonReader = new JSONReader();
        ArrayList<JSONQuestionObject> questionList = new ArrayList();        
        questionList = jsonReader.readJSONFile();

       // Example of extracting the parsed data from the ArrayList
        System.out.println(questionList.get(0).getQuestionText());
        for(int i = 0; i < 4; i++){
            System.out.println("- " + questionList.get(0).getPossibleAnswers().get(i));
        }
        System.out.println(questionList.get(0).getCategories());
*/
    }
    
}
