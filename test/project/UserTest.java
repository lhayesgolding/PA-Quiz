/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
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
    @Test
    public void testGetNameAndSetName() {
        assertEquals("testName", testUser.getEmail());
        testUser.setName("testName1");
        assertEquals("testName1", testUser.getEmail());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmailAndSetEmail() {
        assertEquals("testEmail", testUser.getEmail());
        testUser.setEmail("testEmail1");
        assertEquals("testEmail1", testUser.getEmail());
    }


    /**
     * Test of getUserID method, of class User.
     */
    @Test
    public void testGetUserIDAndSetUserID() {
        assertEquals("testUserID", testUser.getUserID());
        testUser.setUserID("testUserID1");
        assertEquals("testUserID1", testUser.getUserID());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPasswordAndSetPassword() {
        assertEquals("testPassword", testUser.getPassword());
        testUser.setPassword("testPassword1");
        assertEquals("testPassword1", testUser.getPassword());
    }


    /**
     * Test of addScore method, of class User.
     */
    @Test
    public void testAddScore() {
        //How to test? Won't let me instantiate a Test object.
    }

    /**
     * Test of saveScores method, of class User.
     */
    @Test
    public void testSaveScores() throws Exception {
        
    }
    
}
