package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D.Float;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class FlatTableHeaderBorder extends FlatEmptyBorder {
   protected Color separatorColor = UIManager.getColor("TableHeader.separatorColor");
   protected Color bottomSeparatorColor = UIManager.getColor("TableHeader.bottomSeparatorColor");
   protected boolean showTrailingVerticalLine = UIManager.getBoolean("TableHeader.showTrailingVerticalLine");

   public FlatTableHeaderBorder() {
      super(UIManager.getInsets("TableHeader.cellMargins"));
   }

   public Insets getBorderInsets(Component c, Insets insets) {
      JTableHeader header = (JTableHeader)SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
      if (header != null && header.getUI() instanceof FlatTableHeaderUI) {
         FlatTableHeaderUI ui = (FlatTableHeaderUI)header.getUI();
         if (ui.cellMargins != null) {
            Insets m = ui.cellMargins;
            return scaleInsets(c, insets, m.top, m.left, m.bottom, m.right);
         }
      }

      return super.getBorderInsets(c, insets);
   }

   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      JTableHeader header = (JTableHeader)SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
      boolean leftToRight = ((Component)(header != null ? header : c)).getComponentOrientation().isLeftToRight();
      boolean paintLeft = !leftToRight;
      boolean paintRight = leftToRight;
      Color separatorColor = this.separatorColor;
      Color bottomSeparatorColor = this.bottomSeparatorColor;
      if (header != null) {
         int hx = SwingUtilities.convertPoint(c, x, y, header).x;
         if (this.isDraggedColumn(header, hx)) {
            paintRight = true;
            paintLeft = true;
         } else {
            if (hx <= 0 && !leftToRight && this.hideTrailingVerticalLine(header)) {
               paintLeft = false;
            }

            if (hx + width >= header.getWidth() && leftToRight && this.hideTrailingVerticalLine(header)) {
               paintRight = false;
            }
         }

         if (header.getUI() instanceof FlatTableHeaderUI) {
            FlatTableHeaderUI ui = (FlatTableHeaderUI)header.getUI();
            if (ui.separatorColor != null) {
               separatorColor = ui.separatorColor;
            }

            if (ui.bottomSeparatorColor != null) {
               bottomSeparatorColor = ui.bottomSeparatorColor;
            }
         }
      }

      float lineWidth = UIScale.scale(1.0F);
      Graphics2D g2 = (Graphics2D)g.create();

      try {
         FlatUIUtils.setRenderingHints(g2);
         g2.setColor(separatorColor);
         if (paintLeft) {
            g2.fill(new Float((float)x, (float)y, lineWidth, (float)height - lineWidth));
         }

         if (paintRight) {
            g2.fill(new Float((float)(x + width) - lineWidth, (float)y, lineWidth, (float)height - lineWidth));
         }

         g2.setColor(bottomSeparatorColor);
         g2.fill(new Float((float)x, (float)(y + height) - lineWidth, (float)width, lineWidth));
      } finally {
         g2.dispose();
      }

   }

   protected boolean isDraggedColumn(JTableHeader header, int x) {
      TableColumn draggedColumn = header.getDraggedColumn();
      if (draggedColumn == null) {
         return false;
      } else {
         int draggedDistance = header.getDraggedDistance();
         if (draggedDistance == 0) {
            return false;
         } else {
            int columnCount = header.getColumnModel().getColumnCount();

            for(int i = 0; i < columnCount; ++i) {
               if (header.getHeaderRect(i).x + draggedDistance == x) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   protected boolean hideTrailingVerticalLine(JTableHeader header) {
      if (header.getUI() instanceof FlatTableHeaderUI) {
         FlatTableHeaderUI ui = (FlatTableHeaderUI)header.getUI();
         if (ui.showTrailingVerticalLine != null) {
            return !ui.showTrailingVerticalLine;
         }
      }

      if (this.showTrailingVerticalLine) {
         return false;
      } else {
         Container viewport = header.getParent();
         Container viewportParent = viewport != null ? viewport.getParent() : null;
         if (!(viewportParent instanceof JScrollPane)) {
            return false;
         } else {
            JScrollPane scrollPane = (JScrollPane)viewportParent;
            JViewport columnHeader = scrollPane.getColumnHeader();
            if (viewport != columnHeader) {
               return false;
            } else {
               JScrollBar vsb = scrollPane.getVerticalScrollBar();
               if (vsb != null && vsb.isVisible()) {
                  return vsb.getY() == viewport.getY();
               } else {
                  return true;
               }
            }
         }
      }
   }
}
