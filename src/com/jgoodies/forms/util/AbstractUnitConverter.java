package com.jgoodies.forms.util;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Toolkit;

public abstract class AbstractUnitConverter implements UnitConverter {
   private static final int DTP_RESOLUTION = 72;
   private static int defaultScreenResolution = -1;

   public int inchAsPixel(double in, Component component) {
      return this.inchAsPixel(in, this.getScreenResolution(component));
   }

   public int millimeterAsPixel(double mm, Component component) {
      return this.millimeterAsPixel(mm, this.getScreenResolution(component));
   }

   public int centimeterAsPixel(double cm, Component component) {
      return this.centimeterAsPixel(cm, this.getScreenResolution(component));
   }

   public int pointAsPixel(int pt, Component component) {
      return this.pointAsPixel(pt, this.getScreenResolution(component));
   }

   public int dialogUnitXAsPixel(int dluX, Component c) {
      return this.dialogUnitXAsPixel(dluX, this.getDialogBaseUnitsX(c));
   }

   public int dialogUnitYAsPixel(int dluY, Component c) {
      return this.dialogUnitYAsPixel(dluY, this.getDialogBaseUnitsY(c));
   }

   protected abstract double getDialogBaseUnitsX(Component var1);

   protected abstract double getDialogBaseUnitsY(Component var1);

   protected final int inchAsPixel(double in, int dpi) {
      return (int)Math.round((double)dpi * in);
   }

   protected final int millimeterAsPixel(double mm, int dpi) {
      return (int)Math.round((double)dpi * mm * 10.0D / 254.0D);
   }

   protected final int centimeterAsPixel(double cm, int dpi) {
      return (int)Math.round((double)dpi * cm * 100.0D / 254.0D);
   }

   protected final int pointAsPixel(int pt, int dpi) {
      return Math.round((float)(dpi * pt / 72));
   }

   protected int dialogUnitXAsPixel(int dluX, double dialogBaseUnitsX) {
      return (int)Math.round((double)dluX * dialogBaseUnitsX / 4.0D);
   }

   protected int dialogUnitYAsPixel(int dluY, double dialogBaseUnitsY) {
      return (int)Math.round((double)dluY * dialogBaseUnitsY / 8.0D);
   }

   protected double computeAverageCharWidth(FontMetrics metrics, String testString) {
      int width = metrics.stringWidth(testString);
      double average = (double)width / (double)testString.length();
      return average;
   }

   protected int getScreenResolution(Component c) {
      if (c == null) {
         return this.getDefaultScreenResolution();
      } else {
         Toolkit toolkit = c.getToolkit();
         return toolkit != null ? toolkit.getScreenResolution() : this.getDefaultScreenResolution();
      }
   }

   protected int getDefaultScreenResolution() {
      if (defaultScreenResolution == -1) {
         defaultScreenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
      }

      return defaultScreenResolution;
   }
}
