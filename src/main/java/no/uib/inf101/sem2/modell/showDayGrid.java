package no.uib.inf101.sem2.modell;

import javax.swing.event.SwingPropertyChangeSupport;

import no.uib.inf101.sem2.grid.CellPosition;

public class showDayGrid extends TetrisModel implements iShowGrid {

    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;
    private int value;

    public showDayGrid(TetrisBoard tetrisBoard, WeatherModell modellWeather) {
        super(tetrisBoard, modellWeather);
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;
        this.value = 0;

    }
     
    // vi bør heller ha det slik at showGridFirst(har en par.) som er initialvalue = 0, senere kan vi gjør at den blir lik tetrisBoard.rows() (altså at den går til neste side)
    // TODO: liten bug at vi "skipper" gjennom 2 timer, for hver side vi går videre med!
    
    public void showGridFirst(int initialValue) {
        // this will be our grid system (change name for it)
        this.value += initialValue;
        // set new values for new col
        for (int row = 1; row < tetrisBoard.rows(); row++) {
            tetrisBoard.set(new CellPosition(row, 0),
                    modellWeather.convertTimeToDate(modellWeather.getNextHoursDetails(tetrisBoard.rows() + (this.value)).get(row-1 + (this.value))));
                    this.value++;
            // vi har en forskjell mellom de første timene (til og med 5 timer), også må 6
            // timer være noe annet (vi tar ikke hensyn til det som skjer mellom 2-5 timer eller 7-11 timer (siden vi ikke har nok data...),
            // viser feil her: ikke riktig etter row og slikt!
            if (row >= 6) {
                tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(1, 0 + (this.value)));
            }
            else if(row >= 12){
                tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(2, 0 + (this.value)));
            } else {
                tetrisBoard.set(new CellPosition(row, 1),  modellWeather.iconString(0, 0 + (this.value)));
            }
            // might change how this might work later? but since we start at col = 2, we never go trough the for loop tetrisBoard.cols()     but rather to 
            // tetrisBoard.cols() - 2, meaning we are missing to elements:
            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols()-2), modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols()-2));
            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols()-1), modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols()-1));


            for (int col = 2; col < tetrisBoard.cols(); col++) {
                tetrisBoard.set(new CellPosition(0, col-2), modellWeather.genericTitleDetailsInfoList().get(col-2));
                tetrisBoard.set(new CellPosition(row, col),
                Double.toString(modellWeather.getAverageOfArray(modellWeather.getListOfMultipleTimeDetails(5, modellWeather.genericDetailsInfoList().get(col-2)).get(0))) + ""+ modellWeather.getDetailsUnit(modellWeather.genericDetailsInfoList().get(col-2)));

            }
        }

    }
    // vi bør heller kanskje ha en knapp her, som etterhvert også stopper slik at vi ikke kan misbruke dette videre! ....
    public void showNextPage(){
        // show the next page by adding the values to the grid respectivly!
          // set new values for new col
        showGridFirst(tetrisBoard.rows()-1);
    }

    // TODO: check if both next page and previous page shows the correct information
    public void showPreviousPage(){
        // show the prevoius pages by subtracting the values from the grid resprectivly!
          // set new values for new col
          showGridFirst(-tetrisBoard.rows()+1);
    }
 

    
}
