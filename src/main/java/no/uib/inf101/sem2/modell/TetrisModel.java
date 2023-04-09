package no.uib.inf101.sem2.modell;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    @Override
    public BufferedImage IconToPicture(String value) {
            BufferedImage sunny_test = Inf101Graphics.loadImageFromResources("/sunny_sun.png");
            BufferedImage partlyClodyNight = Inf101Graphics.loadImageFromResources("/PartlyCloudy.Night.png");
            BufferedImage partlyCloudyDay = Inf101Graphics.loadImageFromResources("/PartlyCloudy_day.png");
            BufferedImage Cloudy = Inf101Graphics.loadImageFromResources("/Cloudy.png");
            BufferedImage Rainy = Inf101Graphics.loadImageFromResources("/rainy.png");
            BufferedImage light_rain = Inf101Graphics.loadImageFromResources("/light_rain.png");




    
            if(value.equals("partlycloudy_night")){
                    return partlyClodyNight;
            }
            else if(value.equals("cloudy")){
                return Cloudy;
            }
            else if(value.equals("partlycloudy_day")){
                return partlyCloudyDay;
            }
            else if(value.equals("lightrain")){
                return light_rain;
            }
            else if(value.equals("rain")){
                return Rainy;
            }
            return sunny_test;
        
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
