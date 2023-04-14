package no.uib.inf101.sem2.modell;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.validation.ValidatorHandler;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.Inf101Graphics;
import no.uib.inf101.sem2.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel {
    public TetrisBoard tetrisBoard;
    public WeatherModell modellWeather;

    /**
     * 
     * @param tetrisBoard
     * @param tetrominoFactory
     */
    public TetrisModel(TetrisBoard tetrisBoard, WeatherModell modellWeather) {
        this.tetrisBoard = tetrisBoard;
        this.modellWeather = modellWeather;

    }

    @Override
    public GridDimension getDimension() {
        return tetrisBoard;
    }

    @Override
    public Iterable<GridCell<String>> getTilesOnBoard() {
        return tetrisBoard;

    }

    public HashMap<String, BufferedImage> IconToPicture() {
            BufferedImage sunny_test = Inf101Graphics.loadImageFromResources("/sunny_sun.png");
            BufferedImage partlyClodyNight = Inf101Graphics.loadImageFromResources("/PartlyCloudy.Night.png");
            BufferedImage partlyCloudyDay = Inf101Graphics.loadImageFromResources("/PartlyCloudy_day.png");
            BufferedImage Cloudy = Inf101Graphics.loadImageFromResources("/Cloudy.png");
            BufferedImage light_rain = Inf101Graphics.loadImageFromResources("/light_rain.png");
            BufferedImage Rainy = Inf101Graphics.loadImageFromResources("/rainy.png");



            HashMap<String, BufferedImage> imageMap = new HashMap<>();
            imageMap.put("partlycloudy_night",partlyClodyNight);
            imageMap.put("cloudy",Cloudy);
            imageMap.put("partlycloudy_day",partlyCloudyDay);
            imageMap.put("lightrain", light_rain);
            imageMap.put("rain",Rainy);
            imageMap.put("-", sunny_test);


            return imageMap;
        
    }

    @Override
    public Boolean checkIfIcon(String value) {
       
        if(value.contains("night") || value.contains("day") || value.contains("cloudy") || value.contains("rain")){
            return true;
        }
        else{
            return false;
        }
    }

  

}
