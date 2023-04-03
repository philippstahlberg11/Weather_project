package no.uib.inf101.sem2.grid;

// Les om records her: https://inf101.ii.uib.no/notat/mutabilitet/#record

import java.awt.Color;

/**
 * A CellColor contains a CellPosition and a Color.
 *
 * @param cellPosition  the position of the cell
 * @param color        the color of the cell
 */
public record CellColor(CellPosition cellPosition, Color color) {
    
}
