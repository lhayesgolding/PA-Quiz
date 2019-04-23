package model;

import model.Question;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import jsonparser.JSONQuestionObject;
import jsonparser.JSONReader;
import jsonparser.JSONUserQuestionObject;
import jsonparser.JSONUserTestObject;

/**
 *
 * @author jthre_000
 */
public class Test {

  private ArrayList<Question> questionList = new ArrayList<>();
  private ArrayList<Question> questionListTemp = new ArrayList<>();
  private ArrayList<Question> questionListUser = new ArrayList<>();
  private int score;
  private int numberOfQuestions;
  File file = new File("Questions.json");
  private static String testType = "recorded";

  public Test() {
    numberOfQuestions = 50;
    score = 0;
    this.initializeQuestionList(file);
    questionList = questionListUser;
    numberOfQuestions = questionListUser.size();
  }

  public Test(int numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
    score = 0;
    this.initializeQuestionList(file);
  }
  

  /**
   * Returns score
   * @return the score of this test
   */
  public int getScore() {
    return score;
  }

  /**
   * Gets the score percentage based on number of correct answers
   * @return the score percentage
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
   * Returns the question in the passed index (past test)
   * @param questIndex the index of the question requested
   * @return the question requested
   */  
  public Question getPastQuestion(int questIndex){
    return questionListUser.get(questIndex);
  }
  
  /**
   * Adds a question to the array
   * @param q the question to be added
   */  
  public void addQuestion(Question q){
    questionListUser.add(q);
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
  public void initializeQuestionList(File file0) {
    try {
      JSONReader jsonReader = new JSONReader();
      ArrayList<JSONQuestionObject> jsonquestionList = new ArrayList();
      jsonquestionList = jsonReader.readJSONQuestionsFile();
      String quest;
      ArrayList<String> choices;
      Integer answer;
      Integer questionID;
      ArrayList<String> hints;

      for (int i = 0; i < 87; i++) {
        quest = jsonquestionList.get(i).getQuestionText();
        choices = (ArrayList<String>) jsonquestionList.get(i).getPossibleAnswers();
        answer = jsonquestionList.get(i).getCorrectAnswerIdx();
        hints = (ArrayList<String>) jsonquestionList.get(i).getExplanations();
        questionID = jsonquestionList.get(i).getQuestionID();
        
        Question question = new Question(quest, choices, answer, hints, questionID);
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
  
    /**
     * Gets a past test 
     * @param test the test being retrieved 
     * @return the new test
     * @throws Exception thrown if there is an error during runtime
     */
    public Test retrievePastTest(JSONUserTestObject test) throws Exception{
    ArrayList<JSONUserQuestionObject> userQuestions = (ArrayList<JSONUserQuestionObject>) test.getQuestions();
    Test newTest = new Test();
    
    for(int i = 0; i < userQuestions.size(); i++){
      int questionID = userQuestions.get(i).getQuestionID();
      int userAnswer = userQuestions.get(i).getUserAnswer();
      for(int j = 0; j < questionListTemp.size(); j++){
        if(questionID == questionListTemp.get(j).getQuestionID()){
          newTest.addQuestion(questionListTemp.get(j));
          newTest.getPastQuestion(i).setUserAnswer(userAnswer);
          int numQuestions = newTest.getNumberOfQuestions();
          numQuestions++;
          newTest.setNumberOfQuestions(numQuestions);
          break;
        }
      }
    }
    return newTest;
  }

    /**
     * Sets the type of test to either practice or recorded
     * @param type the type of test
     */
    public static void setTestType(String type){
      testType = type;
  }

    /**
     * Gets the type of test
     * @return the test type
     */
    public static String getTestType(){
    return testType;
}

    /**
     * Sets the test type to null
     */
    public static void resetTestType(){
          testType = null;
}
}
