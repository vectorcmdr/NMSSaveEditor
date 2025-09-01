package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

public class FlatEmptyBorder extends EmptyBorderUIResource {
   public FlatEmptyBorder() {
      super(0, 0, 0, 0);
   }

   public FlatEmptyBorder(int top, int left, int bottom, int right) {
      super(top, left, bottom, right);
   }

   public FlatEmptyBorder(Insets insets) {
      super(insets);
   }

   public Insets getBorderInsets() {
      return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
   }

   public Insets getBorderInsets(Component c, Insets insets) {
      return scaleInsets(c, insets, this.top, this.left, this.bottom, this.right);
   }

   protected static Insets scaleInsets(Component c, Insets insets, int top, int left, int bottom, int right) {
      boolean leftToRight = left == right || c == null || c.getComponentOrientation().isLeftToRight();
      insets.left = UIScale.scale(leftToRight ? left : right);
      insets.top = UIScale.scale(top);
      insets.right = UIScale.scale(leftToRight ? right : left);
      insets.bottom = UIScale.scale(bottom);
      return insets;
   }

   public Insets getUnscaledBorderInsets() {
      return super.getBorderInsets();
   }

   public Object applyStyleProperty(Insets insets) {
      Insets oldInsets = this.getUnscaledBorderInsets();
      this.top = insets.top;
      this.left = insets.left;
      this.bottom = insets.bottom;
      this.right = insets.right;
      return oldInsets;
   }

   public Insets getStyleableValue() {
      return new Insets(this.top, this.left, this.bottom, this.right);
   }
}
