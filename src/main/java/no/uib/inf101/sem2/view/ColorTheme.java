package no.uib.inf101.sem2.view;
import java.awt.Color;
import java.util.ArrayList;


public interface ColorTheme {
    
    /**
     * Gets the frame color, this is some default value
     */
    Color getFrameColor();

    /**
     * Gets the background color, this is some default value
     */
    Color getBackgroundColor();
    /**
     * Gets the different rows-colors, this is some default value
     * @return an arraylist consisting of colors
     */
    ArrayList<Color> getRowsDefaultColors();
    
}
