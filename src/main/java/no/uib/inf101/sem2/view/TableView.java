package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.GridCell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class TableView extends JPanel {

  private ViewableTableModel viewableTableModel;

  private ColorTheme colorTheme;

  private final double OUTERMARGIN = 15;
  private final double INNERMARGIN = 5;

  private HashMap<String, BufferedImage> imageMaps;

  public TableView(ViewableTableModel viewableTableModel) {
    this.viewableTableModel = viewableTableModel;
    this.colorTheme = new DefaultColorTheme();

    this.setPreferredSize(new Dimension(100, 680));
    this.setFocusable(true);
    this.setBackground(colorTheme.getBackgroundColor());

    // images/icons, being initilized with values!
    this.imageMaps = viewableTableModel.IconToPicture();

  }

  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object.
  // Note: NEVER call paintComponent directly yourself
  @Override
  protected void paintComponent(Graphics g) {
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
  private void drawGame(Graphics2D graphics2d) {

    double x = OUTERMARGIN;
    double y = OUTERMARGIN;
    double width = this.getWidth() - 2 * OUTERMARGIN;
    double height = this.getHeight() - 2 * OUTERMARGIN;

    Rectangle2D box = new Rectangle2D.Double(x, y, width, height);

    graphics2d.setColor(this.colorTheme.getFrameColor());
    graphics2d.fill(box);

    CellPositionToPixelConverter cellposition = new CellPositionToPixelConverter(box,
        this.viewableTableModel.getDimension(), INNERMARGIN);

    drawCells(graphics2d, this.viewableTableModel.getTilesOnBoard(), cellposition, this.colorTheme,
        viewableTableModel, imageMaps);

  }

  /**
   * Drawing each the cells with a loop, by finding where to draw them,
   * and then drawing them as string text or icons
   * 
   * @param graphics2d                   paintComponent for our drawing
   * @param iterableGridCell             the tiles on the board
   * @param cellPositionToPixelConverter find the right pixel coordinates, to
   *                                     later then draw on
   * @param colorTheme                   helps us find the color each of cells
   *                                     have, then setting the color for our
   *                                     cells to them
   * @param viewableTableModel           to later be able to distinguish
   *                                     betweenworking with a icon or just
   *                                     regular text
   * 
   * 
   * @param hashmap<string,              bufferedimage> where we will get our
   *                                     images from (or icons), that are
   *                                     initialized in the constructur above
   * 
   */
  private static void drawCells(Graphics2D graphics2d, Iterable<GridCell<String>> iterableGridCell,
      CellPositionToPixelConverter cellPositionToPixelConverter, ColorTheme colorTheme, ViewableTableModel view,
      HashMap<String, BufferedImage> imageS) {

    // Going trougn each of the elements in the iterableGridCell
    // "fillings" each of these to a rectangle, and then drawing them as strings or
    // as images (icons)

    for (GridCell<String> i : iterableGridCell) {
      Rectangle2D rektangel = cellPositionToPixelConverter.getBoundsForCell(i.pos());
      graphics2d.setColor(Color.WHITE);
      graphics2d.fill(rektangel);
      // We take care of icons vs normal strings of information:
      if (view.checkIfIcon(i.value())) {
        double xValue = rektangel.getCenterX();
        double yValue = rektangel.getCenterY();
        try {
          Inf101Graphics.drawCenteredImage(graphics2d, imageS.get(i.value()), xValue, yValue, 0.35);
        } catch (Exception e) {
          Inf101Graphics.drawCenteredImage(graphics2d, imageS.get("-"), xValue, yValue, 0.35);

        }
      } else {
        Color colorDifferent = colorTheme.getRowsDefaultColors().get(i.pos().col());
        graphics2d.setColor(colorDifferent);
        graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
        try {
          Inf101Graphics.drawCenteredString(graphics2d, i.value(), rektangel);
        } catch (Exception e) {
          Inf101Graphics.drawCenteredString(graphics2d, "NOT FOUND", rektangel);
        }
      }
    }
  }

}
