package project;

import java.util.ArrayList;

public class Question {

  private String quest;
  private ArrayList<String> choices = new ArrayList<>(); // made this an ArrayList for convenience
  private Integer answer;
  private Integer userAnswer = -1;
  private ArrayList<String> hints = new ArrayList<>(); // same with this one

  Question(String quest0, ArrayList<String> choices0, Integer answer0, ArrayList<String> hints0) {
    quest = quest0;
    choices = choices0;
    answer = answer0;
    hints = hints0;
  }

  /**
   * Returns quest
   * @return the question in the form of a String
   */
  public String getQuest() {
    return quest;
  }

  /**
   * Returns choices
   * @return an ArrayList of answer choices
   */
  public ArrayList getChoices() {
    return choices;
  }

  /**
   * Returns answer
   * @return an answer in the form of an Integer
   */
  public Integer getAnswer() {
    return answer;
  }

  /**
   * Returns hints
   * @return an ArrayList of hints for each question
   */
  public ArrayList getHints() {
    return hints;
  }

  /**
   * Returns userAnswer
   * @return the Integer of the answer the user selected
   */
  public Integer getUserAnswer() {
    return userAnswer;
  }

  /**
   * Sets userAnswer
   * @param selection the answer the user provided
   */
  public void setUserAnswer(int selection) {
    userAnswer = selection;
  }

  /**
   * Returns true if the user's answer is correct, or false otherwise
   * @return true if answer is equal to userAnswer, false otherwise
   */
  public boolean isCorrect() {
    return answer.equals(userAnswer);
  }
}
