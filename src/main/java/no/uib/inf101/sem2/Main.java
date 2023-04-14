package no.uib.inf101.sem2;

import no.uib.inf101.sem2.view.SampleView;
import no.uib.inf101.sem2.view.TetrisView;
import no.uib.inf101.sem2.view.WeatherView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import no.uib.inf101.sem2.modell.showGrid;
import no.uib.inf101.sem2.modell.JsonObject;
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

    JsonObject stringJson = new JsonObject(url);
    // gives the option to either have a jsonobject from a url/file:
    String stringJsonURL = stringJson.getJSONFromURL();

    WeatherModell modell = new WeatherModell(stringJsonURL);
    // modell.getMultipleTimeDetails(modell.genericDetailsInfoList(), 0), modell.genericDetailsInfoList()
    WeatherView view = new WeatherView(modell);




    TetrisBoard board = new TetrisBoard(7, modell.genericDetailsInfoList().size()+2);

    //board.set(new CellPosition(0, 0),"air_pressure_at_sea_level");
    //board.set(new CellPosition(0, 3),"air_pressure_at_sea_level");
    //board.set(new CellPosition(2, 0),"air_pressure_at_sea_level"); 
    //board.set(new CellPosition(2, 3),"air_pressure_at_sea_level");

     // limit på 86 i "time" par.

    TetrisModel model = new TetrisModel(board, modell);
    showGrid i = new showGrid(board, modell);
    TetrisView view2 = new TetrisView(model, modell);
    WeatherController controller = new WeatherController(i, view2);


    
       
    System.out.println(modell.getAverageOfArray( modell.getListOfMultipleTimeDetails(10, "air_temperature").get(0)));



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
    bottomPane.add(new JLabel("Day-for-Day-info-grid--------------------------------------"), BorderLayout.CENTER);
    bottomPane.add(view2, BorderLayout.AFTER_LAST_LINE);

   // topPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Using GridLayout"));
    bottomPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Using BorderLayout"));

    containerPane.add(bottomPane);

  

    scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


   

   /*  frame.getContentPane().add(containerPane); */
   // frame.add(scrPane);
   


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
