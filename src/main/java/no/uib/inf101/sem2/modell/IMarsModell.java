package no.uib.inf101.sem2.modell;

import java.util.ArrayList;

public interface IMarsModell {

    /**
     * Gets the most generic information, f.ex. temperature-values
     * 
     * @param detailString a string, of what value you want to get (like "pressure")
     * @return a string of the value detailString represents
     */
    String getTimeDetailsGenerallStringForMars(String detailString);

    /**
     * Gets the generic details you would want from Mars
     * 
     * @return a Arraylist (consisting of strings) of the most generic values
     */
    ArrayList<String> genericDetailsInfoListForMars();

    /**
     * Gets the most generic titles for your most generic details
     * 
     * @return a arraylist (consisting of strings) of the most generic titles for
     *         your generic values (in order!)
     */
    ArrayList<String> genericTitleDetailsInfoListForMars();

}
