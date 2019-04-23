/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import model.Question;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lizri
 */
public class QuestionTest {
        String testQuest = "Why?";
        ArrayList<String> testChoices = new ArrayList<String>() {
                {
                    add("Choice0");
                    add("Choice1");
                    add("Choice2");
                }       
        };
        int testAnswer = 1;
        ArrayList<String> testHints = new ArrayList<String>() {
                {
                    add("Hint0");
                    add("Hint1");
                    add("Hint2");
                }       
        };
        int QuestionID = 1;
        Question testQuestion = new Question(testQuest, testChoices, testAnswer, testHints, QuestionID);
    
    public QuestionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getQuest method, of class Question.
     */
    @Test
    public void testGetQuest() { 
        assertEquals("Why?", testQuestion.getQuest());
    }

    /**
     * Test of getChoices method, of class Question.
     */
    @Test
    public void testGetChoices() {
        assertEquals("[Choice0, Choice1, Choice2]", testQuestion.getChoices().toString());
    }

    /**
     * Test of getAnswer method, of class Question.
     */
    @Test
    public void testGetAnswer() {
        assertTrue(testQuestion.getAnswer() == 1);
    }

    /**
     * Test of getHints method, of class Question.
     */
    @Test
    public void testGetHints() {
        assertEquals("[Hint0, Hint1, Hint2]", testQuestion.getHints().toString());
    }

    /**
     * Test of getUserAnswer method, of class Question.
     */
    @Test
    public void testGetUserAnswerAndIsCorrect() {
        assertTrue(testQuestion.getUserAnswer() == -1);
        assertFalse(testQuestion.isCorrect());
        testQuestion.setUserAnswer(1);
        assertTrue(testQuestion.getUserAnswer() == 1);
        assertTrue(testQuestion.isCorrect());
    }   
}
