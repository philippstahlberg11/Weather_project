package no.uib.inf101.sem2.view;

import java.awt.Color;

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
        return new Color(0,0,0,0);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getTranspoarentColor() {

        return new Color(0,0,0,128);
    }

   

}
