package no.uib.inf101.sem2.modell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

public class WeatherModell implements IWeatherModell {

    private String jsonStr;

    public WeatherModell(String jsonString) {
        this.jsonStr = jsonString;

    }

    @Override
    public String getTimeDetailsGenerallString(int time, String detailString) {

        // in case if we need to set something to nothing:
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
                e1.printStackTrace();
            }
        }
        return detailString;

    }

    @Override
    public String getTimeDetails(int time, String detailString) {

        // in case if we need to set something to nothing:
        if (detailString == "-") {
            return ("");
        }
        return (getTimeDetailsGenerallString(time, detailString) + " " + getDetailsUnit(detailString));

    }

    @Override
    public double getAverageOfArray(List<String> arrayList) {

        int average = 0;
        for (String i : arrayList) {
            try {
                i = i.replaceAll("\\s+", "");
                average += Double.parseDouble(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        average = average / (arrayList.size());

        return average;
    }

    @Override
    public Object getDetailsUnit(String detailString) {

        try {
            JSONObject jsonObj = new JSONObject(this.jsonStr);
            JSONObject jsonObect = jsonObj.getJSONObject("properties");
            JSONObject data_meta = jsonObect.getJSONObject("meta");
            JSONObject data_units = data_meta.getJSONObject("units");
            Object get_data_unit = data_units.get(detailString);

            return get_data_unit;

        } catch (JSONException e) {
            throw new JSONException(e);
        }

    }

    @Override
    public ArrayList<Object> getMultipleTimeDetails(ArrayList<String> detailsUnits, int time) {

        ArrayList<Object> timeDetailsList = new ArrayList<>();
        for (int i = 0; i < detailsUnits.size(); i++) {
            timeDetailsList.add(getTimeDetails(time, detailsUnits.get(i)));
        }

        return timeDetailsList;

    }

    @Override
    public ArrayList<Integer> getUniqueValuesOnlyOfArray(ArrayList<Integer> arrayList) {

        ArrayList<Integer> newList = new ArrayList<>();
        Set<Integer> uniqueGas = new HashSet<Integer>(arrayList);

        for (Integer u : uniqueGas) {
            newList.add(u);
        }
        return newList;
    }

    @Override
    public ArrayList<String> genericDetailsInfoList() {

        ArrayList<String> genericInfo = new ArrayList<>();

        genericInfo.add("air_temperature");
        genericInfo.add("ultraviolet_index_clear_sky");
        genericInfo.add("relative_humidity");
        genericInfo.add("wind_speed");
        genericInfo.add("air_pressure_at_sea_level");
        genericInfo.add("wind_from_direction");

        return genericInfo;

    }

    @Override
    public ArrayList<String> genericTitleDetailsInfoList() {
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

    @Override
    public ArrayList<String> getNextHoursDetails(int timeLimit) {

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

    @Override
    public ArrayList<String> getNextHoursDates(int timeLimit) {

        ArrayList<String> nextHourgenericInfo = new ArrayList<>();
        ArrayList<String> newlist = new ArrayList<>();

        for (int i = 0; i < timeLimit; i++) {
            JSONObject jsonObj = new JSONObject(this.jsonStr);
            JSONObject jsonObect = jsonObj.getJSONObject("properties");
            JSONArray ja_data = jsonObect.getJSONArray("timeseries");
            JSONObject data_object = ja_data.getJSONObject(i);

            nextHourgenericInfo.add(data_object.getString("time"));

            newlist.add(convertTimeToDate(nextHourgenericInfo.get(i)));
        }

        return newlist;

    }

    @Override
    public String convertTimeToHours(String time) {
        // 2023-04-03T15:00:00Z¨
        String[] arr = time.split("-|:|T|Z");
        // arr (eks.) : [2023, 04, 03, 15, 00, 00]
        return arr[3];
    }

    @Override
    public String convertTimeToDate(String time) {
        // 2023-04-03T15:00:00Z¨
        String[] arr = time.split("-|:|T|Z");
        // arr (eks.) : [2023, 04, 03, 15, 00, 00]
        return arr[2];
    }

    @Override
    public String iconString(int timeNext, int time) {
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

        return data_details_instant_temp.getString("symbol_code") + "";

    }

    @Override
    public List<List<String>> getListOfMultipleTimeDetails(int timelimit, String detailString) {

        List<List<String>> listOfLists = new ArrayList<List<String>>();
        ArrayList<Integer> somelist = new ArrayList<>();

        int constant = 0;
        // correctDate[2]
        for (int i = 0; i < timelimit; i++) {
            Integer u = Integer.parseInt(convertTimeToDate(getNextHoursDetails(timelimit).get(i)));
            somelist.add(u);
            listOfLists.add(new ArrayList<String>());
            try {
                if (u.equals(getUniqueValuesOnlyOfArray(somelist).get(constant))) {
                    listOfLists.get(constant).add(getTimeDetailsGenerallString(i, detailString));
                } else {
                    constant++;
                    listOfLists.get(constant).add(getTimeDetailsGenerallString(i, detailString));
                    // next date!
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return listOfLists;

    }

    @Override
    public List<String> getUniqueValuesOnlyOfArrayString(List<String> arrayList) {
        List<String> newList = new ArrayList<String>(new HashSet<String>(arrayList));
        return newList;
    }

}
