package no.uib.inf101.sem2.modell;

import java.util.ArrayList;
import java.util.List;

public interface IWeatherModell {

    /**
     * Shows us what the value of any detail has, and it's corresponding unit
     * 
     * @param time         from what time, current time = 0.
     * @param detailString the string that tells what information you want to get.
     * @return the specific detail information and it's corresponding unit.
     */
    String getTimeDetails(int time, String detailString);

    /**
     * Get the most generic units
     * 
     * @param detailString a string which represents for which value you want a unit
     *                     for
     * @return with the corresponding unit to the detailString
     */
    Object getDetailsUnit(String detailString);

    /**
     * Gets a arraylist with multiple values and their corresponding units
     * 
     * @param detailsUnits an arraylist with all the values you want to get
     * @param time         an int representing at what time
     * @return an arraylist with multiple
     */
    ArrayList<Object> getMultipleTimeDetails(ArrayList<String> detailsUnits, int time);

    /**
     * An arraylist with strings of the most generic information one would want
     * 
     * @return a array with strings representing different values like temperature
     *         etc.
     */
    ArrayList<String> genericDetailsInfoList();

    /**
     * An arraylist with string of the most generic information titles one would
     * want
     * 
     * @return a array with string representing different titles for values, like
     *         "Time" etc.
     */
    public ArrayList<String> genericTitleDetailsInfoList();

    /**
     * Gets all the information about possible, for the specified timeLimit
     * 
     * @param timeLimit an int representing how many hours you wish to go forwards
     * @return an arraylist with all the different information in the next
     *         hours/hour (length is specified after timeLimit)
     */
    ArrayList<String> getNextHoursDetails(int timeLimit);

    /**
     * Converts from a generic time-string to the hours
     * 
     * @param time an int representing in this format: YY-MM-DDTHH:MM:SSZ
     * @return hours
     */
    String convertTimeToHours(String time);

    /**
     * Gets the specific next time "summary"-information, meaning "sunny" etc.
     * 
     * @see At 12 hours the certainty might change, beware!
     * @param timeNext shows the time "summary" either .0 - 1 - 2. (0-5hours,
     *                 6-11hours, 12+ hours)
     * @param time     the specific hours
     * @return a string associatied with the times weather
     */
    public String iconString(int timeNext, int time);

    /**
     * Gets the details of any value, like temperature
     * 
     * @param time         an int representing at which time it gets the
     *                     corresponding value
     * @param detailString an string, which value one want's to get, example:
     *                     air_temperature
     * @return the value of the detailString, at the current time
     */
    public String getTimeDetailsGenerallString(int time, String detailString);

    /**
     * Converts the time to the date
     * 
     * @param time a string representing the date in this format: YY-MM-DDTHH:MM:SSZ
     * @return the date
     */
    public String convertTimeToDate(String time);

    /**
     * Gets the avarage value of an array
     * 
     * @param arrayList an array of string values, that can be double values
     * @return a double values representing the average value of the array
     */
    public double getAverageOfArray(List<String> arrayList);

    /**
     * Gets only values of a array
     * 
     * @param arrayList an arraylist consisting of integers, that may have
     *                  duplicates
     * @return a arraylist consisting of integers, that are only of unique values
     */
    public ArrayList<Integer> getUniqueValuesOnlyOfArray(ArrayList<Integer> arrayList);

    /**
     * Gets the details of any value in multiple hours
     * 
     * @param timelimit    a int, showing a restricting, how many hours forward
     * @param detailString a string, what detail-value you want to get
     * @return a 2D-arraylist, that shows all the values of one day in the first
     *         place, etc.
     */
    public List<List<String>> getListOfMultipleTimeDetails(int timelimit, String detailString);

    /**
     * Gets the unique values of an array and makes this into a new arraylist
     * 
     * @param arrayList an arraylist consisting of string values
     * @return Returns a list of only unique values found in a array of string
     *         elements
     */
    public List<String> getUniqueValuesOnlyOfArrayString(List<String> arrayList);

    /**
     * Gets the date for the specified timeLimit
     * 
     * @param timeLimit a integer representing the maximum time forwards we need to
     *                  get the dates
     * @return a arraylist consisting of strings, which show the dates
     */
    ArrayList<String> getNextHoursDates(int timeLimit);
}
