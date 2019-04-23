/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** @author ConorLaptop */
public class PasswordHashing {

  /**
   * Hashes the password entered by the user
   *
   * @param password the plain text password
   * @return the hashed password
   * @throws NoSuchAlgorithmException thrown if the hash fails
   */
  public String hashPassword(String password) throws NoSuchAlgorithmException {
    String algorithm = "SHA";

    byte[] plainText = password.getBytes();

    MessageDigest md = MessageDigest.getInstance(algorithm);

    md.reset();
    md.update(plainText);
    byte[] encodedPassword = md.digest();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < encodedPassword.length; i++) {
      if ((encodedPassword[i] & 0xff) < 0x10) {
        sb.append("0");
      }

      sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
    }

    System.out.println("Plain    : " + password);
    System.out.println("Encrypted: " + sb.toString());

    return sb.toString();
  }
}
