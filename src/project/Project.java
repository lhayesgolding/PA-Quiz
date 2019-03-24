/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** @author jthre_000 */
public class Project extends Application {
    
    private static int numOfQuestions = 10;
    private static Test test = new Test();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       launch(args);
    }
    public static int getNumOfQuestions(){
        return numOfQuestions;
    }
    
    public static void setNumOfQuestions(int number){
        numOfQuestions = number;
    
    }
    
    public static Test getTest() {
        return test;
    }
    
    public static void setTest(Test test0) {
        test = test0;
    }
    
}
