package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.sem2.modell.MarsModell;
import no.uib.inf101.sem2.modell.WeatherModell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class WeatherView extends JPanel {


    // we want the information from jsonparser to this class, since we would like to know the time, detailsstirng (without having a par.) and etc.

    public ArrayList<Object> temperatureString;
    public String detailsInfo;
    private MarsModell marsModell;

    // perhaps these parameters should be generalized, since one could have an array of them, or just one object?
    public WeatherView(WeatherModell viewWeatherModell, MarsModell marsModell){

        this.marsModell = marsModell;
        this.setPreferredSize(new Dimension(250, 350));
        this.temperatureString = viewWeatherModell.getMultipleTimeDetails(viewWeatherModell.genericDetailsInfoList(), 2);
        this.detailsInfo = viewWeatherModell.iconString(0, 2);

    }
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      

    /*   int w = getWidth();
      int h = getHeight();


          // I stedet for å bruke setColor, kan vi bruke setPaint
        // for å sette fargen til en GradientPaint
        Color color1 = Color.BLUE.brighter();
        Color color2 = Color.WHITE;
        graphics2d.setPaint(new GradientPaint(0, 0, color1, h, h, color2));
        graphics2d.fillRect(0, 0, w, h); */
        g.drawImage(testImageIcon(),0,0,getSize().width,getSize().height,this);
        drawMainGeneric(g2);
        
      
    }
 
      
  
    

    /**
     * Used to print each string as a newline
     */
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());

    }


    public BufferedImage testImageIcon(){


      BufferedImage day = Inf101Graphics.loadImageFromResources("/day_image.jpg");
      BufferedImage night = Inf101Graphics.loadImageFromResources("/night_image.jpg");
      BufferedImage cloudy = Inf101Graphics.loadImageFromResources("/fog.png");
      BufferedImage rain = Inf101Graphics.loadImageFromResources("/fog.png");
      BufferedImage fog = Inf101Graphics.loadImageFromResources("/fog.png");
      BufferedImage elseWeather = Inf101Graphics.loadImageFromResources("/fog.png");



      //        if(value.contains("night") || value.contains("day") || value.contains("cloudy") || value.contains("rain") || value.contains("fog")){

      if(detailsInfo.contains("day")){
        return day;
      }
      else if(detailsInfo.contains("night")){
        return night;
      } 
      else if(detailsInfo.contains("cloudy")){

      }
      else if(detailsInfo.contains("rain")){

      }
      else if(detailsInfo.contains("fog")){

      }
      else{
        return day;
      }
      return day;



    }

    public int getFeelsLkeTemperature(String temperature, String vindSpeed){

      if(temperature.contains("celsius")){
        temperature = temperature.replaceAll("celsius", "");
        temperature = temperature.replaceAll(" ", "");
      }
      if(vindSpeed.contains("m/s")){
        vindSpeed = vindSpeed.replaceAll("m/s", "");
        vindSpeed = vindSpeed.replaceAll(" ", "");
      }
      Double temperatureInt = Double.parseDouble(temperature);
      Double vindspeedInt = Double.parseDouble(vindSpeed);

      // formula from this site: https://hjelp.yr.no/hc/no/articles/360001695513-Effektiv-temperatur-og-f%C3%B8les-som-
      Double W = 13.12 + 0.0615*temperatureInt - 11.36* Math.pow(vindspeedInt, 0.16) + 0.3965*temperatureInt * Math.pow(vindspeedInt, 0.16);


      return W.intValue();
    }
      // General information on the front-page, things like current temperatures and so on!
      // TODO: might also have a tip of the day, we could see at the percentages of rain/thunder in the next couple days!

      public void drawMainGeneric(Graphics2D graphics2d){

    
     

        for(int i = 0; i < this.temperatureString.size(); i++) {
            graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
            int valueX = this.getWidth()/4 + (i*110);
            if(i == 0){
              valueX -= 80;
            }
            drawString(graphics2d, this.temperatureString.get(i) + " ", valueX, this.getHeight()/3);
        }

   
  /*       
        // icon, example: fair_day - picture
        drawString(graphics2d, detailsInfo, this.getWidth()/10, this.getHeight()/6);
        // should rather just be the whole background!
 */




        // Calculate the feels like temperature:
        graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
        drawString(graphics2d, "Feels like: " + getFeelsLkeTemperature(temperatureString.get(0) + " ", temperatureString.get(3) + " ") + " celsius", this.getWidth()/4 -80, this.getHeight()/3+30);

  
     

        for(int i = 0; i < marsModell.genericDetailsInfoListForMars().size(); i++){
          graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
          int valueX = this.getWidth()/4 + (i*180);
          if(i == 0){
            valueX -= 200;
          }
          if(i == 1){
            valueX -= 100;
          }
          
          Rectangle2D rect6 = new Rectangle2D.Double(valueX-300,this.getHeight()/2, this.getWidth()/2, this.getHeight()/2);
          graphics2d.setColor(Color.WHITE);
          graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
          Inf101Graphics.drawCenteredString(graphics2d, marsModell.genericTitleDetailsInfoListForMars().get(i) + ": " + marsModell.getTimeDetailsGenerallStringForMars(marsModell.genericDetailsInfoListForMars().get(i)), rect6);

          //drawString(graphics2d, marsModell.genericTitleDetailsInfoListForMars().get(i) + ": " + marsModell.getTimeDetailsGenerallStringForMars(marsModell.genericDetailsInfoListForMars().get(i)), valueX, this.getHeight()/2);
        }
        graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
        drawString(graphics2d, "Weather forecast for the planet mars:", this.getWidth()/4-250, this.getHeight()/2+50);  

      }



}
