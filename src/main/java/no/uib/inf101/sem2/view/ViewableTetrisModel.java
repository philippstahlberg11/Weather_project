package no.uib.inf101.sem2.view;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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



   public BufferedImage IconToPicture(String value);



   public Boolean checkIfIcon(String value);







}   
