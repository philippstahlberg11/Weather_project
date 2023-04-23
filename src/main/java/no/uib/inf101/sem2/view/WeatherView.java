package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.sem2.modell.MarsModell;
import no.uib.inf101.sem2.modell.WeatherModell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class WeatherView extends JPanel {

  public ArrayList<Object> temperatureString;
  public String detailsInfo;
  private MarsModell marsModell;
  private HashMap<String, BufferedImage> images;

  public WeatherView(WeatherModell viewWeatherModell, MarsModell marsModell) {

    this.marsModell = marsModell;
    this.temperatureString = viewWeatherModell.getMultipleTimeDetails(viewWeatherModell.genericDetailsInfoList(), 2);
    this.detailsInfo = viewWeatherModell.getCurrentWeatherString(0, 2);
    this.images = viewWeatherModell.testImageIcon();

    this.setPreferredSize(new Dimension(250, 350));

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g.drawImage(getImageToCurrentWeatherString(), 0, 0, getSize().width, getSize().height, this);
    drawMainGeneric(g2);

  }

  /**
   * Used to print each string as a newline
   * Copied from:
   * https://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring
   * 
   */
  private void drawString(Graphics g, String text, int x, int y) {
    for (String line : text.split("\n"))
      g.drawString(line, x, y += g.getFontMetrics().getHeight());

  }

  /**
   * Sees if our current weather is f.example sunny, then showing us a sunny
   * background
   * 
   * @return a BufferedImage, chosen from what weather it currently is
   */
  private BufferedImage getImageToCurrentWeatherString() {

    if (detailsInfo.contains("day")) {
      return images.get("day");
    } else if (detailsInfo.contains("night")) {
      return images.get("night");
    } else if (detailsInfo.contains("cloudy")) {
      return images.get("cloudy");
    } else if (detailsInfo.contains("rain")) {
      return images.get("rain");
    } else if (detailsInfo.contains("fog")) {
      return images.get("fog");
    } else {
      return images.get("day");
    }

  }

  /**
   * Gets us the "feels like" temperature, from a specific formula
   * 
   * @param temperature a string, actually representing a possible double
   *                    variable, which is the current temperature
   * @param vindSpeed   a stirng, actually represetnign a possible double
   *                    varbiale, which is the current windspeed
   * @return the "feels like" temperature
   */
  private int getFeelsLkeTemperature(String temperature, String vindSpeed) {

    if (temperature.contains("celsius")) {
      temperature = temperature.replaceAll("celsius", "");
      temperature = temperature.replaceAll(" ", "");
    }
    if (vindSpeed.contains("m/s")) {
      vindSpeed = vindSpeed.replaceAll("m/s", "");
      vindSpeed = vindSpeed.replaceAll(" ", "");
    }
    Double temperatureInt = Double.parseDouble(temperature);
    Double vindspeedInt = Double.parseDouble(vindSpeed);

    // formula from this site:
    // https://hjelp.yr.no/hc/no/articles/360001695513-Effektiv-temperatur-og-f%C3%B8les-som-
    Double W = 13.12 + 0.0615 * temperatureInt - 11.36 * Math.pow(vindspeedInt, 0.16)
        + 0.3965 * temperatureInt * Math.pow(vindspeedInt, 0.16);

    return W.intValue();
  }

  /**
   * Shows the most generall information, current weather forecast, mars forecast,
   * and background picture
   * 
   * @param graphics2d takes in a graphics2D parameter, to show the view in it
   */
  private void drawMainGeneric(Graphics2D graphics2d) {

    for (int i = 0; i < this.temperatureString.size(); i++) {
      graphics2d.setColor(Color.WHITE);
      graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
      int valueX = this.getWidth() / 4 + (i * 110);
      if (i == 0) {
        valueX -= 80;
      }
      drawString(graphics2d, this.temperatureString.get(i) + " ", valueX, this.getHeight() / 3);
    }
    graphics2d.setFont(new Font("Arial", Font.ITALIC, 25));
    drawString(graphics2d, "Current Weather ", (this.getWidth() / 4 + (-80)), this.getHeight() / 4);

    // Calculate the feels like temperature:
    graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
    drawString(
        graphics2d, "Feels like: "
            + getFeelsLkeTemperature(temperatureString.get(0) + " ", temperatureString.get(3) + " ") + " celsius",
        this.getWidth() / 4 - 80, this.getHeight() / 3 + 30);

    for (int i = 0; i < marsModell.genericDetailsInfoListForMars().size(); i++) {
      graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
      int valueX = this.getWidth() / 4 + (i * 180);
      if (i == 0) {
        valueX -= 200;
      }
      if (i == 1) {
        valueX -= 100;
      }

      Rectangle2D rect6 = new Rectangle2D.Double(valueX - 300, this.getHeight() / 2, this.getWidth() / 2,
          this.getHeight() / 2);
      graphics2d.setColor(new Color(204, 0, 34));
      graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
      Inf101Graphics.drawCenteredString(graphics2d,
          marsModell.genericTitleDetailsInfoListForMars().get(i) + ": "
              + marsModell.getTimeDetailsGenerallStringForMars(marsModell.genericDetailsInfoListForMars().get(i)),
          rect6);

    }
    graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
    drawString(graphics2d, "Weather forecast for the planet Mars:", this.getWidth() / 4 - 250,
        this.getHeight() / 2 + 50);

    graphics2d.setColor(Color.WHITE);
    graphics2d.setFont(new Font("Arial", Font.ITALIC, 25));
    drawString(graphics2d, "Day to day and hour to hour tables below!", 50, this.getHeight() - 50);

  }

}
