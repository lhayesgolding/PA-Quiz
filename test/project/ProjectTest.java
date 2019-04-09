/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.stage.Stage;
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
public class ProjectTest {
    
    public ProjectTest() {
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
     * Test of start method, of class Project.
     */
    @Test
    public void testStart() throws Exception {
        // Is this testable??
        /*
        System.out.println("start");
        Stage stage = null;
        Project instance = new Project();
        instance.start(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }


    /**
     * Test of getNumOfQuestions method, of class Project.
     */
    @Test
    public void testSetAndGetNumOfQuestions() {
        System.out.println("setAndGetNumOfQuestions");
        Project.setNumOfQuestions(10);
        assertEquals(10, Project.getNumOfQuestions()); 
    }

    /**
     * Test of getTest method, of class Project.
     */
    @Test
    public void testGetTest() {
        System.out.println("getTest");
        //better way to test this?
        assertNotNull(Project.getTest());
    }

    /**
     * Test of setTest method, of class Project.
     */
    @Test
    public void testSetTest() {
        //testable?
    }

    /**
     * Test of setUsername method, of class Project.
     */
    @Test
    public void testSetUsernameAndGetCurrentUser() {
        System.out.println("setUsernameAndGetCurrentUser");
        String username = "thisUsername";
        Project.setUsername(username);
        assertEquals(username, Project.getCurrentUser());
    }

    /**
     * Test of addNewUser method, of class Project.
     */
    @Test
    public void testAddNewUser() throws FileNotFoundException {
        System.out.println("addNewUser");
        File file = new File("src/datafiles/userstorage.txt");
        long length1 = file.length();
        User newUser = new User("newName", "newEmail", "newUserID", "newPassword");
        Project.addNewUser(newUser);
        long length2 = file.length();
        assertTrue(length2 > length1);
    }

    /**
     * Test of initializeUserMap method, of class Project.
     */
    @Test
    public void testInitializeUserMap() {
        System.out.println("initializeUserMap");
        Project.initializeUserMap();
        //how to test this??
    }

    /**
     * Test of valid method, of class Project.
     */
    @Test
    public void testValid() {
        System.out.println("valid");
        Project.initializeUserMap();
        //this works specifically for the userstorage file at the time of coding
        //is there a better way?
        assertTrue(Project.valid("tom2", "password2"));
        assertTrue(Project.valid("rick9", "poipoi"));
    }

    /**
     * Test of getusermap method, of class Project.
     */
    @Test
    public void testGetusermap() {
        System.out.println("getusermap");
        //better way to test this?
        assertNotNull(Project.getusermap());
    }

    /**
     * Test of existingUser method, of class Project.
     */
    @Test
    public void testExistingUser() {
        Project.initializeUserMap();
        assertTrue(Project.existingUser("rick9"));
        assertFalse(Project.existingUser("poipoi"));
    }

    /**
     * Test of getUsersName method, of class Project.
     */
    @Test
    public void testGetUsersName() {
        Project.initializeUserMap();
        Project.setUsername("rick9");
        assertEquals("Rick", Project.getUsersName());
    }
    
}
