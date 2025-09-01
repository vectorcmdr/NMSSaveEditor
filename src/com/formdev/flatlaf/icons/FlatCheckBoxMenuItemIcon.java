package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D.Float;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class FlatCheckBoxMenuItemIcon extends FlatAbstractIcon {
   @FlatStylingSupport.Styleable
   protected Color checkmarkColor = UIManager.getColor("CheckBoxMenuItem.icon.checkmarkColor");
   @FlatStylingSupport.Styleable
   protected Color disabledCheckmarkColor = UIManager.getColor("CheckBoxMenuItem.icon.disabledCheckmarkColor");
   @FlatStylingSupport.Styleable
   protected Color selectionForeground = UIManager.getColor("MenuItem.selectionForeground");

   public FlatCheckBoxMenuItemIcon() {
      super(15, 15, (Color)null);
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

   protected void paintIcon(Component c, Graphics2D g2) {
      boolean selected = c instanceof AbstractButton && ((AbstractButton)c).isSelected();
      if (selected) {
         g2.setColor(this.getCheckmarkColor(c));
         this.paintCheckmark(g2);
      }

   }

   protected void paintCheckmark(Graphics2D g2) {
      Float path = new Float(1, 3);
      path.moveTo(4.5F, 7.5F);
      path.lineTo(6.6F, 10.0F);
      path.lineTo(11.25F, 3.5F);
      g2.setStroke(new BasicStroke(1.9F, 1, 1));
      g2.draw(path);
   }

   protected Color getCheckmarkColor(Component c) {
      if (c instanceof JMenuItem && ((JMenuItem)c).isArmed() && !this.isUnderlineSelection()) {
         return this.selectionForeground;
      } else {
         return c.isEnabled() ? this.checkmarkColor : this.disabledCheckmarkColor;
      }
   }

   protected boolean isUnderlineSelection() {
      return "underline".equals(UIManager.getString("MenuItem.selectionType"));
   }
}
