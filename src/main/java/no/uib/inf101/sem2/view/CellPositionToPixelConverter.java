package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;


import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
  Rectangle2D box;
  GridDimension gd;
  double margin;

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;

  }

  public Rectangle2D getBoundsForCell(CellPosition pos) {

    double cellWidth = (box.getWidth() - (double) (gd.cols() + 1) * margin) / (double) (gd.cols());
    double cellHeight = (box.getHeight() - (double) (gd.rows() + 1) * margin) / (double) (gd.rows());

    double cellX = margin * (double) (pos.col() + 1) + cellWidth * (double) (pos.col()) + box.getX();
    double cellY = margin * (double) (pos.row() + 1) + cellHeight * (double) (pos.row()) + box.getY();

    return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
  }
}
