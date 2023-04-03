package no.uib.inf101.sem2.grid;

import java.util.List;

/**
 * Objects in a class extending CellColorCollection can assemble a list
 * of CellColor objects through the getCells() method.
 */
public interface CellColorCollection {

  /**
   * Get a list containing the GridCell objects in this collection
   *
   * @return a list of all GridCell objects in this collection
   */
  List<CellColor> getCells();

}
