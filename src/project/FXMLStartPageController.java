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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lizhayes-golding
 */
public class FXMLStartPageController implements Initializable {

  @FXML private ChoiceBox cbNumQuestions, cbTestType;
  @FXML private Button btStart, btAccount;
  @FXML private Label lbNumQuestions, lbTestType;

  @FXML
  public void handleStartButton(ActionEvent event) throws IOException {
    Project.setNumOfQuestions((int) cbNumQuestions.getValue());
    Parent testPageParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    Scene testPageScene = new Scene(testPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(testPageScene);
    window.show();
  }
  
  public void handleAccountButton(ActionEvent event) throws IOException{
    Parent testPageParent = FXMLLoader.load(getClass().getResource("FXMLUserAccountPage.fxml"));
    Scene testPageScene = new Scene(testPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(testPageScene);
    window.show();  
  }

  /** Initializes the controller class. */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cbNumQuestions.getItems().addAll(10, 20, 30, 40, 50);
    cbNumQuestions.setValue(10);
    cbTestType.getItems().addAll("Recorded", "Practice");
    cbTestType.setValue("Recorded");
  }
}
