/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.Icon;
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
/*     */ public interface AnimatedIcon
/*     */   extends Icon
/*     */ {
/*     */   default void paintIcon(Component c, Graphics g, int x, int y) {
/*  72 */     AnimationSupport.paintIcon(this, c, g, x, y);
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
/*     */   void paintIconAnimated(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2, float paramFloat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float getValue(Component paramComponent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean isAnimationEnabled() {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getAnimationDuration() {
/* 109 */     return 150;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getAnimationResolution() {
/* 117 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Animator.Interpolator getAnimationInterpolator() {
/* 125 */     return CubicBezierEasing.STANDARD_EASING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Object getClientPropertyKey() {
/* 132 */     return getClass();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class AnimationSupport
/*     */   {
/*     */     private float startValue;
/*     */     
/*     */     private float targetValue;
/*     */     
/*     */     private float animatedValue;
/*     */     
/*     */     private float fraction;
/*     */     
/*     */     private Animator animator;
/*     */     
/*     */     private int x;
/*     */     
/*     */     private int y;
/*     */ 
/*     */     
/*     */     public static void paintIcon(AnimatedIcon icon, Component c, Graphics g, int x, int y) {
/* 154 */       if (!isAnimationEnabled(icon, c)) {
/*     */ 
/*     */ 
/*     */         
/* 158 */         paintIconImpl(icon, c, g, x, y, null);
/*     */         
/*     */         return;
/*     */       } 
/* 162 */       JComponent jc = (JComponent)c;
/* 163 */       Object key = icon.getClientPropertyKey();
/* 164 */       AnimationSupport as = (AnimationSupport)jc.getClientProperty(key);
/* 165 */       if (as == null) {
/*     */         
/* 167 */         as = new AnimationSupport();
/* 168 */         as.startValue = as.targetValue = as.animatedValue = icon.getValue(c);
/* 169 */         as.x = x;
/* 170 */         as.y = y;
/* 171 */         jc.putClientProperty(key, as);
/*     */       } else {
/*     */         
/* 174 */         float value = icon.getValue(c);
/*     */         
/* 176 */         if (value != as.targetValue) {
/*     */ 
/*     */           
/* 179 */           if (as.animator == null) {
/*     */             
/* 181 */             AnimationSupport as2 = as;
/* 182 */             as.animator = new Animator(icon.getAnimationDuration(), fraction -> {
/*     */                   if (!c.isDisplayable()) {
/*     */                     as2.animator.stop();
/*     */                     
/*     */                     return;
/*     */                   } 
/*     */                   
/*     */                   as2.animatedValue = as2.startValue + (as2.targetValue - as2.startValue) * fraction;
/*     */                   
/*     */                   as2.fraction = fraction;
/*     */                   
/*     */                   c.repaint(as2.x, as2.y, icon.getIconWidth(), icon.getIconHeight());
/*     */                 }() -> {
/*     */                   as2.startValue = as2.animatedValue = as2.targetValue;
/*     */                   
/*     */                   as2.animator = null;
/*     */                 });
/*     */           } 
/*     */           
/* 201 */           if (as.animator.isRunning()) {
/*     */ 
/*     */             
/* 204 */             as.animator.cancel();
/* 205 */             int duration2 = (int)(icon.getAnimationDuration() * as.fraction);
/* 206 */             if (duration2 > 0)
/* 207 */               as.animator.setDuration(duration2); 
/* 208 */             as.startValue = as.animatedValue;
/*     */           } else {
/*     */             
/* 211 */             as.animator.setDuration(icon.getAnimationDuration());
/* 212 */             as.animator.setResolution(icon.getAnimationResolution());
/* 213 */             as.animator.setInterpolator(icon.getAnimationInterpolator());
/*     */             
/* 215 */             as.animatedValue = as.startValue;
/*     */           } 
/*     */           
/* 218 */           as.targetValue = value;
/* 219 */           as.animator.start();
/*     */         } 
/*     */         
/* 222 */         as.x = x;
/* 223 */         as.y = y;
/*     */       } 
/*     */       
/* 226 */       paintIconImpl(icon, c, g, x, y, as);
/*     */     }
/*     */     
/*     */     private static void paintIconImpl(AnimatedIcon icon, Component c, Graphics g, int x, int y, AnimationSupport as) {
/* 230 */       float value = (as != null) ? as.animatedValue : icon.getValue(c);
/* 231 */       icon.paintIconAnimated(c, g, x, y, value);
/*     */     }
/*     */     
/*     */     private static boolean isAnimationEnabled(AnimatedIcon icon, Component c) {
/* 235 */       return (Animator.useAnimation() && icon.isAnimationEnabled() && c instanceof JComponent);
/*     */     }
/*     */     
/*     */     public static void saveIconLocation(AnimatedIcon icon, Component c, int x, int y) {
/* 239 */       if (!isAnimationEnabled(icon, c)) {
/*     */         return;
/*     */       }
/* 242 */       AnimationSupport as = (AnimationSupport)((JComponent)c).getClientProperty(icon.getClientPropertyKey());
/* 243 */       if (as != null) {
/* 244 */         as.x = x;
/* 245 */         as.y = y;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\AnimatedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */