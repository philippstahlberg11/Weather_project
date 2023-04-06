package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import no.uib.inf101.sem2.modell.showGrid;
import no.uib.inf101.sem2.view.TetrisView;

public class WeatherController implements java.awt.event.KeyListener {

    public showGrid showgrid;
    public TetrisView view;

    public WeatherController(showGrid showGrid, TetrisView view){
        this.showgrid = showGrid;
        this.view = view;

        view.setFocusable(true);
        view.addKeyListener(this);
      

        this.showgrid.showGridFirst(0);

    }   


    @Override
    public void keyPressed(KeyEvent e) {
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
    
 
}
