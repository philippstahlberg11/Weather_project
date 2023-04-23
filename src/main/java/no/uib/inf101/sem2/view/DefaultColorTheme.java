package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.util.ArrayList;

public class DefaultColorTheme implements ColorTheme {

   
    @Override
    public Color getFrameColor() {
        return (Color.LIGHT_GRAY);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.WHITE;
    }

    @Override
    public ArrayList<Color> getRowsDefaultColors() {
        ArrayList<Color> newColor = new ArrayList<>();

        // time color
        newColor.add(Color.BLACK);
        // weater color (icons)
        newColor.add(Color.BLACK);
        // temperature color
        newColor.add(Color.RED);
        // uv color
        newColor.add(Color.BLACK);
        // humidity color
        newColor.add(Color.BLACK);
        // wind speed color
        newColor.add(Color.BLUE);
        // air pressure color
        newColor.add(Color.BLACK);
        // wind direction color
        newColor.add(Color.BLACK);
        // in case if one wants to have more rows,
        // caution: we recommend to not have much more than 12 rows,
        // certain information might be very uncertain when going forwards 
        for(int i = 0; i < 10; i++){
            newColor.add(Color.BLACK);
        }

        return newColor;


    }

   

}
