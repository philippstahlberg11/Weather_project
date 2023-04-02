package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * A Grid contains a set of cell states
 *
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 */
public class Grid<E> implements IGrid<E> {
	private final List<E> cells;
	private final int columns;
	private final int rows;
	private final ArrayList<GridCell<E>> cd = new ArrayList<>();

	
	/**
	 * Construct a grid with the given dimensions.
	 * 
	 * @param rows the number of rows
	 * @param columns the number of cols
	 * Where the elements are holding null
	 */
	public Grid(int rows, int columns){
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException();
		}


		this.columns = columns;
		this.rows = rows;

		this.cells = new ArrayList<>(columns * rows);
		for (int i = 0; i < columns * rows; ++i) {
			this.cells.add(null);
	}
	
	}
	/**
	 * Construct a grid with the given dimensions.
	 * 
	 * @param rows the number of rows
	 * @param columns the number of cols
	 * @param initElement What the cells should initially hold (possibly a standard value if not: null)
	 */
	public Grid(int rows, int columns, E initElement) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException();
		}

		this.columns = columns;
		this.rows = rows;
		
		this.cells = new ArrayList<>(columns * rows);
		for (int i = 0; i < columns * rows; ++i) {
			this.cells.add(initElement);
		}
	}	


	@Override
	public int 	rows() {
		return this.rows;
	}



	@Override
	public int cols() {
		return this.columns;
	}



	@Override
	public void set(CellPosition pos, E value) {
		this.checkCoordinate(pos);
		this.cells.set(coordinateToIndex(pos), value);

		
	}	
	/**
	 * This method checks if a given CellPosition is within the bounds of this grid.
	 * If it is not, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param pos the position to check
	 */
	public void checkCoordinate(CellPosition pos) {
		if (!this.positionIsOnGrid(pos)) {
			throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
		}

	}

		/**
	 * This method computes which index in the list belongs to a given Coordinate
	 */
	private int coordinateToIndex(CellPosition pos) {
		return pos.row() + pos.col() * rows;
	}


	@Override
	public E get(CellPosition pos) {	
		this.checkCoordinate(pos);
		return this.cells.get(coordinateToIndex(pos));
	}




	@Override
	public boolean positionIsOnGrid(CellPosition pos) {
		return pos.row() >= 0 && pos.row() < this.rows()
		&& pos.col() >= 0 && pos.col() < this.cols();


	}

	/**
	 * Adding each element to an ArrayList- iterating trough each element in the grid
	 * @return an iterator ArrayList<GridCell<<(any object)>> 
	 * 
	 */

	@Override
	public Iterator<GridCell<E>> iterator() {

	 	for(int i = 0; i < this.rows; i ++){
			for(int y = 0; y < this.columns; y++){
			  this.cd.add(new GridCell<E>(new CellPosition(i, y), get(new CellPosition(i, y))));
			}
		  }
		  return this.cd.iterator();
	}
	
}
	





