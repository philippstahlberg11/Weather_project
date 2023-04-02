package no.uib.inf101.sem2;

import no.uib.inf101.sem2.view.SampleView;
import no.uib.inf101.sem2.view.WeatherView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONValue;
//import org.json.parser.JSONParser;

import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.modell.WeatherModell;
import no.uib.inf101.sem2.modell.JsonString;

public class Main {
  public static void main(String[] args) {
    // need to get the JSON file working first
    System.setProperty("http.agent", "Chrome");
    String url = "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054";


    JsonString stringJson = new JsonString(url);
    String stringJsonURL = stringJson.getJSONFromURL();
    WeatherModell modell = new WeatherModell(stringJsonURL);

    WeatherView view = new WeatherView(modell.getMultipleTimeDetails(modell.genericDetailsInfoList(), 0), modell.genericDetailsInfoList());

    System.out.println(modell.getNextHoursDetails(50));
        // limit på 86 i "time" par.


    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);

  }
}
