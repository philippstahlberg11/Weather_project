package no.uib.inf101.sem2.modell;



import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

import no.uib.inf101.sem2.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel {
    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;

    /**
     * 
     * @param tetrisBoard
     * @param tetrominoFactory
     */
    public TetrisModel(TetrisBoard tetrisBoard, WeatherModell modellWeather){
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
    public void testRowsCols(){
        // set new values for new col
    for(int row = 0; row < tetrisBoard.rows(); row++){
       tetrisBoard.set(new CellPosition(row, 0), "time");
        for(int col = 1; col < tetrisBoard.cols(); col++){
          tetrisBoard.set(new CellPosition(row, col), modellWeather.genericDetailsInfoList().get(col-1));
          
      }
    
    }

    }

}


