package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

public class FlatMarginBorder extends MarginBorder {
   protected int left;
   protected int right;
   protected int top;
   protected int bottom;

   public FlatMarginBorder() {
      this.left = this.right = this.top = this.bottom = 0;
   }

   public FlatMarginBorder(Insets insets) {
      this.left = insets.left;
      this.top = insets.top;
      this.right = insets.right;
      this.bottom = insets.bottom;
   }

   public Insets getBorderInsets(Component c, Insets insets) {
      insets = super.getBorderInsets(c, insets);
      insets.top = UIScale.scale(insets.top + this.top);
      insets.left = UIScale.scale(insets.left + this.left);
      insets.bottom = UIScale.scale(insets.bottom + this.bottom);
      insets.right = UIScale.scale(insets.right + this.right);
      return insets;
   }
}
