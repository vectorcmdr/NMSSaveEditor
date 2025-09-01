package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.UIManager;

public class FlatMenuArrowIcon extends FlatAbstractIcon {
   @FlatStylingSupport.Styleable
   protected String arrowType = UIManager.getString("Component.arrowType");
   @FlatStylingSupport.Styleable
   protected Color arrowColor = UIManager.getColor("Menu.icon.arrowColor");
   @FlatStylingSupport.Styleable
   protected Color disabledArrowColor = UIManager.getColor("Menu.icon.disabledArrowColor");
   @FlatStylingSupport.Styleable
   protected Color selectionForeground = UIManager.getColor("Menu.selectionForeground");

   public FlatMenuArrowIcon() {
      super(6, 10, (Color)null);
   }

   public Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos() {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   protected void paintIcon(Component c, Graphics2D g) {
      if (c != null && !c.getComponentOrientation().isLeftToRight()) {
         g.rotate(Math.toRadians(180.0D), (double)this.width / 2.0D, (double)this.height / 2.0D);
      }

      g.setColor(this.getArrowColor(c));
      if (FlatUIUtils.isChevron(this.arrowType)) {
         Path2D path = FlatUIUtils.createPath(false, 1.0D, 1.0D, 5.0D, 5.0D, 1.0D, 9.0D);
         g.setStroke(new BasicStroke(1.0F));
         g.draw(path);
      } else {
         g.fill(FlatUIUtils.createPath(0.0D, 0.5D, 5.0D, 5.0D, 0.0D, 9.5D));
      }

   }

   protected Color getArrowColor(Component c) {
      if (c instanceof JMenu && ((JMenu)c).isSelected() && !this.isUnderlineSelection()) {
         return this.selectionForeground;
      } else {
         return c != null && !c.isEnabled() ? this.disabledArrowColor : this.arrowColor;
      }
   }

   protected boolean isUnderlineSelection() {
      return "underline".equals(UIManager.getString("MenuItem.selectionType"));
   }
}
