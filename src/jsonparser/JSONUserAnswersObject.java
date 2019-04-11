/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONUserAnswersObject {

@SerializedName("userAnswer")
@Expose
private Integer userAnswer;
@SerializedName("questionID")
@Expose
private Integer questionID;

public Integer getUserAnswer() {
return userAnswer;
}

public void setUserAnswer(Integer userAnswer) {
this.userAnswer = userAnswer;
}

public Integer getQuestionID() {
return questionID;
}

public void setQuestionID(Integer questionID) {
this.questionID = questionID;
}

}
