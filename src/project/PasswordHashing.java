/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author ConorLaptop
 */
public class PasswordHashing {
  
    public byte[] hashPassword(String password) throws NoSuchAlgorithmException{
      SecureRandom random = new SecureRandom();
      byte[] salt = new byte[16];
      random.nextBytes(salt);
      
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(salt);
      
      byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
      
      return hashedPassword;
    }    
}
