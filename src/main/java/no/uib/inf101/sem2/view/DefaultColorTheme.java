package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.util.ArrayList;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(Character c) {

    // sees if the characer given matches any of these cases if not; then we could create one.
    Color color = switch(c) {
        case 'r' -> Color.RED;
        case 'g' -> Color.GREEN;
        case 'b' -> Color.BLUE;
        case 'y' -> Color.YELLOW;
        // ENDRE PÅ DISSE VERDIENE SENERE
        case 'L' -> Color.BLUE;
        case 'I' -> Color.YELLOW;
        case 'Z' -> Color.RED;
        case 'T' -> Color.PINK;
        case 'S' -> Color.CYAN;
        case 'O' -> Color.ORANGE;
        case 'J' -> Color.MAGENTA;
        // default value if no color value given:
        case '-' -> Color.GRAY;
        default -> throw new IllegalArgumentException(
            "No available color for '" + c + "'");
        };
    return color;
    }

    @Override
    public Color getFrameColor() {
        return (Color.LIGHT_GRAY);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getTranspoarentColor() {

        return new Color(0,0,0,128);
    }

    @Override
    public ArrayList<Color> getRowsDefaultColors() {
        // the different colors for our rows in the grid! (change method name after that too!)
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

        return newColor;


    }

   

}
