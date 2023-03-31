package no.uib.inf101.sem2;

import java.awt.List;
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

import no.uib.inf101.sem2.view.WeatherView;


// COPIED EXTERNAL FROM YT_VIDEOS
public class JsonParser{
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
        

                    return (data_details_instant_temp.get(detailString) + " " + JsonParser.getDetailsUnit(jsonString, detailString));
        
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

                // gets the unit for any value, temperature etc.

                return get_data_unit;
    
    
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                throw new JSONException(e);
                //e.printStackTrace();
            }
        
    }
    public static ArrayList<Object> getMultipleTimeDetails(String JsonDetails, ArrayList<String> detailsUnits, int time){
        // returns a ararylist with multiple objects, and their corresponding detailstrings

        ArrayList<Object> timeDetailsList = new ArrayList<>();
        for(int i = 0; i < detailsUnits.size(); i++){
            timeDetailsList.add(getTimeDetails(JsonDetails, time, detailsUnits.get(i)));
        }   


        // supposed to return with multiple timedetails and unit, and they are corresponding to eachother
        return timeDetailsList;

    }

     public static ArrayList<String> genericDetailsInfoList(){
        // want to return with a arraylist with the most generic information we need from details:

        ArrayList<String> genericInfo = new ArrayList<>();

        genericInfo.add("air_temperature");
        genericInfo.add("cloud_area_fraction");
        genericInfo.add("ultraviolet_index_clear_sky");
        genericInfo.add("relative_humidity");
        genericInfo.add("wind_speed");
        genericInfo.add("air_pressure_at_sea_level");
        genericInfo.add("wind_from_direction");
        

        return genericInfo;

        /* 
         *    "air_pressure_at_sea_level": 1013.4,
                "air_temperature": 5,
                "air_temperature_percentile_10": 4.2,
                "air_temperature_percentile_90": 5.7,
                "cloud_area_fraction": 15.1,
                "cloud_area_fraction_high": 0,
                "cloud_area_fraction_low": 2.4,
                "cloud_area_fraction_medium": 0.2,
                "dew_point_temperature": -7.1,
                "fog_area_fraction": 0,
                "relative_humidity": 41.7,
                "ultraviolet_index_clear_sky": 1.7,
                "wind_from_direction": 350.6,
                "wind_speed": 5,
                "wind_speed_of_gust": 9.8,
                "wind_speed_percentile_10": 3.8,
                "wind_speed_percentile_90": 4.8
         */
        
            /** INFO WE WANT TO ADD:
             * 
             * air_temperature
             * cloud_area_fraction
             * relative_humidity
             * ultraviolet_index_clear_sky
             * wind_from_direction
             * wind_speed
             * air_pressure_at_sea_level
             */
        


     }

        
    }

