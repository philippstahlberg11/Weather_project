package no.uib.inf101.sem2.modell;

import no.uib.inf101.sem2.grid.CellPosition;

public class showTimeGrid extends TableModel implements iShowGrid {

    private Table tetrisBoard;
    private WeatherModell modellWeather;
    private int value;

    public showTimeGrid(Table tetrisBoard, WeatherModell modellWeather) {
        super(tetrisBoard, modellWeather);
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;
        this.value = 0;

    }

    @Override
    public void showGridFirst(int initialValue) {

        this.value += initialValue;

        for (int row = 1; row < tetrisBoard.rows(); row++) {
            tetrisBoard.set(new CellPosition(row, 0),
                    modellWeather.convertTimeToHours(modellWeather
                            .getNextHoursDetails(tetrisBoard.rows() + (this.value)).get(row - 1 + (this.value))));

            getWeatherString(row, 1);

            setSpecificValues();

            for (int col = 2; col < tetrisBoard.cols(); col++) {
                tetrisBoard.set(new CellPosition(0, col - 2), modellWeather.genericTitleDetailsInfoList().get(col - 2));
                tetrisBoard.set(new CellPosition(row, col),
                        modellWeather.getTimeDetails(row - 1 + (this.value),
                                modellWeather.genericDetailsInfoList().get(col - 2)));

            }
        }

    }

    private void getWeatherString(int row, int col) {
        if (row >= 6) {
            tetrisBoard.set(new CellPosition(row, col), modellWeather.getCurrentWeatherString(1, 0 + (this.value)));
        } else if (row >= 12) {
            tetrisBoard.set(new CellPosition(row, col), modellWeather.getCurrentWeatherString(2, 0 + (this.value)));
        } else {
            tetrisBoard.set(new CellPosition(row, col), modellWeather.getCurrentWeatherString(0, 0 + (this.value)));
        }

    }

    private void setSpecificValues() {
        tetrisBoard.set(new CellPosition(0, tetrisBoard.cols() - 2),
                modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols() - 2));
        tetrisBoard.set(new CellPosition(0, tetrisBoard.cols() - 1),
                modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols() - 1));
    }

    @Override
    public void showNextPage() {

        showGridFirst(tetrisBoard.rows() - 1);
    }

    @Override
    public void showPreviousPage() {
        showGridFirst(-tetrisBoard.rows() + 1);
    }

}
