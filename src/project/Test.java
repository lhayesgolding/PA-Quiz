package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    
    private ArrayList<Question> questionList = new ArrayList<>();
    private int score;
    private int numberOfQuestions;
    private Timer testTimer;
    
    Test(){
        numberOfQuestions = 75;
        score = 0;
//        testTimer.schedule(task, 0, 75 * 60 * 1000);
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
    
    public void randomizeQuestionOrder(ArrayList questions){
        Collections.shuffle(questions);
    }

}
