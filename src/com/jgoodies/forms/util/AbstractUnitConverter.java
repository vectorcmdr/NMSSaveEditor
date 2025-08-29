/*     */ package com.jgoodies.forms.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Toolkit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractUnitConverter
/*     */   implements UnitConverter
/*     */ {
/*     */   private static final int DTP_RESOLUTION = 72;
/*     */   
/*     */   public int inchAsPixel(double in, Component component) {
/*  63 */     return inchAsPixel(in, getScreenResolution(component));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int millimeterAsPixel(double mm, Component component) {
/*  76 */     return millimeterAsPixel(mm, getScreenResolution(component));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int centimeterAsPixel(double cm, Component component) {
/*  89 */     return centimeterAsPixel(cm, getScreenResolution(component));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int pointAsPixel(int pt, Component component) {
/* 102 */     return pointAsPixel(pt, getScreenResolution(component));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dialogUnitXAsPixel(int dluX, Component c) {
/* 114 */     return dialogUnitXAsPixel(dluX, getDialogBaseUnitsX(c));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dialogUnitYAsPixel(int dluY, Component c) {
/* 127 */     return dialogUnitYAsPixel(dluY, getDialogBaseUnitsY(c));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract double getDialogBaseUnitsX(Component paramComponent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract double getDialogBaseUnitsY(Component paramComponent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int inchAsPixel(double in, int dpi) {
/* 165 */     return (int)Math.round(dpi * in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int millimeterAsPixel(double mm, int dpi) {
/* 177 */     return (int)Math.round(dpi * mm * 10.0D / 254.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int centimeterAsPixel(double cm, int dpi) {
/* 189 */     return (int)Math.round(dpi * cm * 100.0D / 254.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int pointAsPixel(int pt, int dpi) {
/* 201 */     return Math.round((dpi * pt / 72));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int dialogUnitXAsPixel(int dluX, double dialogBaseUnitsX) {
/* 213 */     return (int)Math.round(dluX * dialogBaseUnitsX / 4.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int dialogUnitYAsPixel(int dluY, double dialogBaseUnitsY) {
/* 225 */     return (int)Math.round(dluY * dialogBaseUnitsY / 8.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double computeAverageCharWidth(FontMetrics metrics, String testString) {
/* 243 */     int width = metrics.stringWidth(testString);
/* 244 */     double average = width / testString.length();
/*     */     
/* 246 */     return average;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScreenResolution(Component c) {
/* 257 */     if (c == null) {
/* 258 */       return getDefaultScreenResolution();
/*     */     }
/* 260 */     Toolkit toolkit = c.getToolkit();
/* 261 */     return (toolkit != null) ? toolkit.getScreenResolution() : getDefaultScreenResolution();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   private static int defaultScreenResolution = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDefaultScreenResolution() {
/* 276 */     if (defaultScreenResolution == -1) {
/* 277 */       defaultScreenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
/*     */     }
/*     */     
/* 280 */     return defaultScreenResolution;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\form\\util\AbstractUnitConverter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */