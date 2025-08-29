/*    */ package com.formdev.flatlaf.util;
/*    */ 
/*    */ import java.awt.image.RGBImageFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GrayFilter
/*    */   extends RGBImageFilter
/*    */ {
/*    */   private final float brightness;
/*    */   private final float contrast;
/*    */   private final int alpha;
/*    */   private final int origContrast;
/*    */   private final int origBrightness;
/*    */   
/*    */   public static GrayFilter createDisabledIconFilter(boolean dark) {
/* 23 */     return dark ? 
/* 24 */       new GrayFilter(-20, -70, 100) : 
/* 25 */       new GrayFilter(25, -25, 100);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GrayFilter(int brightness, int contrast, int alpha) {
/* 34 */     this.origBrightness = Math.max(-100, Math.min(100, brightness));
/* 35 */     this.origContrast = Math.max(-100, Math.min(100, contrast));
/* 36 */     this.alpha = Math.max(0, Math.min(100, alpha));
/*    */     
/* 38 */     this.brightness = (float)(Math.pow(this.origBrightness, 3.0D) / 10000.0D);
/* 39 */     this.contrast = this.origContrast / 100.0F;
/*    */     
/* 41 */     this.canFilterIndexColorModel = true;
/*    */   }
/*    */   
/*    */   public GrayFilter() {
/* 45 */     this(0, 0, 100);
/*    */   }
/*    */   
/*    */   public int getBrightness() {
/* 49 */     return this.origBrightness;
/*    */   }
/*    */   
/*    */   public int getContrast() {
/* 53 */     return this.origContrast;
/*    */   }
/*    */   
/*    */   public int getAlpha() {
/* 57 */     return this.alpha;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int filterRGB(int x, int y, int rgb) {
/* 63 */     int gray = (int)(0.3D * (rgb >> 16 & 0xFF) + 0.59D * (rgb >> 8 & 0xFF) + 0.11D * (rgb & 0xFF));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     if (this.brightness >= 0.0F) {
/* 69 */       gray = (int)((gray + this.brightness * 255.0F) / (1.0F + this.brightness));
/*    */     } else {
/* 71 */       gray = (int)(gray / (1.0F - this.brightness));
/*    */     } 
/* 73 */     if (this.contrast >= 0.0F)
/* 74 */     { if (gray >= 127) {
/* 75 */         gray = (int)(gray + (255 - gray) * this.contrast);
/*    */       } else {
/* 77 */         gray = (int)(gray - gray * this.contrast);
/*    */       }  }
/* 79 */     else { gray = (int)(127.0F + (gray - 127) * (this.contrast + 1.0F)); }
/*    */ 
/*    */ 
/*    */     
/* 83 */     int a = (this.alpha != 100) ? ((rgb >> 24 & 0xFF) * this.alpha / 100 << 24) : (rgb & 0xFF000000);
/*    */     
/* 85 */     return a | gray << 16 | gray << 8 | gray;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\GrayFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */