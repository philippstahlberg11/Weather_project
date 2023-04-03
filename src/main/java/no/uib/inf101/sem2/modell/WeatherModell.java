package no.uib.inf101.sem2.modell;

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

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.IWeatherView;



public class WeatherModell implements IWeatherModell, IWeatherView{

    private String jsonStr;
    private Boolean value = false;

    public WeatherModell(String jsonString){
        this.jsonStr = jsonString;

        
    }
     
        public String getTimeDetails(int time, String detailString){
            // gets the most generic information, about wind, temperature, wind-direction etc. (check test.json for example.)
            
            // in case if we need to set something no nothing:
            if(detailString == "-"){
                return("");
            }
                try {
                    JSONObject jsonObj = new JSONObject(this.jsonStr);
                    JSONObject jsonObect = jsonObj.getJSONObject("properties");
                    JSONArray ja_data = jsonObect.getJSONArray("timeseries");
                    JSONObject data_object = ja_data.getJSONObject(time);
                    JSONObject data_videre =  data_object.getJSONObject("data");
                    JSONObject data_enda_videre = data_videre.getJSONObject("instant");
                    JSONObject data_details_instant_temp = data_enda_videre.getJSONObject("details");
        

                    return (data_details_instant_temp.get(detailString) + " " + getDetailsUnit(detailString));
        
                } catch (JSONException e) {
                    throw new JSONException(e);
                }

                
            
        }
        public Object getDetailsUnit(String detailString){
            // gets the most generic units, all the units used in the data.

            try {
                JSONObject jsonObj = new JSONObject(this.jsonStr);
                JSONObject jsonObect = jsonObj.getJSONObject("properties");
                JSONObject data_meta = jsonObect.getJSONObject("meta");
                JSONObject data_units = data_meta.getJSONObject("units");
                Object get_data_unit = data_units.get(detailString);

                // gets the unit for any value, temperature etc.

                return get_data_unit;
    
    
            } catch (JSONException e) {
                throw new JSONException(e);
            }
        
    }
    public ArrayList<Object> getMultipleTimeDetails(ArrayList<String> detailsUnits, int time){
        // returns a ararylist with multiple objects, and their corresponding detailstrings

        ArrayList<Object> timeDetailsList = new ArrayList<>();
        for(int i = 0; i < detailsUnits.size(); i++){
            timeDetailsList.add(getTimeDetails(time, detailsUnits.get(i)));
        }   


        // supposed to return with multiple timedetails and unit, and they are corresponding to eachother
        return timeDetailsList;

    }

     public ArrayList<String> genericDetailsInfoList(){
        // want to return with a arraylist with the most generic information we need from details:

        ArrayList<String> genericInfo = new ArrayList<>();

        genericInfo.add("air_temperature");
        genericInfo.add("ultraviolet_index_clear_sky");
        genericInfo.add("relative_humidity");
        genericInfo.add("wind_speed");
        genericInfo.add("air_pressure_at_sea_level");
        genericInfo.add("wind_from_direction");
        

        return genericInfo;


     }

     public ArrayList<String> getNextHoursDetails(int timeLimit){

        // ha en arraylist til hver time som er også få de mest generic-infoen om hver av timene:
        ArrayList<String> nextHourgenericInfo = new ArrayList<>();

        for(int i = 0; i < timeLimit; i ++){
                    JSONObject jsonObj = new JSONObject(this.jsonStr);
                    JSONObject jsonObect = jsonObj.getJSONObject("properties");
                    JSONArray ja_data = jsonObect.getJSONArray("timeseries");
                    JSONObject data_object = ja_data.getJSONObject(i);

                    nextHourgenericInfo.add(data_object.getString("time"));

        }

        return nextHourgenericInfo;

     }

     public String convertTimeToHours(String time){
        // 2023-04-03T15:00:00Z¨
        String[] arr = time.split("-|:|T|Z");
        // arr (eks.) : [2023, 04, 03, 15, 00, 00]
        return arr[3];
    }

    public String iconString(int timeNext, int time){
        //System.out.println(getOnlyJsonValues(strJson));
        ArrayList<Integer> i = new ArrayList<>();
        i.add(1);
        i.add(6);
        i.add(12);


            JSONObject jsonObj = new JSONObject(this.jsonStr);
            JSONObject jsonObect = jsonObj.getJSONObject("properties");
            JSONArray ja_data = jsonObect.getJSONArray("timeseries");
            JSONObject data_object = ja_data.getJSONObject(time);
            JSONObject data_videre =  data_object.getJSONObject("data");
            JSONObject data_enda_videre = data_videre.getJSONObject("next_" + i.get(timeNext) + "_hours");
            JSONObject data_details_instant_temp = data_enda_videre.getJSONObject("summary");

            // få tak i unit til temperatur
            //System.out.println(ja_data_data.getString("time"));
            return data_details_instant_temp.getString("symbol_code") + "";

   
      
}
     

     
         
    }



