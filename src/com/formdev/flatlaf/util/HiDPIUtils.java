package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D.Double;
import java.text.AttributedCharacterIterator;
import javax.swing.JComponent;

public class HiDPIUtils {
   private static Boolean useTextYCorrection;
   private static final float[] SCALE_FACTORS = new float[]{1.25F, 1.5F, 1.75F, 2.0F, 2.25F, 2.5F, 3.0F, 3.5F, 4.0F};
   private static final float[] CORRECTION_SEGOE_UI = new float[]{-0.5F, -0.5F, -0.625F, -0.75F, -0.75F, -0.75F, -0.75F, -0.75F, -0.875F};
   private static final float[] CORRECTION_TAHOMA = new float[]{-0.25F, -0.25F, -0.25F, -0.0F, -0.125F, -0.125F, -0.125F, -0.125F, -0.0F};
   private static final float[] CORRECTION_INTER = new float[]{-0.25F, -0.25F, -0.25F, -0.0F, -0.125F, -0.125F, -0.0F, -0.25F, -0.0F};
   private static final float[] CORRECTION_OPEN_SANS = new float[]{-0.5F, -0.25F, -0.25F, -0.0F, -0.25F, -0.25F, -0.0F, -0.25F, -0.25F};
   private static Boolean useDebugScaleFactor;

   public static void paintAtScale1x(Graphics2D g, JComponent c, HiDPIUtils.Painter painter) {
      paintAtScale1x(g, 0, 0, c.getWidth(), c.getHeight(), painter);
   }

   public static void paintAtScale1x(Graphics2D g, int x, int y, int width, int height, HiDPIUtils.Painter painter) {
      AffineTransform t = g.getTransform();
      double scaleX = t.getScaleX();
      double scaleY = t.getScaleY();
      double shearX = t.getShearX();
      double shearY = t.getShearY();
      boolean rotated = shearX != 0.0D || shearY != 0.0D || scaleX <= 0.0D || scaleY <= 0.0D;
      double realScaleX;
      double realScaleY;
      if (rotated) {
         realScaleX = Math.hypot(scaleX, shearX);
         realScaleY = Math.hypot(scaleY, shearY);
      } else {
         realScaleX = Math.abs(scaleX);
         realScaleY = Math.abs(scaleY);
      }

      if (realScaleX == 1.0D && realScaleY == 1.0D) {
         painter.paint(g, x, y, width, height, 1.0D);
      } else {
         double px = (double)x * scaleX + (double)y * shearX + t.getTranslateX();
         double py = (double)y * scaleY + (double)x * shearY + t.getTranslateY();
         Double scaledRect = scale(realScaleX, realScaleY, px, py, width, height);

         try {
            AffineTransform t1x;
            if (rotated) {
               t1x = new AffineTransform(scaleX, shearY, shearX, scaleY, Math.floor(scaledRect.x), Math.floor(scaledRect.y));
               t1x.scale(1.0D / realScaleX, 1.0D / realScaleY);
            } else {
               t1x = new AffineTransform(1.0D, 0.0D, 0.0D, 1.0D, Math.floor(scaledRect.x), Math.floor(scaledRect.y));
            }

            g.setTransform(t1x);
            int swidth = (int)scaledRect.width;
            int sheight = (int)scaledRect.height;
            painter.paint(g, 0, 0, swidth, sheight, realScaleX);
         } finally {
            g.setTransform(t);
         }

      }
   }

   private static Double scale(double scaleX, double scaleY, double px, double py, int width, int height) {
      double newX = normalize(px);
      double newY = normalize(py);
      double newWidth = normalize(px + (double)width * scaleX) - newX;
      double newHeight = normalize(py + (double)height * scaleY) - newY;
      return new Double(newX, newY, newWidth, newHeight);
   }

   private static double normalize(double value) {
      return Math.floor(value + 0.25D) + 0.25D;
   }

   private static boolean useTextYCorrection() {
      if (useTextYCorrection == null) {
         useTextYCorrection = FlatSystemProperties.getBoolean("flatlaf.useTextYCorrection", true);
      }

      return useTextYCorrection;
   }

