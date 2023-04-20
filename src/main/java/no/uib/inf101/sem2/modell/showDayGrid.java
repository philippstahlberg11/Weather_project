package no.uib.inf101.sem2.modell;

import java.util.ArrayList;

import javax.swing.event.SwingPropertyChangeSupport;

import no.uib.inf101.sem2.grid.CellPosition;

public class showDayGrid extends TetrisModel implements iShowGrid {

    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;
    private int value;
    private ArrayList <String> someList = new ArrayList<>();

    public showDayGrid(TetrisBoard tetrisBoard, WeatherModell modellWeather) {
        super(tetrisBoard, modellWeather);
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;
        this.value = 0;

    }
     
    // vi bør heller ha det slik at showGridFirst(har en par.) som er initialvalue = 0, senere kan vi gjør at den blir lik tetrisBoard.rows() (altså at den går til neste side)
    // TODO: liten bug at vi "skipper" gjennom 2 timer, for hver side vi går videre med!
    
    public void showGridFirst(int initialValue) {


        // set new values for new col
        for (int row = 1; row < tetrisBoard.rows(); row++) {
            someList.add((modellWeather.getUniqueValuesOnlyOfArrayString(modellWeather.getNextHoursDetailsUnique(tetrisBoard.rows() + (initialValue)))).get(row-1));
            tetrisBoard.set(new CellPosition(row, 0), someList.get(row-1));
                    
            // Sets the current icon for the date today, but also (around midday) icon for the next day 
            tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(1, 0 + (row*initialValue)));
        

            // might change how this might work later? but since we start at col = 2, we never go trough the for loop tetrisBoard.cols()     but rather to 
            // tetrisBoard.cols() - 2, meaning we are missing to elements:
            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols()-2), modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols()-2));
            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols()-1), modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols()-1));
            for (int col = 2; col < tetrisBoard.cols(); col++) {
                tetrisBoard.set(new CellPosition(0, col-2), modellWeather.genericTitleDetailsInfoList().get(col-2));
                tetrisBoard.set(new CellPosition(0, 0), "Date");
                //System.out.println(modellWeather.getAverageOfArray(modellWeather.getListOfMultipleTimeDetails(tetrisBoard.rows()-1 + (this.value), modellWeather.genericDetailsInfoList().get(col-2)).get(0)));
                tetrisBoard.set(new CellPosition(row, col),
                Double.toString(modellWeather.getAverageOfArray(modellWeather.getListOfMultipleTimeDetails(( initialValue), modellWeather.genericDetailsInfoList().get(col-2)).get(row-1))) + " "+ modellWeather.getDetailsUnit(modellWeather.genericDetailsInfoList().get(col-2)));

            }
        }

    }

    @Override
    public void showNextPage() {
        //nothing
    }

    @Override
    public void showPreviousPage() {
        //nothing
    }

 

    
}
