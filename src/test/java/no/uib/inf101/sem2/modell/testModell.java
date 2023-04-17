package no.uib.inf101.sem2.modell;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.modell.JsonObject;
import no.uib.inf101.sem2.modell.WeatherModell;





public class testModell {
    

    // From this site: https://www.geeksforgeeks.org/how-to-convert-a-string-to-an-int-in-java/
    // Function to convert String to integer
    public static int convert(String str)
    {
        int val = 0;  
        // Convert the String
        try {
            val = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
  
            // This is thrown when the String
            // contains characters other than digits
            System.out.println("Invalid String");
        }
        return val;
    }


    @Test
    public void testModellRunOne(){
        
        System.setProperty("http.agent", "Chrome");
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054";
    
        JsonObject stringJson = new JsonObject(url);
        // gives the option to either have a jsonobject from a url/file:
        String stringJsonURL = stringJson.getJSONFromURL();
    
        WeatherModell modell = new WeatherModell(stringJsonURL);

        String value = "air_temperature";

        String tempValue = modell.getTimeDetailsGenerallString(0, value);
        int anyNumber = convert(tempValue);


        String wholeString = modell.getTimeDetails(0, value);


        assertTrue(wholeString.contains("celsius"));
        assertTrue(anyNumber > -20 && anyNumber < 30);


    }





}
