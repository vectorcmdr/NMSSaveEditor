/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.awt.Color;
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
/*     */ public class HSLColor
/*     */ {
/*     */   private final Color rgb;
/*     */   private final float[] hsl;
/*     */   private final float alpha;
/*     */   
/*     */   public HSLColor(Color rgb) {
/*  45 */     this.rgb = rgb;
/*  46 */     this.hsl = fromRGB(rgb);
/*  47 */     this.alpha = rgb.getAlpha() / 255.0F;
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
/*     */   public HSLColor(float h, float s, float l) {
/*  60 */     this(h, s, l, 1.0F);
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
/*     */   public HSLColor(float h, float s, float l, float alpha) {
/*  73 */     this.hsl = new float[] { h, s, l };
/*  74 */     this.alpha = alpha;
/*  75 */     this.rgb = toRGB(this.hsl, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HSLColor(float[] hsl) {
/*  86 */     this(hsl, 1.0F);
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
/*     */   public HSLColor(float[] hsl, float alpha) {
/*  98 */     this.hsl = hsl;
/*  99 */     this.alpha = alpha;
/* 100 */     this.rgb = toRGB(hsl, alpha);
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
/*     */   public Color adjustHue(float degrees) {
/* 112 */     return toRGB(degrees, this.hsl[1], this.hsl[2], this.alpha);
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
/*     */   public Color adjustLuminance(float percent) {
/* 124 */     return toRGB(this.hsl[0], this.hsl[1], percent, this.alpha);
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
/*     */   public Color adjustSaturation(float percent) {
/* 136 */     return toRGB(this.hsl[0], percent, this.hsl[2], this.alpha);
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
/*     */   public Color adjustShade(float percent) {
/* 149 */     float multiplier = (100.0F - percent) / 100.0F;
/* 150 */     float l = Math.max(0.0F, this.hsl[2] * multiplier);
/*     */     
/* 152 */     return toRGB(this.hsl[0], this.hsl[1], l, this.alpha);
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
/*     */   public Color adjustTone(float percent) {
/* 165 */     float multiplier = (100.0F + percent) / 100.0F;
/* 166 */     float l = Math.min(100.0F, this.hsl[2] * multiplier);
/*     */     
/* 168 */     return toRGB(this.hsl[0], this.hsl[1], l, this.alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAlpha() {
/* 178 */     return this.alpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getComplementary() {
/* 189 */     float hue = (this.hsl[0] + 180.0F) % 360.0F;
/* 190 */     return toRGB(hue, this.hsl[1], this.hsl[2]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHue() {
/* 200 */     return this.hsl[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getHSL() {
/* 210 */     return this.hsl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLuminance() {
/* 220 */     return this.hsl[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getRGB() {
/* 230 */     return this.rgb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSaturation() {
/* 240 */     return this.hsl[1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 246 */     String toString = "HSLColor[h=" + this.hsl[0] + ",s=" + this.hsl[1] + ",l=" + this.hsl[2] + ",alpha=" + this.alpha + "]";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 252 */     return toString;
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
/*     */   public static float[] fromRGB(Color color) {
/* 264 */     float s, rgb[] = color.getRGBColorComponents(null);
/* 265 */     float r = rgb[0];
/* 266 */     float g = rgb[1];
/* 267 */     float b = rgb[2];
/*     */ 
/*     */ 
/*     */     
/* 271 */     float min = Math.min(r, Math.min(g, b));
/* 272 */     float max = Math.max(r, Math.max(g, b));
/*     */ 
/*     */ 
/*     */     
/* 276 */     float h = 0.0F;
/*     */     
/* 278 */     if (max == min) {
/* 279 */       h = 0.0F;
/* 280 */     } else if (max == r) {
/* 281 */       h = (60.0F * (g - b) / (max - min) + 360.0F) % 360.0F;
/* 282 */     } else if (max == g) {
/* 283 */       h = 60.0F * (b - r) / (max - min) + 120.0F;
/* 284 */     } else if (max == b) {
/* 285 */       h = 60.0F * (r - g) / (max - min) + 240.0F;
/*     */     } 
/*     */ 
/*     */     
/* 289 */     float l = (max + min) / 2.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     if (max == min) {
/* 297 */       s = 0.0F;
/* 298 */     } else if (l <= 0.5F) {
/* 299 */       s = (max - min) / (max + min);
/*     */     } else {
/* 301 */       s = (max - min) / (2.0F - max - min);
/*     */     } 
/*     */     
/* 304 */     return new float[] { h, s * 100.0F, l * 100.0F };
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
/*     */   public static Color toRGB(float[] hsl) {
/* 319 */     return toRGB(hsl, 1.0F);
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
/*     */   public static Color toRGB(float[] hsl, float alpha) {
/* 335 */     return toRGB(hsl[0], hsl[1], hsl[2], alpha);
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
/*     */   public static Color toRGB(float h, float s, float l) {
/* 349 */     return toRGB(h, s, l, 1.0F);
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
/*     */   public static Color toRGB(float h, float s, float l, float alpha) {
/*     */     float q;
/* 364 */     if (s < 0.0F || s > 100.0F) {
/*     */       
/* 366 */       String message = "Color parameter outside of expected range - Saturation";
/* 367 */       throw new IllegalArgumentException(message);
/*     */     } 
/*     */     
/* 370 */     if (l < 0.0F || l > 100.0F) {
/*     */       
/* 372 */       String message = "Color parameter outside of expected range - Luminance";
/* 373 */       throw new IllegalArgumentException(message);
/*     */     } 
/*     */     
/* 376 */     if (alpha < 0.0F || alpha > 1.0F) {
/*     */       
/* 378 */       String message = "Color parameter outside of expected range - Alpha";
/* 379 */       throw new IllegalArgumentException(message);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 384 */     h %= 360.0F;
/* 385 */     h /= 360.0F;
/* 386 */     s /= 100.0F;
/* 387 */     l /= 100.0F;
/*     */ 
/*     */ 
/*     */     
/* 391 */     if (l < 0.5D) {
/* 392 */       q = l * (1.0F + s);
/*     */     } else {
/* 394 */       q = l + s - s * l;
/*     */     } 
/* 396 */     float p = 2.0F * l - q;
/*     */     
/* 398 */     float r = Math.max(0.0F, HueToRGB(p, q, h + 0.33333334F));
/* 399 */     float g = Math.max(0.0F, HueToRGB(p, q, h));
/* 400 */     float b = Math.max(0.0F, HueToRGB(p, q, h - 0.33333334F));
/*     */     
/* 402 */     r = Math.min(r, 1.0F);
/* 403 */     g = Math.min(g, 1.0F);
/* 404 */     b = Math.min(b, 1.0F);
/*     */     
/* 406 */     return new Color(r, g, b, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   private static float HueToRGB(float p, float q, float h) {
/* 411 */     if (h < 0.0F) h++;
/*     */     
/* 413 */     if (h > 1.0F) h--;
/*     */     
/* 415 */     if (6.0F * h < 1.0F)
/*     */     {
/* 417 */       return p + (q - p) * 6.0F * h;
/*     */     }
/*     */     
/* 420 */     if (2.0F * h < 1.0F)
/*     */     {
/* 422 */       return q;
/*     */     }
/*     */     
/* 425 */     if (3.0F * h < 2.0F)
/*     */     {
/* 427 */       return p + (q - p) * 6.0F * (0.6666667F - h);
/*     */     }
/*     */     
/* 430 */     return p;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\HSLColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */