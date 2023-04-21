package no.uib.inf101.sem2.modell;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class testGetGenericInfo {

    private static WeatherModell modell;

    @BeforeAll
    public static void setup() {

        System.setProperty("http.agent", "Chrome");
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054";

        JsonObject stringJson = new JsonObject(url);
        // gives the option to either have a jsonobject from a url/file:
        String stringJsonURL = stringJson.getJSONFromURL();

        modell = new WeatherModell(stringJsonURL);
    }

    // From this site:
    // https://www.geeksforgeeks.org/how-to-convert-a-string-to-an-int-in-java/
    // Function to convert String to integer
    public static int convert(String str) {
        int val = 0;
        // Convert the String
        try {
            val = Integer.parseInt(str);
        } catch (NumberFormatException e) {

            // This is thrown when the String
            // contains characters other than digits
            System.out.println("Invalid String");
        }
        return val;
    }

    // testing for the hour-by-hour grid, that it shows realistic typical values!:::

    @Test
    public void testGetTemperature() {

        // air_temperature...
        String value = modell.genericDetailsInfoList().get(0);

        String anyValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(anyValue);

        String wholeString = modell.getTimeDetails(0, value);

        assertTrue(wholeString.contains("celsius"));
        assertTrue(anyNumber > -20 && anyNumber < 30);

    }

    @Test
    public void testGetUltravioletIndex() {

        // ultraviolet_index...
        String value = modell.genericDetailsInfoList().get(1);

        String anyValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(anyValue);

        String wholeString = modell.getTimeDetails(0, value);

        assertTrue(wholeString.contains("1"));
        assertTrue(anyNumber > -1 && anyNumber < 5);

    }

    @Test
    public void testGetHumidity() {

        // ultraviolet_index...
        String value = modell.genericDetailsInfoList().get(2);

        String anyValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(anyValue);

        String wholeString = modell.getTimeDetails(0, value);

        assertTrue(wholeString.contains("%"));
        assertTrue(anyNumber > -1 && anyNumber < 101);

    }

    @Test
    public void testGetWindSpeed() {

        // ultraviolet_index...
        String value = modell.genericDetailsInfoList().get(3);

        String anyValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(anyValue);

        String wholeString = modell.getTimeDetails(0, value);

        assertTrue(wholeString.contains("m/s"));
        assertTrue(anyNumber > -1 && anyNumber < 50);

    }

    @Test
    public void testGetAirPressure() {

        // ultraviolet_index...
        String value = modell.genericDetailsInfoList().get(4);

        String anyValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(anyValue);

        String wholeString = modell.getTimeDetails(0, value);

        assertTrue(wholeString.contains("hPa"));
        assertTrue(anyNumber > -1 && anyNumber < 1500);

    }

    @Test
    public void testGetWindFromDirection() {

        // ultraviolet_index...
        String value = modell.genericDetailsInfoList().get(5);

        String anyValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(anyValue);

        String wholeString = modell.getTimeDetails(0, value);

        assertTrue(wholeString.contains("degrees"));
        assertTrue(anyNumber > -1 && anyNumber < 360);

    }

    // testing for the day-by-day Grid, that it shows information that at least are
    // realistic typical values!:::

    @Test
    public void testGetListOfMultipleTimeDetailsForTemperature() {

        List<List<String>> value = modell.getListOfMultipleTimeDetails(5, modell.genericDetailsInfoList().get(0));

        for (int i = 0; i < value.get(0).size(); i++) {
            int anyNumber = convert(value.get(0).get(i));
            assertTrue(anyNumber > -20 && anyNumber < 30);
        }

    }

    @Test
    public void testGetListOfMultipleTimeDetailsForUltraviolet() {

        List<List<String>> value = modell.getListOfMultipleTimeDetails(5, modell.genericDetailsInfoList().get(1));

        for (int i = 0; i < value.get(0).size(); i++) {
            int anyNumber = convert(value.get(0).get(i));
            assertTrue(anyNumber > -1 && anyNumber < 5);
        }

    }

    @Test
    public void testGetListOfMultipleTimeDetailsForHumidity() {

        List<List<String>> value = modell.getListOfMultipleTimeDetails(5, modell.genericDetailsInfoList().get(2));

        for (int i = 0; i < value.get(0).size(); i++) {
            int anyNumber = convert(value.get(0).get(i));
            assertTrue(anyNumber > -1 && anyNumber < 101);
        }

    }

    @Test
    public void testGetListOfMultipleTimeDetailsForWindSpeed() {

        List<List<String>> value = modell.getListOfMultipleTimeDetails(5, modell.genericDetailsInfoList().get(3));

        for (int i = 0; i < value.get(0).size(); i++) {
            int anyNumber = convert(value.get(0).get(i));
            assertTrue(anyNumber > -1 && anyNumber < 50);
        }

    }

    @Test
    public void testGetListOfMultipleTimeDetailsForAirPressure() {

        List<List<String>> value = modell.getListOfMultipleTimeDetails(5, modell.genericDetailsInfoList().get(4));

        for (int i = 0; i < value.get(0).size(); i++) {
            int anyNumber = convert(value.get(0).get(i));
            assertTrue(anyNumber > -1 && anyNumber < 1500);
        }

    }

    @Test
    public void testGetListOfMultipleTimeDetailsForWindDirection() {

        List<List<String>> value = modell.getListOfMultipleTimeDetails(5, modell.genericDetailsInfoList().get(5));

        for (int i = 0; i < value.get(0).size(); i++) {
            int anyNumber = convert(value.get(0).get(i));
            assertTrue(anyNumber > -1 && anyNumber < 360);
        }

    }

}
