package com.formdev.flatlaf.util;

import java.awt.Color;
import javax.swing.plaf.ColorUIResource;

public class DerivedColor extends ColorUIResource {
   private final ColorFunctions.ColorFunction[] functions;
   private boolean hasBaseOfDefaultColor;
   private int baseOfDefaultColorRGB;

   public DerivedColor(Color defaultColor, ColorFunctions.ColorFunction... functions) {
      super(defaultColor != null ? defaultColor : Color.red);
      this.functions = functions;
   }

   public Color derive(Color baseColor) {
      if ((!this.hasBaseOfDefaultColor || this.baseOfDefaultColorRGB != baseColor.getRGB()) && baseColor != this) {
         Color result = ColorFunctions.applyFunctions(baseColor, this.functions);
         if (!this.hasBaseOfDefaultColor && result.getRGB() == this.getRGB()) {
            this.hasBaseOfDefaultColor = true;
            this.baseOfDefaultColorRGB = baseColor.getRGB();
         }

         return result;
      } else {
         return this;
      }
   }

   public ColorFunctions.ColorFunction[] getFunctions() {
      return this.functions;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append(super.toString());
      ColorFunctions.ColorFunction[] var2 = this.functions;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ColorFunctions.ColorFunction function = var2[var4];
         buf.append('\n');
         buf.append(function.toString());
      }

      return buf.toString();
   }
}
