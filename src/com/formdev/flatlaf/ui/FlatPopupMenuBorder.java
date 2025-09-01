package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class FlatPopupMenuBorder extends FlatLineBorder implements FlatStylingSupport.StyleableBorder {
   private Color borderColor;

   public FlatPopupMenuBorder() {
      super(UIManager.getInsets("PopupMenu.borderInsets"), UIManager.getColor("PopupMenu.borderColor"));
   }

   public Object applyStyleProperty(String key, Object value) {
      byte var5 = -1;
      switch(key.hashCode()) {
      case 722830999:
         if (key.equals("borderColor")) {
            var5 = 1;
         }
         break;
      case 1103974978:
         if (key.equals("borderInsets")) {
            var5 = 0;
         }
      }

      switch(var5) {
      case 0:
         return this.applyStyleProperty((Insets)value);
      case 1:
         Object oldValue = this.getLineColor();
         this.borderColor = (Color)value;
         return oldValue;
      default:
         throw new FlatStylingSupport.UnknownStyleException(key);
      }
   }

   public Map<String, Class<?>> getStyleableInfos() {
      Map<String, Class<?>> infos = new FlatStylingSupport.StyleableInfosMap();
      infos.put("borderInsets", Insets.class);
      infos.put("borderColor", Color.class);
      return infos;
   }

   public Object getStyleableValue(String key) {
      byte var3 = -1;
      switch(key.hashCode()) {
      case 722830999:
         if (key.equals("borderColor")) {
            var3 = 1;
         }
         break;
      case 1103974978:
         if (key.equals("borderInsets")) {
            var3 = 0;
         }
      }

      switch(var3) {
      case 0:
         return this.getStyleableValue();
      case 1:
         return this.borderColor;
      default:
         return null;
      }
   }

   public Color getLineColor() {
      return this.borderColor != null ? this.borderColor : super.getLineColor();
   }

   public Insets getBorderInsets(Component c, Insets insets) {
      if (c instanceof Container && ((Container)c).getComponentCount() > 0 && ((Container)c).getComponent(0) instanceof JScrollPane) {
         insets.left = insets.top = insets.right = insets.bottom = UIScale.scale(1);
         return insets;
      } else {
         return super.getBorderInsets(c, insets);
      }
   }
}
