package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatTreeUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.function.Function;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.TreeUI;

public class FlatTreeCollapsedIcon extends FlatAbstractIcon {
   private final boolean chevron;
   private Path2D path;

   public FlatTreeCollapsedIcon() {
      this(UIManager.getColor("Tree.icon.collapsedColor"));
   }

   FlatTreeCollapsedIcon(Color color) {
      super(11, 11, color);
      this.chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      this.setStyleColorFromTreeUI(c, g);
      this.rotate(c, g);
      String arrowType = (String)getStyleFromTreeUI(c, (ui) -> {
         return ui.iconArrowType;
      });
      boolean chevron = arrowType != null ? FlatUIUtils.isChevron(arrowType) : this.chevron;
      if (chevron) {
         g.setStroke(new BasicStroke(1.0F, 1, 0));
         if (this.path == null) {
            this.path = FlatUIUtils.createPath(false, 3.5D, 1.5D, 7.5D, 5.5D, 3.5D, 9.5D);
         }

         g.draw(this.path);
      } else {
         if (this.path == null) {
            this.path = FlatUIUtils.createPath(2.0D, 1.0D, 2.0D, 10.0D, 10.0D, 5.5D);
         }

         g.fill(this.path);
      }

   }

   void setStyleColorFromTreeUI(Component c, Graphics2D g) {
      setStyleColorFromTreeUI(c, g, (ui) -> {
         return ui.iconCollapsedColor;
      });
   }

   void rotate(Component c, Graphics2D g) {
      if (!c.getComponentOrientation().isLeftToRight()) {
         g.rotate(Math.toRadians(180.0D), (double)this.width / 2.0D, (double)this.height / 2.0D);
      }

   }

   static <T> T getStyleFromTreeUI(Component c, Function<FlatTreeUI, T> f) {
      JTree tree = c instanceof JTree ? (JTree)c : (JTree)SwingUtilities.getAncestorOfClass(JTree.class, c);
      if (tree != null) {
         TreeUI ui = tree.getUI();
         if (ui instanceof FlatTreeUI) {
            return f.apply((FlatTreeUI)ui);
         }
      }

      return null;
   }

   static void setStyleColorFromTreeUI(Component c, Graphics2D g, Function<FlatTreeUI, Color> f) {
      Color color = (Color)getStyleFromTreeUI(c, f);
      if (color != null) {
         g.setColor(color);
      }

   }
}
