package com.formdev.flatlaf.ui;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.plaf.SpinnerUI;

public class FlatRoundBorder extends FlatBorder {
   @FlatStylingSupport.Styleable
   protected int arc = UIManager.getInt("Component.arc");
   @FlatStylingSupport.Styleable
   protected Boolean roundRect;

   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      if (this.isMacStyleSpinner(c)) {
         int macStyleButtonsWidth = ((FlatSpinnerUI)((JSpinner)c).getUI()).getMacStyleButtonsWidth();
         width -= macStyleButtonsWidth;
         if (!c.getComponentOrientation().isLeftToRight()) {
            x += macStyleButtonsWidth;
         }
      }

      super.paintBorder(c, g, x, y, width, height);
   }

   protected int getArc(Component c) {
      if (this.isCellEditor(c)) {
         return 0;
      } else {
         Boolean roundRect = FlatUIUtils.isRoundRect(c);
         if (roundRect == null) {
            roundRect = this.roundRect;
         }

         return roundRect != null ? (roundRect ? 32767 : 0) : (this.isMacStyleSpinner(c) ? 0 : this.arc);
      }
   }

   private boolean isMacStyleSpinner(Component c) {
      if (c instanceof JSpinner) {
         SpinnerUI ui = ((JSpinner)c).getUI();
         if (ui instanceof FlatSpinnerUI) {
            return ((FlatSpinnerUI)ui).isMacStyle();
         }
      }

      return false;
   }
}
