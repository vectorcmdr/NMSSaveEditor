/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatSystemProperties;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import javax.swing.JComponent;
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
/*     */ public class HiDPIUtils
/*     */ {
/*     */   private static Boolean useTextYCorrection;
/*     */   
/*     */   public static void paintAtScale1x(Graphics2D g, JComponent c, Painter painter) {
/*  39 */     paintAtScale1x(g, 0, 0, c.getWidth(), c.getHeight(), painter);
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
/*     */   public static void paintAtScale1x(Graphics2D g, int x, int y, int width, int height, Painter painter) {
/*     */     double realScaleX, realScaleY;
/*  52 */     AffineTransform t = g.getTransform();
/*     */ 
/*     */     
/*  55 */     double scaleX = t.getScaleX();
/*  56 */     double scaleY = t.getScaleY();
/*  57 */     double shearX = t.getShearX();
/*  58 */     double shearY = t.getShearY();
/*     */ 
/*     */ 
/*     */     
/*  62 */     boolean rotated = (shearX != 0.0D || shearY != 0.0D || scaleX <= 0.0D || scaleY <= 0.0D);
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (rotated) {
/*     */       
/*  68 */       realScaleX = Math.hypot(scaleX, shearX);
/*  69 */       realScaleY = Math.hypot(scaleY, shearY);
/*     */     } else {
/*     */       
/*  72 */       realScaleX = Math.abs(scaleX);
/*  73 */       realScaleY = Math.abs(scaleY);
/*     */     } 
/*     */ 
/*     */     
/*  77 */     if (realScaleX == 1.0D && realScaleY == 1.0D) {
/*  78 */       painter.paint(g, x, y, width, height, 1.0D);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  83 */     double px = x * scaleX + y * shearX + t.getTranslateX();
/*  84 */     double py = y * scaleY + x * shearY + t.getTranslateY();
/*     */ 
/*     */     
/*  87 */     Rectangle2D.Double scaledRect = scale(realScaleX, realScaleY, px, py, width, height);
/*     */     
/*     */     try {
/*     */       AffineTransform t1x;
/*     */       
/*  92 */       if (rotated) {
/*     */         
/*  94 */         t1x = new AffineTransform(scaleX, shearY, shearX, scaleY, Math.floor(scaledRect.x), Math.floor(scaledRect.y));
/*  95 */         t1x.scale(1.0D / realScaleX, 1.0D / realScaleY);
/*     */       } else {
/*  97 */         t1x = new AffineTransform(1.0D, 0.0D, 0.0D, 1.0D, Math.floor(scaledRect.x), Math.floor(scaledRect.y));
/*  98 */       }  g.setTransform(t1x);
/*     */       
/* 100 */       int swidth = (int)scaledRect.width;
/* 101 */       int sheight = (int)scaledRect.height;
/*     */ 
/*     */       
/* 104 */       painter.paint(g, 0, 0, swidth, sheight, realScaleX);
/*     */     } finally {
/*     */       
/* 107 */       g.setTransform(t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Rectangle2D.Double scale(double scaleX, double scaleY, double px, double py, int width, int height) {
/* 117 */     double newX = normalize(px);
/* 118 */     double newY = normalize(py);
/* 119 */     double newWidth = normalize(px + width * scaleX) - newX;
/* 120 */     double newHeight = normalize(py + height * scaleY) - newY;
/*     */     
/* 122 */     return new Rectangle2D.Double(newX, newY, newWidth, newHeight);
/*     */   }
/*     */   
/*     */   private static double normalize(double value) {
/* 126 */     return Math.floor(value + 0.25D) + 0.25D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean useTextYCorrection() {
/* 133 */     if (useTextYCorrection == null)
/* 134 */       useTextYCorrection = Boolean.valueOf(FlatSystemProperties.getBoolean("flatlaf.useTextYCorrection", true)); 
/* 135 */     return useTextYCorrection.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float computeTextYCorrection(Graphics2D g) {
/* 146 */     if (!useTextYCorrection() || !SystemInfo.isWindows) {
/* 147 */       return 0.0F;
/*     */     }
/* 149 */     if (!SystemInfo.isJava_9_orLater) {
/*     */       
/* 151 */       float scaleFactor = getUserScaleFactor();
/* 152 */       if (scaleFactor > 1.0F) {
/* 153 */         switch (g.getFont().getFamily()) {
/*     */           case "Segoe UI":
/*     */           case "Segoe UI Light":
/*     */           case "Segoe UI Semibold":
/* 157 */             return -(((scaleFactor == 2.25F || scaleFactor == 4.0F) ? 0.875F : 0.625F) * scaleFactor);
/*     */           
/*     */           case "Noto Sans":
/*     */           case "Open Sans":
/* 161 */             return -(0.3F * scaleFactor);
/*     */           
/*     */           case "Verdana":
/* 164 */             return -(((scaleFactor < 2.0F) ? 0.4F : 0.3F) * scaleFactor);
/*     */         } 
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
/*     */       }
/*     */     } else {
/* 181 */       switch (g.getFont().getFamily()) {
/*     */         case "Segoe UI":
/*     */         case "Segoe UI Light":
/*     */         case "Segoe UI Semibold":
/*     */         case "Verdana":
/*     */         case "Dialog":
/*     */         case "SansSerif":
/* 188 */           return correctionForScaleY(g, CORRECTION_SEGOE_UI);
/*     */         
/*     */         case "Tahoma":
/* 191 */           return correctionForScaleY(g, CORRECTION_TAHOMA);
/*     */         
/*     */         case "Inter":
/*     */         case "Inter Light":
/*     */         case "Inter Semi Bold":
/*     */         case "Roboto":
/*     */         case "Roboto Light":
/*     */         case "Roboto Medium":
/* 199 */           return correctionForScaleY(g, CORRECTION_INTER);
/*     */         
/*     */         case "Noto Sans":
/*     */         case "Open Sans":
/* 203 */           return correctionForScaleY(g, CORRECTION_OPEN_SANS);
/*     */       } 
/*     */     
/*     */     } 
/* 207 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/* 211 */   private static final float[] SCALE_FACTORS = new float[] { 1.25F, 1.5F, 1.75F, 2.0F, 2.25F, 2.5F, 3.0F, 3.5F, 4.0F };
/*     */   
/* 213 */   private static final float[] CORRECTION_SEGOE_UI = new float[] { -0.5F, -0.5F, -0.625F, -0.75F, -0.75F, -0.75F, -0.75F, -0.75F, -0.875F };
/* 214 */   private static final float[] CORRECTION_TAHOMA = new float[] { -0.25F, -0.25F, -0.25F, -0.0F, -0.125F, -0.125F, -0.125F, -0.125F, -0.0F };
/* 215 */   private static final float[] CORRECTION_INTER = new float[] { -0.25F, -0.25F, -0.25F, -0.0F, -0.125F, -0.125F, -0.0F, -0.25F, -0.0F };
/* 216 */   private static final float[] CORRECTION_OPEN_SANS = new float[] { -0.5F, -0.25F, -0.25F, -0.0F, -0.25F, -0.25F, -0.0F, -0.25F, -0.25F }; private static Boolean useDebugScaleFactor;
/*     */   
/*     */   private static float correctionForScaleY(Graphics2D g, float[] correction) {
/* 219 */     if (correction.length != 9) {
/* 220 */       throw new IllegalArgumentException();
/*     */     }
/* 222 */     double scaleY = g.getTransform().getScaleY();
/* 223 */     return (scaleY < 1.25D) ? 0.0F : correction[scaleFactor2index((float)scaleY)];
/*     */   }
/*     */   
/*     */   private static int scaleFactor2index(float scaleFactor) {
/* 227 */     for (int i = 0; i < SCALE_FACTORS.length; i++) {
/* 228 */       if (scaleFactor <= SCALE_FACTORS[i])
/* 229 */         return i; 
/*     */     } 
/* 231 */     return SCALE_FACTORS.length - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean useDebugScaleFactor() {
/* 237 */     if (useDebugScaleFactor == null)
/* 238 */       useDebugScaleFactor = Boolean.valueOf(FlatSystemProperties.getBoolean("FlatLaf.debug.HiDPIUtils.useDebugScaleFactor", false)); 
/* 239 */     return useDebugScaleFactor.booleanValue();
/*     */   }
/*     */   
/*     */   private static float getUserScaleFactor() {
/* 243 */     return !useDebugScaleFactor() ? 
/* 244 */       UIScale.getUserScaleFactor() : 
/* 245 */       Float.parseFloat(System.getProperty("FlatLaf.debug.HiDPIUtils.debugScaleFactor", "1"));
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
/*     */   public static void drawStringWithYCorrection(JComponent c, Graphics2D g, String text, int x, int y) {
/* 257 */     drawStringUnderlineCharAtWithYCorrection(c, g, text, -1, x, y);
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
/*     */   public static void drawStringUnderlineCharAtWithYCorrection(JComponent c, Graphics2D g, String text, int underlinedIndex, int x, int y) {
/* 269 */     float yCorrection = computeTextYCorrection(g);
/* 270 */     if (yCorrection != 0.0F) {
/* 271 */       g.translate(0.0D, yCorrection);
/* 272 */       JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
/* 273 */       g.translate(0.0D, -yCorrection);
/*     */     } else {
/* 275 */       JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Graphics2D createGraphicsTextYCorrection(Graphics2D g) {
/* 283 */     final float yCorrection = computeTextYCorrection(g);
/* 284 */     if (yCorrection == 0.0F) {
/* 285 */       return g;
/*     */     }
/* 287 */     return new Graphics2DProxy(g)
/*     */       {
/*     */         public void drawString(String str, int x, int y) {
/* 290 */           super.drawString(str, x, y + yCorrection);
/*     */         }
/*     */ 
/*     */         
/*     */         public void drawString(String str, float x, float y) {
/* 295 */           super.drawString(str, x, y + yCorrection);
/*     */         }
/*     */ 
/*     */         
/*     */         public void drawString(AttributedCharacterIterator iterator, int x, int y) {
/* 300 */           super.drawString(iterator, x, y + yCorrection);
/*     */         }
/*     */ 
/*     */         
/*     */         public void drawString(AttributedCharacterIterator iterator, float x, float y) {
/* 305 */           super.drawString(iterator, x, y + yCorrection);
/*     */         }
/*     */ 
/*     */         
/*     */         public void drawChars(char[] data, int offset, int length, int x, int y) {
/* 310 */           super.drawChars(data, offset, length, x, Math.round(y + yCorrection));
/*     */         }
/*     */ 
/*     */         
/*     */         public void drawBytes(byte[] data, int offset, int length, int x, int y) {
/* 315 */           super.drawBytes(data, offset, length, x, Math.round(y + yCorrection));
/*     */         }
/*     */ 
/*     */         
/*     */         public void drawGlyphVector(GlyphVector g, float x, float y) {
/* 320 */           super.drawGlyphVector(g, x, y + yCorrection);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static interface Painter {
/*     */     void paint(Graphics2D param1Graphics2D, int param1Int1, int param1Int2, int param1Int3, int param1Int4, double param1Double);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\HiDPIUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */