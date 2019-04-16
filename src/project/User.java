package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class User {

    private String name;
    private String email;
    private String userID;
    private String password;
    private ArrayList<Double> scores = new ArrayList<>();

    User() {
        name = "Guest";
        email = "None";
        userID = "Guest";
        password = "None";
    }

    User(String n, String e, String u, String p) throws FileNotFoundException {
        name = n;
        email = e;
        userID = u;
        password = p;

        /*File file = new File(userID + "Scores");
        
        if (file.exists()) {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                scores.add(Double.parseDouble(s.nextLine()));
            }
        } */
    }

    /**
     * Returns name
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param x the name the user entered
     */
    public void setName(String x) {
        name = x;
    }

    /**
     * Returns email
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     * @param x the email the user entered
     */
    public void setEmail(String x) {
        email = x;
    }

    /**
     * Returns userID
     * @return the user ID of the user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets userID
     * @param x the user ID the user entered
     */
    public void setUserID(String x) {
        userID = x;
    }

    /**
     * Returns password
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param x the password the user entered
     */
    public void setPassword(String x) {
        password = x;
    }

    /**
     * adds a score to the scores ArrayList
     * @param t the test that needs its score added to the scores ArrayList
     */
    public void addScore(Test t) {
        double score = t.getScore();
        scores.add(score);
    }

    /**
     * saves the user's scores to a file
     * @throws FileNotFoundException thrown if the file is not found
     * @throws IOException thrown when I/O error occurs
     */
    public void saveScores() throws FileNotFoundException, IOException {
        File file = new File(userID + "Scores");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        for (double s : scores) {
            pw.println(s);
        }
        pw.close();
    }
}
