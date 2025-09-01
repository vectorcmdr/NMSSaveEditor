package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatTableHeaderUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.JTableHeader;

public class FlatAscendingSortIcon extends FlatAbstractIcon {
   protected boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
   protected Color sortIconColor = UIManager.getColor("Table.sortIconColor");

   public FlatAscendingSortIcon() {
      super(10, 5, (Color)null);
   }

   protected void paintIcon(Component c, Graphics2D g) {
      boolean chevron = this.chevron;
      Color sortIconColor = this.sortIconColor;
      JTableHeader tableHeader = (JTableHeader)SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
      if (tableHeader != null) {
         TableHeaderUI ui = tableHeader.getUI();
         if (ui instanceof FlatTableHeaderUI) {
            FlatTableHeaderUI fui = (FlatTableHeaderUI)ui;
            if (fui.arrowType != null) {
               chevron = FlatUIUtils.isChevron(fui.arrowType);
            }

            if (fui.sortIconColor != null) {
               sortIconColor = fui.sortIconColor;
            }
         }
      }

      g.setColor(sortIconColor);
      this.paintArrow(c, g, chevron);
   }

   protected void paintArrow(Component c, Graphics2D g, boolean chevron) {
      if (chevron) {
         Path2D path = FlatUIUtils.createPath(false, 1.0D, 4.0D, 5.0D, 0.0D, 9.0D, 4.0D);
         g.setStroke(new BasicStroke(1.0F));
         g.draw(path);
      } else {
         g.fill(FlatUIUtils.createPath(0.5D, 5.0D, 5.0D, 0.0D, 9.5D, 5.0D));
      }

   }
}
