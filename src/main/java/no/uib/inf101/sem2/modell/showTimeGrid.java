package no.uib.inf101.sem2.modell;

import no.uib.inf101.sem2.grid.CellPosition;

public class showTimeGrid extends TetrisModel implements iShowGrid {

    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;
    public int value;

    public showTimeGrid(TetrisBoard tetrisBoard, WeatherModell modellWeather) {
        super(tetrisBoard, modellWeather);
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;
        this.value = 0;

    }

    public void showGridFirst(int initialValue) {

        this.value += initialValue;

        for (int row = 1; row < tetrisBoard.rows(); row++) {
            tetrisBoard.set(new CellPosition(row, 0),
                    modellWeather.convertTimeToHours(modellWeather
                            .getNextHoursDetails(tetrisBoard.rows() + (this.value)).get(row - 1 + (this.value))));

            if (row >= 6) {
                tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(1, 0 + (this.value)));
            } else if (row >= 12) {
                tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(2, 0 + (this.value)));
            } else {
                tetrisBoard.set(new CellPosition(row, 1), modellWeather.iconString(0, 0 + (this.value)));
            }

            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols() - 2),
                    modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols() - 2));
            tetrisBoard.set(new CellPosition(0, tetrisBoard.cols() - 1),
                    modellWeather.genericTitleDetailsInfoList().get(tetrisBoard.cols() - 1));

            for (int col = 2; col < tetrisBoard.cols(); col++) {
                tetrisBoard.set(new CellPosition(0, col - 2), modellWeather.genericTitleDetailsInfoList().get(col - 2));
                tetrisBoard.set(new CellPosition(row, col),
                        modellWeather.getTimeDetails(row - 1 + (this.value),
                                modellWeather.genericDetailsInfoList().get(col - 2)));

            }
        }

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
