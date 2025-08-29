/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RadialGradientPaint;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Map;
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
/*     */ public class FlatDropShadowBorder
/*     */   extends FlatEmptyBorder
/*     */   implements FlatStylingSupport.StyleableBorder
/*     */ {
/*     */   @Styleable
/*     */   protected Color shadowColor;
/*     */   @Styleable
/*     */   protected Insets shadowInsets;
/*     */   @Styleable
/*     */   protected float shadowOpacity;
/*     */   private int shadowSize;
/*     */   private Image shadowImage;
/*     */   private Color lastShadowColor;
/*     */   private float lastShadowOpacity;
/*     */   private int lastShadowSize;
/*     */   private double lastSystemScaleFactor;
/*     */   private float lastUserScaleFactor;
/*     */   
/*     */   public FlatDropShadowBorder() {
/*  61 */     this(null);
/*     */   }
/*     */   
/*     */   public FlatDropShadowBorder(Color shadowColor) {
/*  65 */     this(shadowColor, 4, 0.5F);
/*     */   }
/*     */   
/*     */   public FlatDropShadowBorder(Color shadowColor, int shadowSize, float shadowOpacity) {
/*  69 */     this(shadowColor, new Insets(-shadowSize, -shadowSize, shadowSize, shadowSize), shadowOpacity);
/*     */   }
/*     */   
/*     */   public FlatDropShadowBorder(Color shadowColor, Insets shadowInsets, float shadowOpacity) {
/*  73 */     super(nonNegativeInsets(shadowInsets));
/*     */     
/*  75 */     this.shadowColor = shadowColor;
/*  76 */     this.shadowInsets = shadowInsets;
/*  77 */     this.shadowOpacity = shadowOpacity;
/*     */     
/*  79 */     this.shadowSize = maxInset(shadowInsets);
/*     */   }
/*     */   
/*     */   private static Insets nonNegativeInsets(Insets shadowInsets) {
/*  83 */     return new Insets(Math.max(shadowInsets.top, 0), Math.max(shadowInsets.left, 0), 
/*  84 */         Math.max(shadowInsets.bottom, 0), Math.max(shadowInsets.right, 0));
/*     */   }
/*     */   
/*     */   private int maxInset(Insets shadowInsets) {
/*  88 */     return Math.max(
/*  89 */         Math.max(shadowInsets.left, shadowInsets.right), 
/*  90 */         Math.max(shadowInsets.top, shadowInsets.bottom));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object applyStyleProperty(String key, Object value) {
/*  96 */     Object oldValue = FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*  97 */     if (key.equals("shadowInsets")) {
/*  98 */       applyStyleProperty(nonNegativeInsets(this.shadowInsets));
/*  99 */       this.shadowSize = maxInset(this.shadowInsets);
/*     */     } 
/* 101 */     return oldValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/* 107 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/* 113 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 118 */     if (this.shadowSize <= 0) {
/*     */       return;
/*     */     }
/* 121 */     HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, width, height, this::paintImpl);
/*     */   }
/*     */   
/*     */   private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 125 */     Color shadowColor = (this.shadowColor != null) ? this.shadowColor : g.getColor();
/* 126 */     int shadowSize = scale(this.shadowSize, scaleFactor);
/*     */ 
/*     */     
/* 129 */     float userScaleFactor = UIScale.getUserScaleFactor();
/* 130 */     if (this.shadowImage == null || 
/* 131 */       !shadowColor.equals(this.lastShadowColor) || this.lastShadowOpacity != this.shadowOpacity || this.lastShadowSize != shadowSize || this.lastSystemScaleFactor != scaleFactor || this.lastUserScaleFactor != userScaleFactor) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       this.shadowImage = createShadowImage(shadowColor, shadowSize, this.shadowOpacity, (float)(scaleFactor * userScaleFactor));
/*     */       
/* 139 */       this.lastShadowColor = shadowColor;
/* 140 */       this.lastShadowOpacity = this.shadowOpacity;
/* 141 */       this.lastShadowSize = shadowSize;
/* 142 */       this.lastSystemScaleFactor = scaleFactor;
/* 143 */       this.lastUserScaleFactor = userScaleFactor;
/*     */     } 
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
/* 157 */     int left = scale(this.shadowInsets.left, scaleFactor);
/* 158 */     int right = scale(this.shadowInsets.right, scaleFactor);
/* 159 */     int top = scale(this.shadowInsets.top, scaleFactor);
/* 160 */     int bottom = scale(this.shadowInsets.bottom, scaleFactor);
/*     */ 
/*     */     
/* 163 */     int x1o = x - Math.min(left, 0);
/* 164 */     int y1o = y - Math.min(top, 0);
/* 165 */     int x2o = x + width + Math.min(right, 0);
/* 166 */     int y2o = y + height + Math.min(bottom, 0);
/*     */ 
/*     */     
/* 169 */     int x1i = x1o + shadowSize;
/* 170 */     int y1i = y1o + shadowSize;
/* 171 */     int x2i = x2o - shadowSize;
/* 172 */     int y2i = y2o - shadowSize;
/*     */     
/* 174 */     int wh = shadowSize * 2 - 1;
/* 175 */     int center = shadowSize - 1;
/*     */ 
/*     */     
/* 178 */     if (left > 0 || top > 0) {
/* 179 */       g.drawImage(this.shadowImage, x1o, y1o, x1i, y1i, 0, 0, center, center, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 184 */     if (top > 0) {
/* 185 */       g.drawImage(this.shadowImage, x1i, y1o, x2i, y1i, center, 0, center + 1, center, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 190 */     if (right > 0 || top > 0) {
/* 191 */       g.drawImage(this.shadowImage, x2i, y1o, x2o, y1i, center, 0, wh, center, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (left > 0) {
/* 197 */       g.drawImage(this.shadowImage, x1o, y1i, x1i, y2i, 0, center, center, center + 1, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 202 */     if (right > 0) {
/* 203 */       g.drawImage(this.shadowImage, x2i, y1i, x2o, y2i, center, center, wh, center + 1, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 208 */     if (left > 0 || bottom > 0) {
/* 209 */       g.drawImage(this.shadowImage, x1o, y2i, x1i, y2o, 0, center, center, wh, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 214 */     if (bottom > 0) {
/* 215 */       g.drawImage(this.shadowImage, x1i, y2i, x2i, y2o, center, center, center + 1, wh, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 220 */     if (right > 0 || bottom > 0) {
/* 221 */       g.drawImage(this.shadowImage, x2i, y2i, x2o, y2o, center, center, wh, wh, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int scale(int value, double scaleFactor) {
/* 227 */     return (int)Math.ceil(UIScale.scale(value) * scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage createShadowImage(Color shadowColor, int shadowSize, float shadowOpacity, float scaleFactor) {
/* 233 */     int shadowRGB = shadowColor.getRGB() & 0xFFFFFF;
/* 234 */     int shadowAlpha = (int)(255.0F * shadowOpacity);
/* 235 */     Color startColor = new Color(shadowRGB | (shadowAlpha & 0xFF) << 24, true);
/* 236 */     Color midColor = new Color(shadowRGB | (shadowAlpha / 2 & 0xFF) << 24, true);
/* 237 */     Color endColor = new Color(shadowRGB, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     int wh = shadowSize * 2 - 1;
/* 246 */     int center = shadowSize - 1;
/*     */     
/* 248 */     RadialGradientPaint p = new RadialGradientPaint(center, center, shadowSize - 0.75F * scaleFactor, new float[] { 0.0F, 0.35F, 1.0F }, new Color[] { startColor, midColor, endColor });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     BufferedImage image = new BufferedImage(wh, wh, 2);
/*     */     
/* 255 */     Graphics2D g = image.createGraphics();
/*     */     try {
/* 257 */       g.setPaint(p);
/* 258 */       g.fillRect(0, 0, wh, wh);
/*     */     } finally {
/* 260 */       g.dispose();
/*     */     } 
/*     */     
/* 263 */     return image;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatDropShadowBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */