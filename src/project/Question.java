package project;

import java.util.ArrayList;

public class Question {
    
    private String quest;
    private ArrayList<String> choices = new ArrayList<>(); // made this an ArrayList for convenience
    private Integer answer;
    private ArrayList<String> hints = new ArrayList<>(); // same with this one
    
    Question(String quest0, ArrayList<String> choices0, Integer answer0, 
            ArrayList<String> hints0 ) {
        quest = quest0;
        choices = choices0;
        answer = answer0;
        hints = hints0;
        
    }
    
    public String getQuest() {
        return quest;
    }
    
    public ArrayList getChoices() {
        return choices;
    }
    
    public Integer getAnswer() {
        return answer;
    }
    
    public ArrayList getHints() {
        return hints;
    }
    
}
