package no.uib.inf101.sem2.modell;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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
    public HashMap<String, BufferedImage> IconToPicture() {
        BufferedImage sunny_test = Inf101Graphics.loadImageFromResources("/sunny_sun.png");
        BufferedImage partlyClodyNight = Inf101Graphics.loadImageFromResources("/PartlyCloudy.Night.png");
        BufferedImage partlyCloudyDay = Inf101Graphics.loadImageFromResources("/PartlyCloudy_day.png");
        BufferedImage Cloudy = Inf101Graphics.loadImageFromResources("/Cloudy.png");
        BufferedImage light_rain = Inf101Graphics.loadImageFromResources("/light_rain.png");
        BufferedImage Rainy = Inf101Graphics.loadImageFromResources("/rainy.png");
        BufferedImage Fair_night = Inf101Graphics.loadImageFromResources("/fair_night.png");
        BufferedImage clear_sky_night = Inf101Graphics.loadImageFromResources("/clearsky_night.png");
        BufferedImage fair_day = Inf101Graphics.loadImageFromResources("/fair_day.png");
        BufferedImage clearsky_day = Inf101Graphics.loadImageFromResources("/clearsky_day.png");
        BufferedImage fog_day = Inf101Graphics.loadImageFromResources("/fog.png");

        HashMap<String, BufferedImage> imageMap = new HashMap<>();
        imageMap.put("partlycloudy_night", partlyClodyNight);
        imageMap.put("fog", fog_day);
        imageMap.put("cloudy", Cloudy);
        imageMap.put("partlycloudy_day", partlyCloudyDay);
        imageMap.put("lightrain", light_rain);
        imageMap.put("rain", Rainy);
        imageMap.put("fair_night", Fair_night);
        imageMap.put("clearsky_night", clear_sky_night);
        imageMap.put("fair_day", fair_day);
        imageMap.put("clearsky_day", clearsky_day);
        imageMap.put("-", sunny_test);

        return imageMap;

    }

    @Override
    public Boolean checkIfIcon(String value) {
        if (value.contains("night") || value.contains("day") || value.contains("cloudy") || value.contains("rain")
                || value.contains("fog")) {
            return true;
        } else {
            return false;
        }
    }

}
