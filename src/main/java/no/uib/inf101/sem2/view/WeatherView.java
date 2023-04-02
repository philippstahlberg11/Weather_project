package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
public class WeatherView extends JPanel {


    // we want the information from jsonparser to this class, since we would like to know the time, detailsstirng (without having a par.) and etc.

    public ArrayList<Object> temperatureString;
    public ArrayList<String> detailsInfo;

    // perhaps these parameters should be generalized, since one could have an array of them, or just one object?
    public WeatherView(ArrayList<Object> temperature, ArrayList<String> detailsString){
        this.setPreferredSize(new Dimension(800, 800));
        this.temperatureString = temperature;
        this.detailsInfo = detailsString;

    }
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      
        drawMainGeneric(g2);
    }

    /**
     * Used to print each string as a newline
     */
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());

    }
      // generell informasjon som første ting på siden
      public void drawMainGeneric(Graphics2D graphics2d){

        for(int i = 0; i < this.temperatureString.size(); i++) {
            graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
            drawString(graphics2d, this.temperatureString.get(i) + " ", this.getWidth()/3, 20 + (i*20));
        }
       





      }



}
