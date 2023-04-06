package no.uib.inf101.sem2.modell;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.Inf101Graphics;
import no.uib.inf101.sem2.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel {
    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;

    /**
     * 
     * @param tetrisBoard
     * @param tetrominoFactory
     */
    public TetrisModel(TetrisBoard tetrisBoard, WeatherModell modellWeather) {
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;

    }

    @Override
    public GridDimension getDimension() {
        return tetrisBoard;
    }

    @Override
    public Iterable<GridCell<String>> getTilesOnBoard() {
        return tetrisBoard;

    }

  

}
