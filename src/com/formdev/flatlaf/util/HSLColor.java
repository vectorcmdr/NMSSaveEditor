package com.formdev.flatlaf.util;

import java.awt.Color;

public class HSLColor {
   private final Color rgb;
   private final float[] hsl;
   private final float alpha;

   public HSLColor(Color rgb) {
      this.rgb = rgb;
      this.hsl = fromRGB(rgb);
      this.alpha = (float)rgb.getAlpha() / 255.0F;
   }

   public HSLColor(float h, float s, float l) {
      this(h, s, l, 1.0F);
   }

   public HSLColor(float h, float s, float l, float alpha) {
      this.hsl = new float[]{h, s, l};
      this.alpha = alpha;
      this.rgb = toRGB(this.hsl, alpha);
   }

   public HSLColor(float[] hsl) {
      this(hsl, 1.0F);
   }

   public HSLColor(float[] hsl, float alpha) {
      this.hsl = hsl;
      this.alpha = alpha;
      this.rgb = toRGB(hsl, alpha);
   }

   public Color adjustHue(float degrees) {
      return toRGB(degrees, this.hsl[1], this.hsl[2], this.alpha);
   }

   public Color adjustLuminance(float percent) {
      return toRGB(this.hsl[0], this.hsl[1], percent, this.alpha);
   }

   public Color adjustSaturation(float percent) {
      return toRGB(this.hsl[0], percent, this.hsl[2], this.alpha);
   }

   public Color adjustShade(float percent) {
      float multiplier = (100.0F - percent) / 100.0F;
      float l = Math.max(0.0F, this.hsl[2] * multiplier);
      return toRGB(this.hsl[0], this.hsl[1], l, this.alpha);
   }

   public Color adjustTone(float percent) {
      float multiplier = (100.0F + percent) / 100.0F;
      float l = Math.min(100.0F, this.hsl[2] * multiplier);
      return toRGB(this.hsl[0], this.hsl[1], l, this.alpha);
   }

   public float getAlpha() {
      return this.alpha;
   }

   public Color getComplementary() {
      float hue = (this.hsl[0] + 180.0F) % 360.0F;
      return toRGB(hue, this.hsl[1], this.hsl[2]);
   }

   public float getHue() {
      return this.hsl[0];
   }

   public float[] getHSL() {
      return this.hsl;
   }

   public float getLuminance() {
      return this.hsl[2];
   }

   public Color getRGB() {
      return this.rgb;
   }

   public float getSaturation() {
      return this.hsl[1];
   }

   public String toString() {
      String toString = "HSLColor[h=" + this.hsl[0] + ",s=" + this.hsl[1] + ",l=" + this.hsl[2] + ",alpha=" + this.alpha + "]";
      return toString;
   }

   public static float[] fromRGB(Color color) {
      float[] rgb = color.getRGBColorComponents((float[])null);
      float r = rgb[0];
      float g = rgb[1];
      float b = rgb[2];
      float min = Math.min(r, Math.min(g, b));
      float max = Math.max(r, Math.max(g, b));
      float h = 0.0F;
      if (max == min) {
         h = 0.0F;
      } else if (max == r) {
         h = (60.0F * (g - b) / (max - min) + 360.0F) % 360.0F;
      } else if (max == g) {
         h = 60.0F * (b - r) / (max - min) + 120.0F;
      } else if (max == b) {
         h = 60.0F * (r - g) / (max - min) + 240.0F;
      }

      float l = (max + min) / 2.0F;
      float s;
      if (max == min) {
         s = 0.0F;
      } else if (l <= 0.5F) {
         s = (max - min) / (max + min);
      } else {
         s = (max - min) / (2.0F - max - min);
      }

      return new float[]{h, s * 100.0F, l * 100.0F};
   }

   public static Color toRGB(float[] hsl) {
      return toRGB(hsl, 1.0F);
   }

   public static Color toRGB(float[] hsl, float alpha) {
      return toRGB(hsl[0], hsl[1], hsl[2], alpha);
   }

   public static Color toRGB(float h, float s, float l) {
      return toRGB(h, s, l, 1.0F);
   }

   public static Color toRGB(float h, float s, float l, float alpha) {
      String message;
      if (!(s < 0.0F) && !(s > 100.0F)) {
         if (!(l < 0.0F) && !(l > 100.0F)) {
            if (!(alpha < 0.0F) && !(alpha > 1.0F)) {
               h %= 360.0F;
               h /= 360.0F;
               s /= 100.0F;
               l /= 100.0F;
               float q;
               if ((double)l < 0.5D) {
                  q = l * (1.0F + s);
               } else {
                  q = l + s - s * l;
               }

               float p = 2.0F * l - q;
               float r = Math.max(0.0F, HueToRGB(p, q, h + 0.33333334F));
               float g = Math.max(0.0F, HueToRGB(p, q, h));
               float b = Math.max(0.0F, HueToRGB(p, q, h - 0.33333334F));
               r = Math.min(r, 1.0F);
               g = Math.min(g, 1.0F);
               b = Math.min(b, 1.0F);
               return new Color(r, g, b, alpha);
            } else {
               message = "Color parameter outside of expected range - Alpha";
               throw new IllegalArgumentException(message);
            }
         } else {
            message = "Color parameter outside of expected range - Luminance";
            throw new IllegalArgumentException(message);
         }
      } else {
         message = "Color parameter outside of expected range - Saturation";
         throw new IllegalArgumentException(message);
      }
   }

   private static float HueToRGB(float p, float q, float h) {
      if (h < 0.0F) {
         ++h;
      }

      if (h > 1.0F) {
         --h;
      }

      if (6.0F * h < 1.0F) {
         return p + (q - p) * 6.0F * h;
      } else if (2.0F * h < 1.0F) {
         return q;
      } else {
         return 3.0F * h < 2.0F ? p + (q - p) * 6.0F * (0.6666667F - h) : p;
      }
   }
}
