package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

public class FlatBorder extends MarginBorder implements FlatStylingSupport.StyleableBorder {
   @FlatStylingSupport.Styleable
   protected int focusWidth = UIManager.getInt("Component.focusWidth");
   @FlatStylingSupport.Styleable
   protected float innerFocusWidth = FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0F);
   @FlatStylingSupport.Styleable
   protected float innerOutlineWidth = FlatUIUtils.getUIFloat("Component.innerOutlineWidth", 0.0F);
   @FlatStylingSupport.Styleable
   protected float borderWidth = FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F);
   @FlatStylingSupport.Styleable
   protected Color focusColor = UIManager.getColor("Component.focusColor");
   @FlatStylingSupport.Styleable
   protected Color borderColor = UIManager.getColor("Component.borderColor");
   @FlatStylingSupport.Styleable
   protected Color disabledBorderColor = UIManager.getColor("Component.disabledBorderColor");
   @FlatStylingSupport.Styleable
   protected Color focusedBorderColor = UIManager.getColor("Component.focusedBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color errorBorderColor = UIManager.getColor("Component.error.borderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color errorFocusedBorderColor = UIManager.getColor("Component.error.focusedBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color warningBorderColor = UIManager.getColor("Component.warning.borderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color warningFocusedBorderColor = UIManager.getColor("Component.warning.focusedBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color customBorderColor = UIManager.getColor("Component.custom.borderColor");
   @FlatStylingSupport.Styleable
   protected String outline;
   @FlatStylingSupport.Styleable
   protected Color outlineColor;
   @FlatStylingSupport.Styleable
   protected Color outlineFocusedColor;

   public Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos() {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      Graphics2D g2 = (Graphics2D)g.create();

      try {
         FlatUIUtils.setRenderingHints(g2);
         float focusWidth = UIScale.scale((float)this.getFocusWidth(c));
         float focusInnerWidth = 0.0F;
         float borderWidth = UIScale.scale(this.getBorderWidth(c));
         float arc = UIScale.scale((float)this.getArc(c));
         Color outlineColor = this.getOutlineColor(c);
         Color focusColor = null;
         if (outlineColor != null || this.isFocused(c)) {
            float innerWidth = !this.isCellEditor(c) && !(c instanceof JScrollPane) ? (outlineColor != null ? this.innerOutlineWidth : this.getInnerFocusWidth(c)) : 0.0F;
            if (focusWidth > 0.0F || innerWidth > 0.0F) {
               focusColor = outlineColor != null ? outlineColor : this.getFocusColor(c);
               focusInnerWidth = borderWidth + UIScale.scale(innerWidth);
            }
         }

         Paint borderColor = outlineColor != null ? outlineColor : this.getBorderColor(c);
         FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, focusWidth, 1.0F, focusInnerWidth, borderWidth, arc, focusColor, (Paint)borderColor, (Paint)null);
      } finally {
         g2.dispose();
      }

   }

   protected Color getOutlineColor(Component c) {
      if (!(c instanceof JComponent)) {
         return null;
      } else {
         Object outline = ((JComponent)c).getClientProperty("JComponent.outline");
         if (outline == null) {
            outline = this.outline;
         }

         if (outline == null) {
            if (this.outlineColor != null && this.outlineFocusedColor != null) {
               outline = new Color[]{this.outlineFocusedColor, this.outlineColor};
            } else if (this.outlineColor != null) {
               outline = this.outlineColor;
            } else if (this.outlineFocusedColor != null) {
               outline = this.outlineFocusedColor;
            }
         }

         if (outline instanceof String) {
            String var3 = (String)outline;
            byte var4 = -1;
            switch(var3.hashCode()) {
            case 96784904:
               if (var3.equals("error")) {
                  var4 = 0;
               }
               break;
            case 1124446108:
               if (var3.equals("warning")) {
                  var4 = 1;
               }
            }

            switch(var4) {
            case 0:
               return this.isFocused(c) ? this.errorFocusedBorderColor : this.errorBorderColor;
            case 1:
               return this.isFocused(c) ? this.warningFocusedBorderColor : this.warningBorderColor;
            }
         } else {
            if (outline instanceof Color) {
               Color color = (Color)outline;
               if (!this.isFocused(c) && this.customBorderColor instanceof DerivedColor) {
                  color = ((DerivedColor)this.customBorderColor).derive(color);
               }

               return color;
            }

            if (outline instanceof Color[] && ((Color[])outline).length >= 2) {
               return ((Color[])outline)[this.isFocused(c) ? 0 : 1];
            }
         }

         return null;
      }
   }

   protected Color getFocusColor(Component c) {
      return this.focusColor;
   }

   protected Paint getBorderColor(Component c) {
      return this.isEnabled(c) ? (this.isFocused(c) ? this.focusedBorderColor : this.borderColor) : this.disabledBorderColor;
   }

   protected boolean isEnabled(Component c) {
      if (c instanceof JScrollPane) {
         JViewport viewport = ((JScrollPane)c).getViewport();
         Component view = viewport != null ? viewport.getView() : null;
         if (view != null && !this.isEnabled(view)) {
            return false;
         }
      }

      return c.isEnabled();
   }

   protected boolean isFocused(Component c) {
      if (c instanceof JScrollPane) {
         return FlatScrollPaneUI.isPermanentFocusOwner((JScrollPane)c);
      } else if (c instanceof JComboBox) {
         return FlatComboBoxUI.isPermanentFocusOwner((JComboBox)c);
      } else {
         return c instanceof JSpinner ? FlatSpinnerUI.isPermanentFocusOwner((JSpinner)c) : FlatUIUtils.isPermanentFocusOwner(c);
      }
   }

   protected boolean isCellEditor(Component c) {
      return FlatUIUtils.isCellEditor(c);
   }

   public Insets getBorderInsets(Component c, Insets insets) {
      float focusWidth = UIScale.scale((float)this.getFocusWidth(c));
      int ow = Math.round(focusWidth + UIScale.scale((float)this.getLineWidth(c)));
      insets = super.getBorderInsets(c, insets);
      insets.top = UIScale.scale(insets.top) + ow;
      insets.left = UIScale.scale(insets.left) + ow;
      insets.bottom = UIScale.scale(insets.bottom) + ow;
      insets.right = UIScale.scale(insets.right) + ow;
      if (this.isCellEditor(c)) {
         insets.top = insets.bottom = 0;
         if (c.getComponentOrientation().isLeftToRight()) {
            insets.right = 0;
         } else {
            insets.left = 0;
         }
      }

      return insets;
   }

   protected int getFocusWidth(Component c) {
      return this.isCellEditor(c) ? 0 : this.focusWidth;
   }

   protected float getInnerFocusWidth(Component c) {
      return this.innerFocusWidth;
   }

   protected int getLineWidth(Component c) {
      return 1;
   }

   protected float getBorderWidth(Component c) {
      return this.borderWidth;
   }

   protected int getArc(Component c) {
      return 0;
   }
}
