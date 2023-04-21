package no.uib.inf101.sem2.modell;

import no.uib.inf101.sem2.grid.Grid;

public class TetrisBoard extends Grid<String> {

    /**
     * A Tetrisboard with given rows and coloumns
     * NOTE: default values are "-" if none given.
     * 
     * @param rows
     * @param columns
     */
    public TetrisBoard(int rows, int columns) {
        super(rows, columns, "");

    }

}
