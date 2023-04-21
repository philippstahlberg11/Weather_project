package no.uib.inf101.sem2.modell;

import java.util.ArrayList;
import no.uib.inf101.sem2.grid.CellPosition;

public class showDayGrid extends TetrisModel implements iShowGrid {

    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;
    private ArrayList<String> someList = new ArrayList<>();

    public showDayGrid(TetrisBoard tetrisBoard, WeatherModell modellWeather) {
        super(tetrisBoard, modellWeather);
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;

    }

    public void showGridFirst(int initialValue) {

        // we never actually change the initalValue! (since we dont want to show
        // next/previous page) - to much data to show...

        for (int row = 1; row < tetrisBoard.rows(); row++) {
            someList.add((modellWeather.getUniqueValuesOnlyOfArrayString(
                    modellWeather.getNextHoursDates(tetrisBoard.rows() + (initialValue)))).get(row - 1));
            tetrisBoard.set(new CellPosition(row, 0), someList.get(row - 1));

            // Sets the current icon for the date today, but also (around midday) icon for
            // the next day
            tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(1, 0 + (row * initialValue)));

            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols() - 2),
                    modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols() - 2));
            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols() - 1),
                    modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols() - 1));
            for (int col = 2; col < tetrisBoard.cols(); col++) {
                tetrisBoard.set(new CellPosition(0, col - 2), modellWeather.genericTitleDetailsInfoList().get(col - 2));
                // instead of time : (date)
                tetrisBoard.set(new CellPosition(0, 0), "Date");

                tetrisBoard.set(new CellPosition(row, col),
                        Double.toString(
                                modellWeather
                                        .getAverageOfArray(modellWeather
                                                .getListOfMultipleTimeDetails((initialValue),
                                                        modellWeather.genericDetailsInfoList().get(col - 2))
                                                .get(row - 1)))
                                + " "
                                + modellWeather.getDetailsUnit(modellWeather.genericDetailsInfoList().get(col - 2)));

            }
        }

    }

    @Override
    public void showNextPage() {
        // nothing
    }

    @Override
    public void showPreviousPage() {
        // nothing
    }

}
