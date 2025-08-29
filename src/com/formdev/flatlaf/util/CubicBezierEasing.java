/*     */ package com.formdev.flatlaf.util;
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
/*     */ public class CubicBezierEasing
/*     */   implements Animator.Interpolator
/*     */ {
/*  32 */   public static final CubicBezierEasing STANDARD_EASING = new CubicBezierEasing(0.4F, 0.0F, 0.2F, 1.0F);
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final CubicBezierEasing EASE = new CubicBezierEasing(0.25F, 0.1F, 0.25F, 1.0F);
/*  37 */   public static final CubicBezierEasing EASE_IN = new CubicBezierEasing(0.42F, 0.0F, 1.0F, 1.0F);
/*  38 */   public static final CubicBezierEasing EASE_IN_OUT = new CubicBezierEasing(0.42F, 0.0F, 0.58F, 1.0F);
/*  39 */   public static final CubicBezierEasing EASE_OUT = new CubicBezierEasing(0.0F, 0.0F, 0.58F, 1.0F);
/*     */ 
/*     */   
/*     */   private final float x1;
/*     */ 
/*     */   
/*     */   private final float y1;
/*     */ 
/*     */   
/*     */   private final float x2;
/*     */ 
/*     */   
/*     */   private final float y2;
/*     */ 
/*     */ 
/*     */   
/*     */   public CubicBezierEasing(float x1, float y1, float x2, float y2) {
/*  56 */     if (x1 < 0.0F || x1 > 1.0F || y1 < 0.0F || y1 > 1.0F || x2 < 0.0F || x2 > 1.0F || y2 < 0.0F || y2 > 1.0F)
/*     */     {
/*  58 */       throw new IllegalArgumentException("control points must be in range [0, 1]");
/*     */     }
/*  60 */     this.x1 = x1;
/*  61 */     this.y1 = y1;
/*  62 */     this.x2 = x2;
/*  63 */     this.y2 = y2;
/*     */   }
/*     */ 
/*     */   
/*     */   public float interpolate(float fraction) {
/*  68 */     if (fraction <= 0.0F || fraction >= 1.0F) {
/*  69 */       return fraction;
/*     */     }
/*     */     
/*  72 */     float low = 0.0F;
/*  73 */     float high = 1.0F;
/*     */     while (true) {
/*  75 */       float mid = (low + high) / 2.0F;
/*  76 */       float estimate = cubicBezier(mid, this.x1, this.x2);
/*  77 */       if (Math.abs(fraction - estimate) < 5.0E-4F)
/*  78 */         return cubicBezier(mid, this.y1, this.y2); 
/*  79 */       if (estimate < fraction) {
/*  80 */         low = mid; continue;
/*     */       } 
/*  82 */       high = mid;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float cubicBezier(float t, float xy1, float xy2) {
/* 106 */     float invT = 1.0F - t;
/* 107 */     float b1 = 3.0F * t * invT * invT;
/* 108 */     float b2 = 3.0F * t * t * invT;
/* 109 */     float b3 = t * t * t;
/* 110 */     return b1 * xy1 + b2 * xy2 + b3;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\CubicBezierEasing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */