package no.uib.inf101.sem2.modell;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class MarsModell implements IMarsModell {

    private String jsonStr;

    public MarsModell(String jsonString) {
        this.jsonStr = jsonString;

    }

    @Override
    public String getTimeDetailsGenerallStringForMars(String detailString) {

        // in case if we need to set something no nothing:
        if (detailString == "-") {
            return ("");
        }
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String newString = jsonObj.getString(detailString);

            return (newString + "");

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
    public ArrayList<String> genericDetailsInfoListForMars() {

        ArrayList<String> genericInfo = new ArrayList<>();

        genericInfo.add("terrestrial_date");
        genericInfo.add("atmo_opacity");
        genericInfo.add("pressure");
        genericInfo.add("local_uv_irradiance_index");
        genericInfo.add("min_temp");
        genericInfo.add("max_temp");

        return genericInfo;

    }

    @Override
    public ArrayList<String> genericTitleDetailsInfoListForMars() {
        ArrayList<String> genericTitles = new ArrayList<>();

        genericTitles.add("Last update");
        genericTitles.add("Current Weather");
        genericTitles.add("Pressure");
        genericTitles.add("UV");
        genericTitles.add("Min. temp");
        genericTitles.add("Max. temp");

        return genericTitles;
    }

}
