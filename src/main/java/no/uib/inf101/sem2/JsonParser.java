package no.uib.inf101.sem2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;


// COPIED EXTERNAL FROM YT_VIDEOS
public class JsonParser {
        public static String getJSONFromFile(String filename) {
            String jsonText = "";
            try {		
                BufferedReader bufferedReader = 
                              new BufferedReader(new FileReader(filename));
            
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
        
        public static String getJSONFromURL(String strUrl) {
            String jsonText = "";

            try {
                URL url = new URL(strUrl);
                InputStream is = url.openStream();
    
                BufferedReader bufferedReader = 
                                new BufferedReader(new InputStreamReader(is));
                
              
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
        public static String getTimeDetails(String jsonString, int time, String detailString){
            // gets the most generic information, about wind, temperature, wind-direction etc. (check test.json for example.)
                try {
                    JSONObject jsonObj = new JSONObject(jsonString);
                    JSONObject jsonObect = jsonObj.getJSONObject("properties");
                    JSONArray ja_data = jsonObect.getJSONArray("timeseries");
                    JSONObject data_object = ja_data.getJSONObject(time);
                    JSONObject data_videre =  data_object.getJSONObject("data");
                    JSONObject data_enda_videre = data_videre.getJSONObject("instant");
                    JSONObject data_details_instant_temp = data_enda_videre.getJSONObject("details");
        

                    return (data_details_instant_temp.get(detailString) + " " + JsonParser.getDetailsUnit(jsonString, "air_temperature"));
        
        
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    throw new JSONException(e);
                    //e.printStackTrace();
                }

                
            
        }
        public static Object getDetailsUnit(String jsonString, String detailString){
            // gets the most generic units, all the units used in the data.

            try {
                JSONObject jsonObj = new JSONObject(jsonString);
                JSONObject jsonObect = jsonObj.getJSONObject("properties");
                JSONObject data_meta = jsonObect.getJSONObject("meta");
                JSONObject data_units = data_meta.getJSONObject("units");
                Object get_data_unit = data_units.get(detailString);

                // få tak i unit til temperatur
    
                return get_data_unit;
    
    
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                throw new JSONException(e);
                //e.printStackTrace();
            }
        
    }
    public static <E> String getTemperatureAndUnitString(E temperature, E unit){
        return (temperature + " " + unit);
    }

     

        
    }

