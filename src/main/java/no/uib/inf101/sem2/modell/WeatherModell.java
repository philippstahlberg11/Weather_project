package no.uib.inf101.sem2.modell;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.IWeatherView;
import no.uib.inf101.sem2.view.Inf101Graphics;

public class WeatherModell implements IWeatherModell, IWeatherView {

    private String jsonStr;
    private Boolean value = false;

    public WeatherModell(String jsonString) {
        this.jsonStr = jsonString;

    }
    public String getTimeDetailsGenerallString(int time, String detailString) {
        // gets the most generic information, about wind, temperature, wind-direction
        // etc. (check test.json for example.)

        // in case if we need to set something no nothing:
        if (detailString == "-") {
            return ("");
        }
        try {
            JSONObject jsonObj = new JSONObject(this.jsonStr);
            JSONObject jsonObect = jsonObj.getJSONObject("properties");
            JSONArray ja_data = jsonObect.getJSONArray("timeseries");
            JSONObject data_object = ja_data.getJSONObject(time);
            JSONObject data_videre = data_object.getJSONObject("data");
            JSONObject data_enda_videre = data_videre.getJSONObject("instant");
            JSONObject data_details_instant_temp = data_enda_videre.getJSONObject("details");

            return (data_details_instant_temp.get(detailString) + "");

        } catch (JSONException e) {
            try {
                throw new JSONException(e);
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return detailString;

    }

    public String getTimeDetails(int time, String detailString) {
        // gets the most generic information, about wind, temperature, wind-direction
        // etc. (check test.json for example.)

        // in case if we need to set something no nothing:
        if (detailString == "-") {
            return ("");
        }
         return (getTimeDetailsGenerallString(time, detailString) + " " + getDetailsUnit(detailString));


    }
    public double getAverageOfArray(List<String> arrayList){

        int average = 0;
        for(String i : arrayList){
            try{
            i = i.replaceAll("\\s+", "");
            average += Double.parseDouble(i);
            }
            catch(Exception e){
                System.out.println("INVALID LIST :" + e);
            }
        }
        average = average/(arrayList.size());

        return average;
    }

    public Object getDetailsUnit(String detailString) {
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

    public ArrayList<Object> getMultipleTimeDetails(ArrayList<String> detailsUnits, int time) {
        // returns a ararylist with multiple objects, and their corresponding
        // detailstrings

        ArrayList<Object> timeDetailsList = new ArrayList<>();
        for (int i = 0; i < detailsUnits.size(); i++) {
            timeDetailsList.add(getTimeDetails(time, detailsUnits.get(i)));
        }

        // supposed to return with multiple timedetails and unit, and they are
        // corresponding to eachother
        return timeDetailsList;

    }
    public ArrayList<Integer> getUniqueValuesOnlyOfArray(ArrayList<Integer> arrayList){

        ArrayList<Integer> newList = new ArrayList<>();
        Set<Integer> uniqueGas = new HashSet<Integer>(arrayList);

        for(Integer u : uniqueGas){
            newList.add(u);
        }
        return newList;
    }

    public ArrayList<String> genericDetailsInfoList() {
        // want to return with a arraylist with the most generic information we need
        // from details:

        ArrayList<String> genericInfo = new ArrayList<>();

        genericInfo.add("air_temperature");
        genericInfo.add("ultraviolet_index_clear_sky");
        genericInfo.add("relative_humidity");
        genericInfo.add("wind_speed");
        genericInfo.add("air_pressure_at_sea_level");
        genericInfo.add("wind_from_direction");

        return genericInfo;

    }
    public ArrayList<String> genericTitleDetailsInfoList(){
        ArrayList<String> genericTitles = new ArrayList<>();

        genericTitles.add("Time");
        genericTitles.add("Weather");
        genericTitles.add("Temperature");
        genericTitles.add("UV");        
        genericTitles.add("Humidity");
        genericTitles.add("Wind speed(m/s)");
        genericTitles.add("Air pressure");
        genericTitles.add("Wind direction");


        return genericTitles;


    }

    public ArrayList<String> getNextHoursDetails(int timeLimit) {

        // ha en arraylist til hver time som er også få de mest generic-infoen om hver
        // av timene:
        ArrayList<String> nextHourgenericInfo = new ArrayList<>();

        for (int i = 0; i < timeLimit; i++) {
            JSONObject jsonObj = new JSONObject(this.jsonStr);
            JSONObject jsonObect = jsonObj.getJSONObject("properties");
            JSONArray ja_data = jsonObect.getJSONArray("timeseries");
            JSONObject data_object = ja_data.getJSONObject(i);

            nextHourgenericInfo.add(data_object.getString("time"));

        }

        return nextHourgenericInfo;

    }

    public String convertTimeToHours(String time) {
        // 2023-04-03T15:00:00Z¨
        String[] arr = time.split("-|:|T|Z");
        // arr (eks.) : [2023, 04, 03, 15, 00, 00]
        return arr[3];
    }
    public String dateString(String time){
        // 2023-04-03T15:00:00Z¨
        String[] arr = time.split("-|:|T|Z");
        // arr (eks.) : [2023, 04, 03, 15, 00, 00]
        return arr[2];
    }

    public String iconString(int timeNext, int time) {
        // System.out.println(getOnlyJsonValues(strJson));
        // choose between next_1_hours, next_6_hours, og next_12_hours... (beware:
        // next_12_hours often has a less stronger certanty)
        ArrayList<Integer> i = new ArrayList<>();
        i.add(1);
        i.add(6);
        i.add(12);

        JSONObject jsonObj = new JSONObject(this.jsonStr);
        JSONObject jsonObect = jsonObj.getJSONObject("properties");
        JSONArray ja_data = jsonObect.getJSONArray("timeseries");
        JSONObject data_object = ja_data.getJSONObject(time);
        JSONObject data_videre = data_object.getJSONObject("data");
        JSONObject data_enda_videre = data_videre.getJSONObject("next_" + i.get(timeNext) + "_hours");
        JSONObject data_details_instant_temp = data_enda_videre.getJSONObject("summary");

        // få tak i unit til temperatur
        // System.out.println(ja_data_data.getString("time"));
        return data_details_instant_temp.getString("symbol_code") + "";

    }
    public List<List<String>> getListOfMultipleTimeDetails(int timelimit, String detailString){


        List<List<String>> listOfLists = new ArrayList<List<String>>(); 
        ArrayList<Integer> somelist = new ArrayList<>();

        int constant = 0;
        //correctDate[2]
        for (int i = 0; i < timelimit; i++){
            Integer u = Integer.parseInt(dateString(getNextHoursDetails(timelimit).get(i)));
            somelist.add(u);
            listOfLists.add(new ArrayList<String>());
            try{
                //System.out.println(getUniqueValuesOnlyOfArray(somelist));
                if(u.equals(getUniqueValuesOnlyOfArray(somelist).get(constant))){
                    listOfLists.get(constant).add(getTimeDetailsGenerallString( i, detailString));
                    
                  //  nextHArrayList.add(getTimeDetails(strJson, i, "air_temperature"));
                  //  nextHArrayList.set(i, nextHArrayList.get(i).replaceAll("\\s+",""));
                }
                else{
                    constant++;
                    listOfLists.get(constant).add(getTimeDetailsGenerallString( i, detailString));
                    // next date!
                }
            }
            catch(Exception e){
                e.printStackTrace();

            }
        }
        return listOfLists;


    }




}
