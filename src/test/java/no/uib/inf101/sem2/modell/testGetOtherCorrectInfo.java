package no.uib.inf101.sem2.modell;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class testGetOtherCorrectInfo {

    private static WeatherModell modell;
    private static TableModel model;

    private static MarsModell modellMars;

    @BeforeAll
    public static void setup() {
        System.setProperty("http.agent", "Chrome");
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054";

        JsonString stringJson = new JsonString(url);
        // gives the option to either have a jsonobject from a url/file:
        String stringJsonURL = stringJson.getJSONFromURL();

        modell = new WeatherModell(stringJsonURL);

        Table board = new Table(7, modell.genericDetailsInfoList().size() + 2);

        model = new TableModel(board, modell);

        String MarsUrl = "https://api.maas2.apollorion.com/";

        JsonString MarsJson = new JsonString(MarsUrl);
        String stringMarsURL = MarsJson.getJSONFromURL();

        modellMars = new MarsModell(stringMarsURL);

    }

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

    @Test
    public void testGetRealIcons() {

        String FirstHourIconString = modell.getCurrentWeatherString(0, 0);
        String SixHourIconString = modell.getCurrentWeatherString(1, 0);
        String TwelveHourIconString = modell.getCurrentWeatherString(2, 0);

        assertTrue(model.checkIfIcon(FirstHourIconString));
        assertTrue(model.checkIfIcon(SixHourIconString));
        assertTrue(model.checkIfIcon(TwelveHourIconString));

    }

    // getUniqueValuesOnlyOfArrayString(
    @Test
    public void testGetUniqueValuesOnlyOfArrayString() {

        ArrayList<String> test = new ArrayList<>();
        test.add("first");
        test.add("first");
        test.add("second");
        test.add("first");
        test.add("second");
        List<String> getUniqueValuesTest = modell.getUniqueValuesOnlyOfArrayString(test);
        int num = 0;
        for (int i = 1; i < getUniqueValuesTest.size(); i++) {
            assertFalse(getUniqueValuesTest.get(num).equals(getUniqueValuesTest.get(i)));
            num++;
        }
    }

    // testing for the mars values, like temperature and so on:::

    @Test
    public void testMarsTemperature() {
        String anyValue = modellMars
                .getTimeDetailsGenerallStringForMars(modellMars.genericDetailsInfoListForMars().get(0));
        String[] newDate = anyValue.split("-");

        int month = convert(newDate[1]);
        int day = convert(newDate[2]);

        assertTrue(newDate[0].contains("20"));
        assertTrue(month > -1 && month < 13);
        assertTrue(day > -1 && day < 32);

    }

    @Test
    public void testMarsPressure() {
        String anyValue = modellMars
                .getTimeDetailsGenerallStringForMars(modellMars.genericDetailsInfoListForMars().get(2));
        int intValue = convert(anyValue);

        assertTrue(intValue > -1 && intValue < 2000);

    }

    @Test
    public void testMarsUV() {
        String anyValue = modellMars
                .getTimeDetailsGenerallStringForMars(modellMars.genericDetailsInfoListForMars().get(3));

        // check multiple contains:
        // https://stackoverflow.com/questions/69533097/check-if-a-string-contains-multiple-values-in-java-without-using-or-operators
        assertTrue(List.of("Mod", "High", "Low").stream().anyMatch(anyValue::contains));

    }

    @Test
    public void testMarsLowestTemperature() {
        String anyValue = modellMars
                .getTimeDetailsGenerallStringForMars(modellMars.genericDetailsInfoListForMars().get(4));
        int intValue = convert(anyValue);

        assertTrue(intValue > -100 && intValue < 22);

    }

    @Test
    public void testMarsHighestTemperature() {
        String anyValue = modellMars
                .getTimeDetailsGenerallStringForMars(modellMars.genericDetailsInfoListForMars().get(5));
        int intValue = convert(anyValue);

        assertTrue(intValue > -100 && intValue < 22);

    }

}
