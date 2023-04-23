package no.uib.inf101.sem2.modell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

//  Copied from the video : https://www.youtube.com/watch?v=yLf2-r8w9lQ&t=186s
// to get the information from any json file/json url to a string!

public class JsonString {

    private String strName;

    public JsonString(String name) {
        this.strName = name;

    }

    public String getJSONFromFile() {
        // should be a file-name
        String jsonText = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(strName));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonText;
    }

    public String getJSONFromURL() {
        // should be a string-url
        String jsonText = "";

        try {
            URL url = new URL(strName);
            InputStream is = url.openStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";

            }

            is.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
