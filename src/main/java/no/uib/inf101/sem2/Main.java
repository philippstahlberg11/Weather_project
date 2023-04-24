package no.uib.inf101.sem2;

import no.uib.inf101.sem2.view.TableView;
import no.uib.inf101.sem2.view.WeatherView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.uib.inf101.sem2.modell.WeatherModell;
import no.uib.inf101.sem2.modell.showDayGrid;
import no.uib.inf101.sem2.modell.showTimeGrid;
import no.uib.inf101.sem2.modell.JsonString;
import no.uib.inf101.sem2.modell.MarsModell;
import no.uib.inf101.sem2.modell.Table;
import no.uib.inf101.sem2.modell.TableModel;
import no.uib.inf101.sem2.controller.WeatherController;

public class Main {

  // maiking the mainPanes: (JPanel)
  private static JPanel containerPane;
  private static JPanel mainPane;

  // change starting width/height
  private static final int PREF_W = 400;
  private static final int PREF_H = 400;

  public static void main(String[] args) {
    // from:
    // https://stackoverflow.com/questions/2529682/setting-user-agent-of-a-java-urlconnection
    // (check below)
    System.setProperty("http.agent", "Chrome");

    String url = "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054";
    String MarsUrl = "https://api.maas2.apollorion.com/";

    // Mars Values:
    JsonString MarsJson = new JsonString(MarsUrl);
    String stringMarsURL = MarsJson.getJSONFromURL();
    MarsModell modellMars = new MarsModell(stringMarsURL);
    // --------

    // Weather values:
    JsonString stringJson = new JsonString(url);
    // gives the option to either have a jsonobject from a url/file:
    String stringJsonURL = stringJson.getJSONFromURL();
    WeatherModell mainModell = new WeatherModell(stringJsonURL);
    WeatherView mainView = new WeatherView(mainModell, modellMars);
    // ----------

    // HOUR FOR HOUR-----
    Table hourBoard = new Table(7, mainModell.genericDetailsInfoList().size() + 2);
    TableModel hourModel = new TableModel(hourBoard, mainModell);
    showTimeGrid hourGrid = new showTimeGrid(hourBoard, mainModell);
    TableView hourToHourTable = new TableView(hourModel);
    new WeatherController(hourGrid, hourToHourTable);
    // ------------

    // DAY FOR DAY_---- WE dont want to show to many days forwards (even YR.no
    // dosent do that), since we dont have enough certain data to sustain our
    // preferably certain data ....
    Table dayBoard = new Table(3, mainModell.genericDetailsInfoList().size() + 2);
    TableModel dayModel = new TableModel(dayBoard, mainModell);
    showDayGrid dayGrid = new showDayGrid(dayBoard, mainModell);
    TableView dayToDayTable = new TableView(dayModel);
    new WeatherController(dayGrid, dayToDayTable);
    // -------------

    JFrame frame = new JFrame();
    // Inspired from this site:
    // https://stackoverflow.com/questions/10801104/how-to-make-a-jframe-scrollable-in-java
    final JScrollPane scrPane = new JScrollPane() {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
      }
    };
    // Inspired from this site:
    // https://stackoverflow.com/questions/41685700/two-jpanels-in-one-jframe
    containerPane = new JPanel();

    mainPane = new JPanel();

    containerPane.setLayout(new GridLayout(1, 1));
    mainPane.setLayout(new BorderLayout());

    scrPane.setViewportView(containerPane);
    
    mainPane.add(mainView, BorderLayout.BEFORE_FIRST_LINE);
    mainPane.add(dayToDayTable, BorderLayout.CENTER);
    mainPane.add(hourToHourTable, BorderLayout.AFTER_LAST_LINE);
    containerPane.add(mainPane);

    scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101");
    frame.add(scrPane);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }
}
