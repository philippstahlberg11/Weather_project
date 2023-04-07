package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.modell.showGrid;
import no.uib.inf101.sem2.view.TetrisView;

import java.awt.Dimension;
import java.awt.event.*;  
import javax.swing.*;  

public class WeatherController implements java.awt.event.KeyListener, ActionListener {

    public showGrid showgrid;
    public TetrisView view;

    JButton nextButton = new JButton("Next Page");
    JButton previousButton = new JButton("Previous Page");



    public WeatherController(showGrid showGrid, TetrisView view){
        this.showgrid = showGrid;
        this.view = view;

        view.setFocusable(true);
        view.addKeyListener(this);
        this.showgrid.showGridFirst(0);

        // method for initialising button for next!

        // https://stackoverflow.com/questions/3195666/how-to-place-a-jbutton-at-a-desired-location-in-a-jframe-using-java
        // for hvordan en lager en knapp (JButton) og plasserer den 
        //https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java

        this.view.setLayout(null);
        this.nextButton.setLayout(null);
        this.previousButton.setLayout(null);

        nextButton.setVisible(true);
        previousButton.setVisible(true);
        previousButton.setSize(new Dimension(100,50));
        nextButton.setSize(new Dimension(100,50));

        this.view.add(previousButton);
        this.view.add(nextButton);
        previousButton.setLocation(600, 600);
        nextButton.setLocation(500, 500);
        nextButton.addActionListener(e -> nextButtonPressed());
        previousButton.addActionListener(e -> previousButtonPressed());

    }   


    private void nextButtonPressed() {
        this.showgrid.showNextPage();
        this.view.repaint();
    }

    private void previousButtonPressed() {
        this.showgrid.showPreviousPage();
        this.view.repaint();
    }


    @Override
    public void keyPressed(KeyEvent e) throws IndexOutOfBoundsException {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //showgrid.showGridFirst(0);
            this.showgrid.showNextPage();
            view.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.showgrid.showPreviousPage();
            view.repaint();
        }
        view.repaint();
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
