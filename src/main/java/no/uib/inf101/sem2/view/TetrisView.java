package no.uib.inf101.sem2.view;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.text.View;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.modell.WeatherModell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Window.Type;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class TetrisView extends JPanel {

  private ViewableTetrisModel viewableTetrisModel;

  private ColorTheme colorTheme;

  private static final double OUTERMARGIN = 15;
  private static final double INNERMARGIN = 5;

  private HashMap<String, BufferedImage> imageMaps;

  public WeatherModell modellWeather;

  /**
   * Sets a default windowsize, focus on our window to "true".
   * We also define our Colortheme we want for later, and the default background
   * color
   * 
   * @param viewableTetrisModel How many coloumns and rows we have, also as to
   *                            what color each cell should have
   */

  public TetrisView(ViewableTetrisModel viewableTetrisModel, WeatherModell modell) {
    this.viewableTetrisModel = viewableTetrisModel;
    this.colorTheme = new DefaultColorTheme();
    this.modellWeather = modell;

    this.setPreferredSize(new Dimension(100, 680));
    this.setFocusable(true);
    this.setBackground(colorTheme.getBackgroundColor());



    this.imageMaps = viewableTetrisModel.IconToPicture();
  

  }

  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object.
  // Note: NEVER call paintComponent directly yourself
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    drawGame(g2);

  }

  /**
   * Creating a Rectangle2D with a margin, this is our "foundation".
   * Then creating a CellPositionToPixelConverter, for our drawCells, which is
   * called upon at the end.
   * 
   * @param graphics2d paintComponent for our drawing
   */
  public void drawGame(Graphics2D graphics2d) {

    double x = OUTERMARGIN;
    double y = OUTERMARGIN;
    double width = this.getWidth() - 2 * OUTERMARGIN;
    double height = this.getHeight() - 2 * OUTERMARGIN;

    Rectangle2D box = new Rectangle2D.Double(x, y, width, height);

    graphics2d.setColor(this.colorTheme.getFrameColor());
    graphics2d.fill(box);

   
    CellPositionToPixelConverter cellposition = new CellPositionToPixelConverter(box,
        this.viewableTetrisModel.getDimension(), INNERMARGIN);

    // drawing each of the cells that wi ll be in our "background" meaning stationary
    // cells
    drawCells(graphics2d, this.viewableTetrisModel.getTilesOnBoard(), cellposition, this.colorTheme,
        viewableTetrisModel, imageMaps);

  }

  /**
   * Drawing each the cells with a loop, by finding where to draw them,
   * and then setting the color for each corresponding cell
   * 
   * @param graphics2d                   paintComponent for our drawing
   * @param iterableGridCell             the tiles on the board
   * @param cellPositionToPixelConverter find the right pixel coordinates, to
   *                                     later then draw on
   * @param colorTheme                   helps us find the color each of cells
   *                                     have, then setting the color for our
   *                                     cells to them
   */
  public static void drawCells(Graphics2D graphics2d, Iterable<GridCell<String>> iterableGridCell,
      CellPositionToPixelConverter cellPositionToPixelConverter, ColorTheme colorTheme, ViewableTetrisModel view, HashMap<String, BufferedImage> imageS) {

    // Going trougn each of the elements in the iterableGridCell
    // "fillings" each of these to a rectangle, and then setting the corresponding
    // color
    // with the position of the cell.

    for (GridCell<String> i : iterableGridCell) {
      Rectangle2D rektangel = cellPositionToPixelConverter.getBoundsForCell(i.pos());
      // Color color = colorTheme.getCellColor(i.value());
      graphics2d.setColor(Color.WHITE);
      graphics2d.fill(rektangel);
      // ta spesiell hensyn til ikoner:
      if(view.checkIfIcon(i.value())){
        //Image scaledImage = view.IconToPicture(i.value()).getScaledInstance(50, 50, Image.SCALE_FAST);

         // BufferedImage drawnBackground = view.IconToPicture(i.value());
          double xValue = rektangel.getCenterX();
          double yValue = rektangel.getCenterY();
        
        
        Inf101Graphics.drawCenteredImage(graphics2d, imageS.get(i.value()), xValue, yValue, 0.35); 
      }
      else{
        Color colorDifferent = colorTheme.getRowsDefaultColors().get(i.pos().col());
        graphics2d.setColor(colorDifferent);
       Inf101Graphics.drawCenteredString(graphics2d, i.value(), rektangel);
      }
    }
  }

}
