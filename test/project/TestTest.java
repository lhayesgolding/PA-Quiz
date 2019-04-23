/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import model.Test;
import java.io.File;
import java.lang.annotation.Annotation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anonymous
 */
public class TestTest {
    
    
    public TestTest() {
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
     * Test of setUserAnswer, getUserAnswer, getQuestion, getAnswer, 
     * calculateScore, of class Test. initializequestionList is also called.
     */
    @org.junit.Test
    public void testVarious() {
        Test test = new Test(10);
        for (int i = 0; i < 10; i++) {
            test.getQuestion(i).setUserAnswer(0);
        }
        int numRight = 0;
        for (int i = 0; i < 10; i++) {
            if (test.getQuestion(i).getUserAnswer() == test.getQuestion(i).getAnswer())
                numRight++;
        }
        test.calculateScore();
        assertEquals(numRight, test.getScore());
    }
    

    /**
     * Test of setTestType, getTestType, resetTestType method, of class Test.
     */
    @org.junit.Test
    public void testResetTestType() {
        Test test = new Test();
        test.setTestType("recorded");
        assertEquals("recorded", test.getTestType());
        test.resetTestType();
        assertNull(test.getTestType());
    }
    
}
