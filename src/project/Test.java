package project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import jsonparser.JSONQuestionObject;
import jsonparser.JSONReader;

public class Test {

  private ArrayList<Question> questionList = new ArrayList<>();
  private ArrayList<Question> questionListTemp = new ArrayList<>();
  private int score;
  private int numberOfQuestions;
  private Timer testTimer;
  File file = new File("Questions.json");
  private static String testType = "recorded";

  Test() {
    numberOfQuestions = 50;
    score = 0;
    this.initializequestionList(file);
  }

  Test(int numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
    score = 0;
    this.initializequestionList(file);
  }

  /**
   * Returns score
   * @return the score of this test
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets score
   * @param questionsCorrect number of questions correct
   * @param numberOfQuestions total number of questions
   */
  public int getScorePercent() {
      return 100 * score / numberOfQuestions;
  }
  
  /**
   * Returns numberOfQuestions
   * @return the number of questions
   */
  public int getNumberOfQuestions() {
    return numberOfQuestions;
  }

  /**
   * Sets numberOfQuestions
   * @param questions the number of questions
   */
  public void setNumberOfQuestions(int questions) {
    numberOfQuestions = questions;
  }

  /**
   * Returns the question in the passed index
   * @param questIndex the index of the question requested
   * @return the question requested
   */
  public Question getQuestion(int questIndex) {
    return questionList.get(questIndex);
  }

  /**
   * Randomizes the question order
   */
  public void randomizeQuestionOrder() {
    Collections.shuffle(questionList);
  }
  
  /**
   * Calculates the score based on how many questions the user 
   * answered correctly
   */
  public void calculateScore() {
    this.score = 0;
    for (Question question : questionList) {
      if (question.isCorrect()) {
        this.score++;
      }
    }
  }

  /**
   * Creates an array of questions from a JSON file containing 
   * question information
   * @param file0 the JSON file that contains question information
   */
  public void initializequestionList(File file0) {
    try {
      JSONReader jsonReader = new JSONReader();
      ArrayList<JSONQuestionObject> jsonquestionList = new ArrayList();
      jsonquestionList = jsonReader.readJSONFile();
      String quest;
      ArrayList<String> choices;
      Integer answer;
      ArrayList<String> hints;

      for (int i = 0; i < 73; i++) {
        quest = jsonquestionList.get(i).getQuestionText();
        choices = (ArrayList<String>) jsonquestionList.get(i).getPossibleAnswers();
        answer = jsonquestionList.get(i).getCorrectAnswerIdx();
        hints = (ArrayList<String>) jsonquestionList.get(i).getExplanations();
        Question question = new Question(quest, choices, answer, hints);
        questionListTemp.add(question);
      }
      Collections.shuffle(questionListTemp);
      for (int i = 0; i < numberOfQuestions; i++) {
        questionList.add(questionListTemp.get(i));
      }

    } catch (Exception e) {
      System.out.println("error loading from file");
    }
  }
  
  public static void setTestType(String type){
      testType = type;
  }
  public static String getTestType(){
    return testType;
}
  public static void resetTestType(){
          testType = null;
}
}
