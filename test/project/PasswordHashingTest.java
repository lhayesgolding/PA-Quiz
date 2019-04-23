/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import model.PasswordHashing;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anonymous
 */
public class PasswordHashingTest {
    
    PasswordHashing instance;
    String password;
    
    public PasswordHashingTest() {
        instance = new PasswordHashing();
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
     * Test of hashPassword method, of class PasswordHashing.
     */
    @Test
    public void testHashPassword() throws Exception {
        password = "password";
        String hashedPassword = instance.hashPassword(password);
        assertNotNull(hashedPassword);
        assertNotEquals(password,hashedPassword);
        assertNotEquals("",hashedPassword);
        
    }
    
}
