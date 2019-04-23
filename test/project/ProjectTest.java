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
     * Test of getNumOfQuestions and setNumOfQuestions methods, of class Project.
     */
    @Test
    public void testSetAndGetNumOfQuestions() {
        System.out.println("setAndGetNumOfQuestions");
        Project.setNumOfQuestions(10);
        assertEquals(10, Project.getNumOfQuestions()); 
    }
   

    /**
     * Test of setUsername and getCurrentUser methods, of class Project.
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
        HashMap hash = new HashMap();
        Project.setUserMap(hash);
        String mapHash = Project.getusermap().toString();
        Project.initializeUserMap();
        String mapHash2 = Project.getusermap().toString();
        HashMap map = Project.getusermap();
        assertNotNull(map);
        assertNotEquals(mapHash,mapHash2);
    }

    /**
     * Test of valid method, of class Project.
     */
    @Test
    public void testValid() {
        System.out.println("valid");
        Project.initializeUserMap();
        User user = (User)Project.getusermap().get("newUserID");
        String username = user.getUserID();
        String password = user.getPassword();
        assertTrue(Project.valid(username, password));
        password = "fakepassword";
        assertFalse(Project.valid(username, password));
    }

    /**
     * Test of existingUser method, of class Project.
     */
    @Test
    public void testExistingUser() {
        Project.initializeUserMap();
        assertTrue(Project.existingUser("newUserID"));
        assertFalse(Project.existingUser("poipoi"));
    }

    /**
     * Test of getUsersName method, of class Project.
     */
    @Test
    public void testGetUsersName() {
        Project.initializeUserMap();
        Project.setUsername("newUserID");
        assertEquals("newName", Project.getUsersName());
    }
    
}
