package jsonparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    public ArrayList readJSONFile() throws Exception{
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
}
