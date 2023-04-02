package no.uib.inf101.sem2.grid;

public interface GridDimension {

  /** Number of rows in the grid */
  int rows();

  /** Number of columns in the grid */
  int cols();
    /**
   * Makes it possible to iterate over all coordinates of this grid
   * Iteration happens row-wise i.e. First row 0, then row 1 and so on.
   */
  default boolean isOnGrid(CellPosition pos) {
    return pos.row() >= 0 && pos.row() < this.rows()
        && pos.col() >= 0 && pos.col() < this.cols();
  }
}