/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import model.User;
import model.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lizri
 */
public class UserTest {
    
    String testName = "testName";
    String testEmail = "testEmail";
    String testUserID = "testUserID";
    String testPassword = "testPassword";
    User testUser;
    
    public UserTest() throws FileNotFoundException {
        this.testUser = new User(testName, testEmail, testUserID, testPassword);
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
     * Test of getName method, of class User.
     */
    @org.junit.Test
    public void testGetNameAndSetName() {
        assertEquals("testName", testUser.getName());
        testUser.setName("testName1");
        assertEquals("testName1", testUser.getName());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @org.junit.Test
    public void testGetEmailAndSetEmail() {
        assertEquals("testEmail", testUser.getEmail());
        testUser.setEmail("testEmail1");
        assertEquals("testEmail1", testUser.getEmail());
    }


    /**
     * Test of getUserID method, of class User.
     */
    @org.junit.Test
    public void testGetUserIDAndSetUserID() {
        assertEquals("testUserID", testUser.getUserID());
        testUser.setUserID("testUserID1");
        assertEquals("testUserID1", testUser.getUserID());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @org.junit.Test
    public void testGetPasswordAndSetPassword() {
        assertEquals("testPassword", testUser.getPassword());
        testUser.setPassword("testPassword1");
        assertEquals("testPassword1", testUser.getPassword());
    }

    /**
     * Test of addScore and saveScores methods, of class User.
     */
    @org.junit.Test
    public void testAddScoreandSaveScores() throws IOException {
        Test test = new Test();
        testUser.addScore(test);
        testUser.saveScores();
        //having trouble figuring out how to test this
    }
    
}
