package no.uib.inf101.sem2.view;
import java.awt.Color;
import java.util.ArrayList;


public interface ColorTheme {
    
    /**
     * Gets the color from a given color Character
     * @param c character that represents a color
     * @return Color
     */
    Color getCellColor(Character c);

    /**
     * Gets the frame color, this is some default value
     */
    Color getFrameColor();

    /**
     * Gets the background color, this is some default value
     */
    Color getBackgroundColor();

    /**
     * Mostly used for our "Game-over" situation.
     * @return Transparent color
     */
    Color getTranspoarentColor();


    /**
     * Gets the different rows-colors, this is some default value
     * @return
     */
    ArrayList<Color> getRowsDefaultColors();
    
}
