package project;

import java.util.ArrayList;

public class Question {
    
    private String quest;
    private ArrayList<String> choices = new ArrayList<>(); // made this an ArrayList for convenience
    private char answer;
    private ArrayList<String> hints = new ArrayList<>(); // same with this one
    
    Question() {
        
    }
    
    public String getQuest() {
        return quest;
    }
    
    public ArrayList getChoices() {
        return choices;
    }
    
    public char getAnswer() {
        return answer;
    }
    
    public ArrayList getHints() {
        return hints;
    }
    
}
