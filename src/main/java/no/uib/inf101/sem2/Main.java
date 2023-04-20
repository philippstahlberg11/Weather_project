package no.uib.inf101.sem2;

import no.uib.inf101.sem2.view.Inf101Graphics;
import no.uib.inf101.sem2.view.SampleView;
import no.uib.inf101.sem2.view.TetrisView;
import no.uib.inf101.sem2.view.WeatherView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.modelmbean.ModelMBean;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONValue;
//import org.json.parser.JSONParser;

import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.grid.IGrid;
import no.uib.inf101.sem2.modell.WeatherModell;
import no.uib.inf101.sem2.modell.showDayGrid;
import no.uib.inf101.sem2.modell.showTimeGrid;
import no.uib.inf101.sem2.modell.JsonObject;
import no.uib.inf101.sem2.modell.MarsModell;
import no.uib.inf101.sem2.modell.TetrisBoard;
import no.uib.inf101.sem2.modell.TetrisModel;
import no.uib.inf101.sem2.controller.WeatherController;
import no.uib.inf101.sem2.grid.CellPosition;

public class Main {
  
  private static JPanel containerPane;
  private static JPanel bottomPane;

  protected static final int PREF_W = 400;
  protected static final int PREF_H = 400;

  public static void main(String[] args) {
    // need to get the JSON file working first
    System.setProperty("http.agent", "Chrome");
    String url = "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054";

    String MarsUrl = "https://api.maas2.apollorion.com/";

    JsonObject MarsJson = new JsonObject(MarsUrl);
    String stringMarsURL = MarsJson.getJSONFromURL();

    MarsModell modellMars = new MarsModell(stringMarsURL);




    





    JsonObject stringJson = new JsonObject(url);
    // gives the option to either have a jsonobject from a url/file:
    String stringJsonURL = stringJson.getJSONFromURL();
    WeatherModell modell = new WeatherModell(stringJsonURL);
    // modell.getMultipleTimeDetails(modell.genericDetailsInfoList(), 0), modell.genericDetailsInfoList()
    WeatherView view = new WeatherView(modell, modellMars);




    // TIME FOR TIME-----
    TetrisBoard board = new TetrisBoard(7, modell.genericDetailsInfoList().size()+2);

    TetrisModel model = new TetrisModel(board, modell);
    showTimeGrid i = new showTimeGrid(board, modell);
    TetrisView view2 = new TetrisView(model, modell);
    WeatherController controller = new WeatherController(i, view2);
    // TIME FOR TIME----


    // DAY FOR DAY_---- WE dont want to show to many days forwards (even YR.no dosent do that), since we dont have enough certain data to sustain our preferably certain data ....
    TetrisBoard board2 = new TetrisBoard(3, modell.genericDetailsInfoList().size()+2);

    TetrisModel model2 = new TetrisModel(board2, modell);
    showDayGrid i2 = new showDayGrid(board2, modell);
    TetrisView view3 = new TetrisView(model2, modell);
    WeatherController controller2 = new WeatherController(i2, view3);
    // DAY FOR DAY___

       


    // Inspirert ifra denne siden: https://stackoverflow.com/questions/41685700/two-jpanels-in-one-jframe
    // for å få en inspierasjon til strukturen til jframe og jpanel - gridlayout

    // Inspirert ifra denne siden: https://stackoverflow.com/questions/10801104/how-to-make-a-jframe-scrollable-in-java
    // for å få en inspirasjon til scrollable side!

    JFrame frame = new JFrame();
    final JScrollPane scrPane = new JScrollPane() {
      @Override
      public Dimension getPreferredSize() {
         return new Dimension(PREF_W, PREF_H);
      }
   };

    containerPane = new JPanel();
   
    bottomPane = new JPanel();

    containerPane.setLayout(new GridLayout(1, 1));
    bottomPane.setLayout(new BorderLayout());

   
    scrPane.setViewportView(containerPane);
  

    bottomPane.add(view, BorderLayout.BEFORE_FIRST_LINE);
    bottomPane.add(view3,BorderLayout.CENTER);
    bottomPane.add(view2, BorderLayout.AFTER_LAST_LINE);

   // topPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Using GridLayout"));
    //bottomPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Using BorderLayout"));

    containerPane.add(bottomPane);

    scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    //frame.setContentPane(containerPane);
   // frame.setSize(700, 680);

    frame.add(scrPane);
   // frame.setLayout(null);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
   // frame.setSize(new Dimension(500,500));



   

  }
}
