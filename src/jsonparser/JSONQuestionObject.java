package jsonparser;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONQuestionObject {

@SerializedName("categories")
@Expose
private Object categories;
@SerializedName("questionText")
@Expose
private String questionText;
@SerializedName("possibleAnswers")
@Expose
private List<String> possibleAnswers = null;
@SerializedName("explanations")
@Expose
private List<String> explanations = null;
@SerializedName("correctAnswerIdx")
@Expose
private Integer correctAnswerIdx;

public Object getCategories() {
return categories;
}

public void setCategories(Object categories) {
this.categories = categories;
}

public String getQuestionText() {
return questionText;
}

public void setQuestionText(String questionText) {
this.questionText = questionText;
}

public List<String> getPossibleAnswers() {
return possibleAnswers;
}

public void setPossibleAnswers(List<String> possibleAnswers) {
this.possibleAnswers = possibleAnswers;
}

public List<String> getExplanations() {
return explanations;
}

public void setExplanations(List<String> explanations) {
this.explanations = explanations;
}

public Integer getCorrectAnswerIdx() {
return correctAnswerIdx;
}

public void setCorrectAnswerIdx(Integer correctAnswerIdx) {
this.correctAnswerIdx = correctAnswerIdx;
}

}