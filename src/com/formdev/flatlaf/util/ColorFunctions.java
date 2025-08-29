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
/*     */ public class ColorFunctions
/*     */ {
/*     */   public static Color lighten(Color color, float amount) {
/*  39 */     return hslIncreaseDecrease(color, amount, 2, true);
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
/*     */   public static Color darken(Color color, float amount) {
/*  53 */     return hslIncreaseDecrease(color, amount, 2, false);
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
/*     */   public static Color saturate(Color color, float amount) {
/*  65 */     return hslIncreaseDecrease(color, amount, 1, true);
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
/*     */   public static Color desaturate(Color color, float amount) {
/*  77 */     return hslIncreaseDecrease(color, amount, 1, false);
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
/*     */   public static Color spin(Color color, float angle) {
/*  89 */     return hslIncreaseDecrease(color, angle, 0, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Color hslIncreaseDecrease(Color color, float amount, int hslIndex, boolean increase) {
/*  94 */     float[] hsl = HSLColor.fromRGB(color);
/*  95 */     float alpha = color.getAlpha() / 255.0F;
/*     */ 
/*     */     
/*  98 */     float amount2 = increase ? amount : -amount;
/*  99 */     if (hslIndex == 0) {
/* 100 */       hsl[0] = (hsl[0] + amount2) % 360.0F;
/*     */     } else {
/* 102 */       hsl[hslIndex] = clamp(hsl[hslIndex] + amount2 * 100.0F);
/*     */     } 
/*     */     
/* 105 */     return HSLColor.toRGB(hsl[0], hsl[1], hsl[2], alpha);
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
/*     */   public static Color fade(Color color, float amount) {
/* 117 */     int newAlpha = Math.round(255.0F * amount);
/* 118 */     return new Color(color.getRGB() & 0xFFFFFF | newAlpha << 24, true);
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
/*     */   public static Color mix(Color color1, Color color2, float weight) {
/* 134 */     if (weight >= 1.0F)
/* 135 */       return color1; 
/* 136 */     if (weight <= 0.0F)
/* 137 */       return color2; 
/* 138 */     if (color1.equals(color2)) {
/* 139 */       return color1;
/*     */     }
/* 141 */     int r1 = color1.getRed();
/* 142 */     int g1 = color1.getGreen();
/* 143 */     int b1 = color1.getBlue();
/* 144 */     int a1 = color1.getAlpha();
/*     */     
/* 146 */     int r2 = color2.getRed();
/* 147 */     int g2 = color2.getGreen();
/* 148 */     int b2 = color2.getBlue();
/* 149 */     int a2 = color2.getAlpha();
/*     */     
/* 151 */     return new Color(
/* 152 */         Math.round(r2 + (r1 - r2) * weight), 
/* 153 */         Math.round(g2 + (g1 - g2) * weight), 
/* 154 */         Math.round(b2 + (b1 - b2) * weight), 
/* 155 */         Math.round(a2 + (a1 - a2) * weight));
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
/*     */   public static Color tint(Color color, float weight) {
/* 169 */     return mix(Color.white, color, weight);
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
/*     */   public static Color shade(Color color, float weight) {
/* 183 */     return mix(Color.black, color, weight);
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
/*     */   
/*     */   public static float luma(Color color) {
/* 202 */     float r = gammaCorrection(color.getRed() / 255.0F);
/* 203 */     float g = gammaCorrection(color.getGreen() / 255.0F);
/* 204 */     float b = gammaCorrection(color.getBlue() / 255.0F);
/* 205 */     return 0.2126F * r + 0.7152F * g + 0.0722F * b;
/*     */   }
/*     */   
/*     */   private static float gammaCorrection(float value) {
/* 209 */     return (value <= 0.03928F) ? (
/* 210 */       value / 12.92F) : 
/* 211 */       (float)Math.pow((value + 0.055D) / 1.055D, 2.4D);
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
/*     */   public static Color applyFunctions(Color color, ColorFunction... functions) {
/* 224 */     if (functions.length == 1 && functions[0] instanceof Mix) {
/* 225 */       Mix mixFunction = (Mix)functions[0];
/* 226 */       return mix(color, mixFunction.color2, mixFunction.weight / 100.0F);
/*     */     } 
/*     */ 
/*     */     
/* 230 */     float[] hsl = HSLColor.fromRGB(color);
/* 231 */     float alpha = color.getAlpha() / 255.0F;
/* 232 */     float[] hsla = { hsl[0], hsl[1], hsl[2], alpha * 100.0F };
/*     */ 
/*     */     
/* 235 */     for (ColorFunction function : functions) {
/* 236 */       function.apply(hsla);
/*     */     }
/*     */     
/* 239 */     return HSLColor.toRGB(hsla[0], hsla[1], hsla[2], hsla[3] / 100.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float clamp(float value) {
/* 246 */     return (value < 0.0F) ? 
/* 247 */       0.0F : (
/* 248 */       (value > 100.0F) ? 
/* 249 */       100.0F : 
/* 250 */       value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ColorFunction
/*     */   {
/*     */     void apply(float[] param1ArrayOffloat);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class HSLIncreaseDecrease
/*     */     implements ColorFunction
/*     */   {
/*     */     public final int hslIndex;
/*     */     
/*     */     public final boolean increase;
/*     */     
/*     */     public final float amount;
/*     */     
/*     */     public final boolean relative;
/*     */     
/*     */     public final boolean autoInverse;
/*     */ 
/*     */     
/*     */     public HSLIncreaseDecrease(int hslIndex, boolean increase, float amount, boolean relative, boolean autoInverse) {
/* 277 */       this.hslIndex = hslIndex;
/* 278 */       this.increase = increase;
/* 279 */       this.amount = amount;
/* 280 */       this.relative = relative;
/* 281 */       this.autoInverse = autoInverse;
/*     */     }
/*     */ 
/*     */     
/*     */     public void apply(float[] hsla) {
/* 286 */       float amount2 = this.increase ? this.amount : -this.amount;
/*     */       
/* 288 */       if (this.hslIndex == 0) {
/*     */         
/* 290 */         hsla[0] = (hsla[0] + amount2) % 360.0F;
/*     */         
/*     */         return;
/*     */       } 
/* 294 */       amount2 = (this.autoInverse && shouldInverse(hsla)) ? -amount2 : amount2;
/* 295 */       hsla[this.hslIndex] = ColorFunctions.clamp(this.relative ? (
/* 296 */           hsla[this.hslIndex] * (100.0F + amount2) / 100.0F) : (
/* 297 */           hsla[this.hslIndex] + amount2));
/*     */     }
/*     */     
/*     */     protected boolean shouldInverse(float[] hsla) {
/* 301 */       return this.increase ? (
/* 302 */         (hsla[this.hslIndex] > 65.0F)) : (
/* 303 */         (hsla[this.hslIndex] < 35.0F));
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*     */       String name;
/* 309 */       switch (this.hslIndex) { case 0:
/* 310 */           name = "spin"; break;
/* 311 */         case 1: name = this.increase ? "saturate" : "desaturate"; break;
/* 312 */         case 2: name = this.increase ? "lighten" : "darken"; break;
/* 313 */         case 3: name = this.increase ? "fadein" : "fadeout"; break;
/* 314 */         default: throw new IllegalArgumentException(); }
/*     */       
/* 316 */       return String.format("%s(%.0f%%%s%s)", new Object[] { name, Float.valueOf(this.amount), 
/* 317 */             this.relative ? " relative" : "", 
/* 318 */             this.autoInverse ? " autoInverse" : "" });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class HSLChange
/*     */     implements ColorFunction
/*     */   {
/*     */     public final int hslIndex;
/*     */ 
/*     */     
/*     */     public final float value;
/*     */ 
/*     */ 
/*     */     
/*     */     public HSLChange(int hslIndex, float value) {
/* 336 */       this.hslIndex = hslIndex;
/* 337 */       this.value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public void apply(float[] hsla) {
/* 342 */       hsla[this.hslIndex] = (this.hslIndex == 0) ? (
/* 343 */         this.value % 360.0F) : 
/* 344 */         ColorFunctions.clamp(this.value);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*     */       String name;
/* 350 */       switch (this.hslIndex) { case 0:
/* 351 */           name = "changeHue"; break;
/* 352 */         case 1: name = "changeSaturation"; break;
/* 353 */         case 2: name = "changeLightness"; break;
/* 354 */         case 3: name = "changeAlpha"; break;
/* 355 */         default: throw new IllegalArgumentException(); }
/*     */       
/* 357 */       return String.format("%s(%.0f%s)", new Object[] { name, Float.valueOf(this.value), (this.hslIndex == 0) ? "" : "%" });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Fade
/*     */     implements ColorFunction
/*     */   {
/*     */     public final float amount;
/*     */ 
/*     */ 
/*     */     
/*     */     public Fade(float amount) {
/* 372 */       this.amount = amount;
/*     */     }
/*     */ 
/*     */     
/*     */     public void apply(float[] hsla) {
/* 377 */       hsla[3] = ColorFunctions.clamp(this.amount);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 382 */       return String.format("fade(%.0f%%)", new Object[] { Float.valueOf(this.amount) });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Mix
/*     */     implements ColorFunction
/*     */   {
/*     */     public final Color color2;
/*     */ 
/*     */     
/*     */     public final float weight;
/*     */ 
/*     */ 
/*     */     
/*     */     public Mix(Color color2, float weight) {
/* 400 */       this.color2 = color2;
/* 401 */       this.weight = weight;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void apply(float[] hsla) {
/* 407 */       Color color1 = HSLColor.toRGB(hsla[0], hsla[1], hsla[2], hsla[3] / 100.0F);
/*     */ 
/*     */       
/* 410 */       Color color = ColorFunctions.mix(color1, this.color2, this.weight / 100.0F);
/*     */ 
/*     */       
/* 413 */       float[] hsl = HSLColor.fromRGB(color);
/* 414 */       System.arraycopy(hsl, 0, hsla, 0, hsl.length);
/* 415 */       hsla[3] = color.getAlpha() / 255.0F * 100.0F;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 420 */       return String.format("mix(#%08x,%.0f%%)", new Object[] { Integer.valueOf(this.color2.getRGB()), Float.valueOf(this.weight) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\ColorFunctions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */