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

  public int getScore() {
    return score;
  }

  public int getScorePercent() {
      return 100 * score / numberOfQuestions;
  }
  
  public int getNumberOfQuestions() {
    return numberOfQuestions;
  }

  public void setNumberOfQuestions(int questions) {
    numberOfQuestions = questions;
  }

  public Question getQuestion(int questIndex) {
    return questionList.get(questIndex);
  }

  public void randomizeQuestionOrder() {
    Collections.shuffle(questionList);
  }

  
  public void calculateScore() {
    this.score = 0;
    for (Question question : questionList) {
      if (question.isCorrect()) {
        this.score++;
      }
    }
  }

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
}