   public static float computeTextYCorrection(Graphics2D g) {
      if (useTextYCorrection() && SystemInfo.isWindows) {
         if (!SystemInfo.isJava_9_orLater) {
            float scaleFactor = getUserScaleFactor();
            if (scaleFactor > 1.0F) {
               String var2 = g.getFont().getFamily();
               byte var3 = -1;
               switch(var2.hashCode()) {
               case -1272274913:
                  if (var2.equals("Segoe UI Light")) {
                     var3 = 1;
                  }
                  break;
               case -803832663:
                  if (var2.equals("Open Sans")) {
                     var3 = 4;
                  }
                  break;
               case -282906057:
                  if (var2.equals("Noto Sans")) {
                     var3 = 3;
                  }
                  break;
               case 976449354:
                  if (var2.equals("Segoe UI Semibold")) {
                     var3 = 2;
                  }
                  break;
               case 1122284041:
                  if (var2.equals("Segoe UI")) {
                     var3 = 0;
                  }
                  break;
               case 2015806707:
                  if (var2.equals("Verdana")) {
                     var3 = 5;
                  }
               }

               switch(var3) {
               case 0:
               case 1:
               case 2:
                  return -((scaleFactor != 2.25F && scaleFactor != 4.0F ? 0.625F : 0.875F) * scaleFactor);
               case 3:
               case 4:
                  return -(0.3F * scaleFactor);
               case 5:
                  return -((scaleFactor < 2.0F ? 0.4F : 0.3F) * scaleFactor);
               }
            }
         } else {
            String var4 = g.getFont().getFamily();
            byte var5 = -1;
            switch(var4.hashCode()) {
            case -1841836187:
               if (var4.equals("Roboto")) {
                  var5 = 10;
               }
               break;
            case -1797328664:
               if (var4.equals("Tahoma")) {
                  var5 = 6;
               }
               break;
            case -1272274913:
               if (var4.equals("Segoe UI Light")) {
                  var5 = 1;
               }
               break;
            case -803832663:
               if (var4.equals("Open Sans")) {
                  var5 = 14;
               }
               break;
            case -378125933:
               if (var4.equals("Inter Semi Bold")) {
                  var5 = 9;
               }
               break;
            case -282906057:
               if (var4.equals("Noto Sans")) {
                  var5 = 13;
               }
               break;
            case -227670902:
               if (var4.equals("SansSerif")) {
                  var5 = 5;
               }
               break;
            case 70808764:
               if (var4.equals("Inter")) {
                  var5 = 7;
               }
               break;
            case 116582576:
               if (var4.equals("Roboto Medium")) {
                  var5 = 12;
               }
               break;
            case 280053883:
               if (var4.equals("Roboto Light")) {
                  var5 = 11;
               }
               break;
            case 976449354:
               if (var4.equals("Segoe UI Semibold")) {
                  var5 = 2;
               }
               break;
            case 1122284041:
               if (var4.equals("Segoe UI")) {
                  var5 = 0;
               }
               break;
            case 1629507730:
               if (var4.equals("Inter Light")) {
                  var5 = 8;
               }
               break;
            case 2015806707:
               if (var4.equals("Verdana")) {
                  var5 = 3;
               }
               break;
            case 2046749032:
               if (var4.equals("Dialog")) {
                  var5 = 4;
               }
            }

            switch(var5) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
               return correctionForScaleY(g, CORRECTION_SEGOE_UI);
            case 6:
               return correctionForScaleY(g, CORRECTION_TAHOMA);
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
               return correctionForScaleY(g, CORRECTION_INTER);
            case 13:
            case 14:
               return correctionForScaleY(g, CORRECTION_OPEN_SANS);
            }
         }

         return 0.0F;
      } else {
         return 0.0F;
      }
   }

   private static float correctionForScaleY(Graphics2D g, float[] correction) {
      if (correction.length != 9) {
         throw new IllegalArgumentException();
      } else {
         double scaleY = g.getTransform().getScaleY();
         return scaleY < 1.25D ? 0.0F : correction[scaleFactor2index((float)scaleY)];
      }
   }

   private static int scaleFactor2index(float scaleFactor) {
      for(int i = 0; i < SCALE_FACTORS.length; ++i) {
         if (scaleFactor <= SCALE_FACTORS[i]) {
            return i;
         }
      }

      return SCALE_FACTORS.length - 1;
   }

   private static boolean useDebugScaleFactor() {
      if (useDebugScaleFactor == null) {
         useDebugScaleFactor = FlatSystemProperties.getBoolean("FlatLaf.debug.HiDPIUtils.useDebugScaleFactor", false);
      }

      return useDebugScaleFactor;
   }

   private static float getUserScaleFactor() {
      return !useDebugScaleFactor() ? UIScale.getUserScaleFactor() : Float.parseFloat(System.getProperty("FlatLaf.debug.HiDPIUtils.debugScaleFactor", "1"));
   }

   public static void drawStringWithYCorrection(JComponent c, Graphics2D g, String text, int x, int y) {
      drawStringUnderlineCharAtWithYCorrection(c, g, text, -1, x, y);
   }

   public static void drawStringUnderlineCharAtWithYCorrection(JComponent c, Graphics2D g, String text, int underlinedIndex, int x, int y) {
      float yCorrection = computeTextYCorrection(g);
      if (yCorrection != 0.0F) {
         g.translate(0.0D, (double)yCorrection);
         JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
         g.translate(0.0D, (double)(-yCorrection));
      } else {
         JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
      }

   }

   public static Graphics2D createGraphicsTextYCorrection(Graphics2D g) {
      final float yCorrection = computeTextYCorrection(g);
      return (Graphics2D)(yCorrection == 0.0F ? g : new Graphics2DProxy(g) {
         public void drawString(String str, int x, int y) {
            super.drawString(str, (float)x, (float)y + yCorrection);
         }

         public void drawString(String str, float x, float y) {
            super.drawString(str, x, y + yCorrection);
         }

         public void drawString(AttributedCharacterIterator iterator, int x, int y) {
            super.drawString(iterator, (float)x, (float)y + yCorrection);
         }

         public void drawString(AttributedCharacterIterator iterator, float x, float y) {
            super.drawString(iterator, x, y + yCorrection);
         }

         public void drawChars(char[] data, int offset, int length, int x, int y) {
            super.drawChars(data, offset, length, x, Math.round((float)y + yCorrection));
         }

         public void drawBytes(byte[] data, int offset, int length, int x, int y) {
            super.drawBytes(data, offset, length, x, Math.round((float)y + yCorrection));
         }

         public void drawGlyphVector(GlyphVector g, float x, float y) {
            super.drawGlyphVector(g, x, y + yCorrection);
         }
      });
   }

   public interface Painter {
      void paint(Graphics2D var1, int var2, int var3, int var4, int var5, double var6);
   }
}
