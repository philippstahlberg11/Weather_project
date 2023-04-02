package no.uib.inf101.sem2.modell;

import java.util.ArrayList;

public interface IWeatherModell {
    

    String getTimeDetails(int time, String detailString);

    Object getDetailsUnit(String detailString);


    ArrayList<Object> getMultipleTimeDetails(ArrayList<String> detailsUnits, int time);


    ArrayList<String> genericDetailsInfoList();

    ArrayList<Object> getNextHoursDetails(int timeLimit);




}
