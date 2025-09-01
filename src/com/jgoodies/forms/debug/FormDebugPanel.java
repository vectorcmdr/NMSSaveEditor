package com.jgoodies.forms.debug;

import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class FormDebugPanel extends JPanel {
   public static boolean paintRowsDefault = true;
   private static final Color DEFAULT_GRID_COLOR;
   private boolean paintInBackground;
   private boolean paintDiagonals;
   private boolean paintRows;
   private Color gridColor;

   public FormDebugPanel() {
      this((FormLayout)null);
   }

   public FormDebugPanel(FormLayout layout) {
      this(layout, false, false);
   }

   public FormDebugPanel(boolean paintInBackground, boolean paintDiagonals) {
      this((FormLayout)null, paintInBackground, paintDiagonals);
   }

   public FormDebugPanel(FormLayout layout, boolean paintInBackground, boolean paintDiagonals) {
      super(layout);
      this.paintRows = paintRowsDefault;
      this.gridColor = DEFAULT_GRID_COLOR;
      this.setPaintInBackground(paintInBackground);
      this.setPaintDiagonals(paintDiagonals);
      this.setGridColor(DEFAULT_GRID_COLOR);
   }

   public void setPaintInBackground(boolean b) {
      this.paintInBackground = b;
   }

   public void setPaintDiagonals(boolean b) {
      this.paintDiagonals = b;
   }

   public void setPaintRows(boolean b) {
      this.paintRows = b;
   }

   public void setGridColor(Color color) {
      this.gridColor = color;
   }

   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (this.paintInBackground) {
         this.paintGrid(g);
      }

   }

   public void paint(Graphics g) {
      super.paint(g);
      if (!this.paintInBackground) {
         this.paintGrid(g);
      }

   }

   private void paintGrid(Graphics g) {
      if (this.getLayout() instanceof FormLayout) {
         FormLayout.LayoutInfo layoutInfo = FormDebugUtils.getLayoutInfo(this);
         int left = layoutInfo.getX();
         int top = layoutInfo.getY();
         int width = layoutInfo.getWidth();
         int height = layoutInfo.getHeight();
         g.setColor(this.gridColor);
         int last = layoutInfo.columnOrigins.length - 1;

         int row;
         boolean firstOrLast;
         int y;
         int start;
         int stop;
         int i;
         int length;
         for(row = 0; row <= last; ++row) {
            firstOrLast = row == 0 || row == last;
            y = layoutInfo.columnOrigins[row];
            start = firstOrLast ? 0 : top;
            stop = firstOrLast ? this.getHeight() : top + height;

            for(i = start; i < stop; i += 5) {
               length = Math.min(3, stop - i);
               g.fillRect(y, i, 1, length);
            }
         }

         last = layoutInfo.rowOrigins.length - 1;

         for(row = 0; row <= last; ++row) {
            firstOrLast = row == 0 || row == last;
            y = layoutInfo.rowOrigins[row];
            start = firstOrLast ? 0 : left;
            stop = firstOrLast ? this.getWidth() : left + width;
            if (firstOrLast || this.paintRows) {
               for(i = start; i < stop; i += 5) {
                  length = Math.min(3, stop - i);
                  g.fillRect(i, y, length, 1);
               }
            }
         }

         if (this.paintDiagonals) {
            g.drawLine(left, top, left + width, top + height);
            g.drawLine(left, top + height, left + width, top);
         }

      }
   }

   static {
      DEFAULT_GRID_COLOR = Color.red;
   }
}
