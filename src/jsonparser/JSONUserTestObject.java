/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author ConorLaptop
 */
public class JSONUserTestObject {
  
@SerializedName("questions")
@Expose
private List<JSONUserQuestionObject> questions = null;

public List<JSONUserQuestionObject> getQuestions() {
return questions;
}

public void setQuestions(List<JSONUserQuestionObject> questions) {
this.questions = questions;
}  
}
