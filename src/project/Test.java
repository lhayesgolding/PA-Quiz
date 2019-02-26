package project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import jsonparser.JSONQuestionObject;
import jsonparser.JSONReader;


public class Test {
    

    
    private ArrayList<Question> questionList = new ArrayList<>();
    private int score;
    private int numberOfQuestions;
    private Timer testTimer;
    File file = new File("Questions.json");
    
    Test(){
        numberOfQuestions = 50;
        score = 0;
//        testTimer.schedule(task, 0, 75 * 60 * 1000);
        this.initializequestionList(file);
    }
    Test(int numberOfQuestions){
        this.numberOfQuestions = numberOfQuestions;
        score = 0;
//        testTimer.schedule(task, 0, numberOfQuestions * 60 * 1000);
    }
    public int getScore() {
        return score;
    }
    public void setScore(int questionsCorrect, int numberOfQuestions) {
        score = questionsCorrect/numberOfQuestions * 100;
    }
    public int getNumberOfQuestions(){
        return numberOfQuestions;
    }
    
    public void setNumberOfQuestions(int questions){
        numberOfQuestions = questions;
    }
    
    public Question getQuestion(int questIndex) {
        return questionList.get(questIndex);
    }
    
    public void randomizeQuestionOrder(ArrayList questions){
        Collections.shuffle(questions);
    }

    public void initializequestionList(File file0){
        try{
              JSONReader jsonReader = new JSONReader();
              ArrayList<JSONQuestionObject> jsonquestionList = new ArrayList();        
              jsonquestionList = jsonReader.readJSONFile();
              String quest;
              ArrayList<String> choices;
              Integer answer;
              ArrayList<String> hints;
              
              for (int i = 0; i < 10; i++){
                  quest = jsonquestionList.get(i).getQuestionText();
                  choices = (ArrayList<String>)jsonquestionList.get(i).getPossibleAnswers();
                  answer = jsonquestionList.get(i).getCorrectAnswerIdx();
                  hints = (ArrayList<String>)jsonquestionList.get(i).getExplanations();
                  Question question;
                  question = new Question(quest, choices, answer, hints);
                  questionList.set(i, question);
                  
              }
              
                }
            catch (Exception e){
                    System.out.println("error loading from file");
                    }
    }
}


