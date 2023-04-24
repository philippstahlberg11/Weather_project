package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.modell.iShowGrid;
import no.uib.inf101.sem2.modell.showDayGrid;
import no.uib.inf101.sem2.view.TableView;

import java.awt.Dimension;
import java.awt.event.*;
/* import java.lang.reflect.Constructor; */
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class WeatherController implements java.awt.event.KeyListener, ActionListener {

    private TableView view;

    JButton showNextGrid = new JButton("NextGrid");

    JButton nextButton = new JButton("Next Page");
    JButton previousButton = new JButton("Previous Page");

    private iShowGrid showgrid;

    List<iShowGrid> showCorrectGrid = new ArrayList<>();

    public WeatherController(iShowGrid showGrid, TableView view) {
        this.showgrid = showGrid;
        this.view = view;

        view.setFocusable(true);
        view.addKeyListener(this);

 /*        this.showNextGrid.setLayout(null);
        showNextGrid.setVisible(true);
        showNextGrid.setSize(new Dimension(100, 50));
        this.view.add(showNextGrid);
        showNextGrid.setLocation(50, 50);
        showNextGrid.addActionListener(e -> showGridNextPressed()); */
        

        if (showGrid instanceof showDayGrid) {
            // we dont want to be able to scroll trought so many days forwards since the
            // data is extremly uncertain after just a few days forwards...
            nextButton.setVisible(false);
            previousButton.setVisible(false);
            // set a value of 24 hours, + 12 hours, that way we guarantee that we can at
            // least show
            // mostly correct information about today and tomorrow, more that this can crash
            // my pc...
            this.showgrid.showGridFirst(24 + 12);
        } else {
            this.showgrid.showGridFirst(2);
            initiliazingHourToHourGrid();

        }

    }

    private void initiliazingHourToHourGrid() {

        // method for initialising button for next! (and previous with same logic)
        // https://stackoverflow.com/questions/3195666/how-to-place-a-jbutton-at-a-desired-location-in-a-jframe-using-java
        // How to make a button and initiliazing it:
        // https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java

        this.view.setLayout(null);
        this.nextButton.setLayout(null);
        this.previousButton.setLayout(null);

        nextButton.setVisible(true);
        previousButton.setVisible(true);
        previousButton.setSize(new Dimension(100, 50));
        nextButton.setSize(new Dimension(100, 50));

        this.view.add(previousButton);
        this.view.add(nextButton);
        previousButton.setLocation(50, 625);
        nextButton.setLocation(1200, 625);

        // get the height/width of the grid
        nextButton.addActionListener(e -> nextButtonPressed());
        previousButton.addActionListener(e -> previousButtonPressed());
    }

    private void nextButtonPressed() {

        this.previousButton.setVisible(true);
        try {
            this.showgrid.showNextPage();
            this.view.repaint();

        } catch (Exception e) {
            this.nextButton.setVisible(false);
        }
        this.view.repaint();

    }

    private void previousButtonPressed() {

        this.nextButton.setVisible(true);
        try {
            this.showgrid.showPreviousPage();
            this.view.repaint();

        } catch (Exception e) {
            this.previousButton.setVisible(false);
        }
        this.view.repaint();
    }

    /* private void showGridNextPressed() {
        this.view.setVisible(false);
        this.view.repaint();

    } */

    @Override
    public void keyPressed(KeyEvent e) throws IndexOutOfBoundsException {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // generell formel for alle knapper evt...
    }

}
