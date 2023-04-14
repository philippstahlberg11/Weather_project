package no.uib.inf101.sem2.view;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewableTetrisModel {

    
  /**
    * Gets the rows and coloums
    * @return rows and couloums 
    */
    GridDimension getDimension();

 
    /**
     * Gets each of the tiles on the a board
     * @return all the tiles as a Iterable
     */
   Iterable<GridCell<String>> getTilesOnBoard();



   public HashMap<String, BufferedImage> IconToPicture();



   public Boolean checkIfIcon(String value);







}   
