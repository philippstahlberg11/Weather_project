package no.uib.inf101.sem2.grid;

/**
 * This class represents a position on a grid.
 * That means indices for row and column.
 * <p>
 * CellPosition is Immutable, this means the only way to make a new
 * CellPosition is to call the constructor.
 */
public record CellPosition(int row, int col) {	}
