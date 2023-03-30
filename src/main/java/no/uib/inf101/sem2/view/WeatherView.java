package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
public class WeatherView extends JPanel {
    // we want to firstly show the text of tempereature simply to the screen:)

    public Object temperatureString;

    public WeatherView(Object temperature){
        this.setPreferredSize(new Dimension(500, 500));
        this.temperatureString = temperature;

    }
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      
      // Draw a centered rectangle with text
      Rectangle2D rect =  new Rectangle2D.Double(50, 50, getWidth() - 100, getHeight() - 100);
      Color color = Color.BLACK;
      g2.setColor(color);
      g2.draw(rect);
      Inf101Graphics.drawCenteredString(g2, "Temperature " + this.temperatureString , rect);
    
    }



}
