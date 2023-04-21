package no.uib.inf101.sem2.view;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewableTetrisModel {

  /**
   * Gets the rows and coloums
   * 
   * @return rows and couloums
   */
  GridDimension getDimension();

  /**
   * Gets each of the tiles on the a board
   * 
   * @return all the tiles as a Iterable
   */
  Iterable<GridCell<String>> getTilesOnBoard();

  /**
   * Initialize the images, with the corresponding values
   * 
   * @return an image, representing the correct value (i.e. sunny - sunny_picture
   *         etc.)
   */
  public HashMap<String, BufferedImage> IconToPicture();

  /**
   * Check if we have an image-icon
   * 
   * @param value a string, representing a value that might include a description
   *              of the weather
   * @return true if we are working with a description of weather -string, false
   *         if not
   */
  public Boolean checkIfIcon(String value);

}
