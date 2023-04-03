package no.uib.inf101.sem2.modell;

import java.util.ArrayList;

import no.uib.inf101.sem2.grid.GridDimension;

public interface IWeatherModell {
// add all javadocs!


    /**
     * Shows us what the value of any detail has, and it's corresponding unit
     * @param time from what time, current time = 0.
     * @param detailString the string that tells what information you want to get.
     * @return the specific detail information and it's corresponding unit.
     */
    String getTimeDetails(int time, String detailString);

    Object getDetailsUnit(String detailString);


    ArrayList<Object> getMultipleTimeDetails(ArrayList<String> detailsUnits, int time);


    ArrayList<String> genericDetailsInfoList();

    ArrayList<String> getNextHoursDetails(int timeLimit);

    String convertTimeToHours(String time);

    public String iconString(int timeNext, int time);

}
