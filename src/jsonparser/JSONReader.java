package jsonparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {
  
    /**
     * Returns questionList
     * @return the list of questions from the Questions.json file
     */
    public ArrayList readJSONQuestionsFile() throws Exception{
        File jsonFile = new File("Questions.json");
        BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
        StringBuilder builder = new StringBuilder();
        
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
            line = reader.readLine();
        }
        String importedJson = builder.toString();
        ArrayList<JSONQuestionObject> questionList = new Gson().fromJson(importedJson, 
                new TypeToken<List<JSONQuestionObject>>(){}.getType());  

        return questionList;
    }
    
    /**
     * Returns testList
     * @return the list of tests from the user's test.json file
     */
    public ArrayList readJSONUserTestFile(String userID) throws Exception{
      File testFile = new File("/src/datafiles" + userID + "Tests.json");
      BufferedReader reader = new BufferedReader(new FileReader(testFile));
      StringBuilder builder = new StringBuilder();
      
      String line = reader.readLine();
      while(line != null){
        builder.append(line);
        builder.append(System.lineSeparator());
        line = reader.readLine();
      }
      
      String importedJson = builder.toString();
      ArrayList<JSONUserAnswersObject> testList = new Gson().fromJson(importedJson, 
              new TypeToken<List<JSONUserAnswersObject>>(){}.getType());
      return testList;
    }
}
