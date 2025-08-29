/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.Graphics2DProxy;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicSliderUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatSliderUI
/*     */   extends BasicSliderUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected int trackWidth;
/*     */   @Styleable
/*     */   protected Dimension thumbSize;
/*     */   @Styleable
/*     */   protected int focusWidth;
/*     */   @Styleable
/*     */   protected float thumbBorderWidth;
/*     */   @Styleable
/*     */   protected Color trackValueColor;
/*     */   @Styleable
/*     */   protected Color trackColor;
/*     */   @Styleable
/*     */   protected Color thumbColor;
/*     */   @Styleable
/*     */   protected Color thumbBorderColor;
/*     */   protected Color focusBaseColor;
/*     */   @Styleable
/*     */   protected Color focusedColor;
/*     */   @Styleable
/*     */   protected Color focusedThumbBorderColor;
/*     */   @Styleable
/*     */   protected Color hoverThumbColor;
/*     */   @Styleable
/*     */   protected Color pressedThumbColor;
/*     */   @Styleable
/*     */   protected Color disabledTrackColor;
/*     */   @Styleable
/*     */   protected Color disabledThumbColor;
/*     */   @Styleable
/*     */   protected Color disabledThumbBorderColor;
/*     */   @Styleable
/*     */   protected Color tickColor;
/*     */   private Color defaultBackground;
/*     */   private Color defaultForeground;
/*     */   protected boolean thumbHover;
/*     */   protected boolean thumbPressed;
/*     */   private Object[] oldRenderingHints;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 117 */     return new FlatSliderUI();
/*     */   }
/*     */   
/*     */   public FlatSliderUI() {
/* 121 */     super(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 126 */     super.installUI(c);
/*     */     
/* 128 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(JSlider slider) {
/* 133 */     super.installDefaults(slider);
/*     */     
/* 135 */     LookAndFeel.installProperty(slider, "opaque", Boolean.valueOf(false));
/*     */     
/* 137 */     this.trackWidth = UIManager.getInt("Slider.trackWidth");
/* 138 */     this.thumbSize = UIManager.getDimension("Slider.thumbSize");
/* 139 */     if (this.thumbSize == null) {
/*     */       
/* 141 */       int thumbWidth = UIManager.getInt("Slider.thumbWidth");
/* 142 */       this.thumbSize = new Dimension(thumbWidth, thumbWidth);
/*     */     } 
/* 144 */     this.focusWidth = FlatUIUtils.getUIInt("Slider.focusWidth", 4);
/* 145 */     this.thumbBorderWidth = FlatUIUtils.getUIFloat("Slider.thumbBorderWidth", 1.0F);
/*     */     
/* 147 */     this.trackValueColor = FlatUIUtils.getUIColor("Slider.trackValueColor", "Slider.thumbColor");
/* 148 */     this.trackColor = UIManager.getColor("Slider.trackColor");
/* 149 */     this.thumbColor = UIManager.getColor("Slider.thumbColor");
/* 150 */     this.thumbBorderColor = UIManager.getColor("Slider.thumbBorderColor");
/* 151 */     this.focusBaseColor = UIManager.getColor("Component.focusColor");
/* 152 */     this.focusedColor = FlatUIUtils.getUIColor("Slider.focusedColor", this.focusBaseColor);
/* 153 */     this.focusedThumbBorderColor = FlatUIUtils.getUIColor("Slider.focusedThumbBorderColor", "Component.focusedBorderColor");
/* 154 */     this.hoverThumbColor = UIManager.getColor("Slider.hoverThumbColor");
/* 155 */     this.pressedThumbColor = UIManager.getColor("Slider.pressedThumbColor");
/* 156 */     this.disabledTrackColor = UIManager.getColor("Slider.disabledTrackColor");
/* 157 */     this.disabledThumbColor = UIManager.getColor("Slider.disabledThumbColor");
/* 158 */     this.disabledThumbBorderColor = FlatUIUtils.getUIColor("Slider.disabledThumbBorderColor", "Component.disabledBorderColor");
/* 159 */     this.tickColor = FlatUIUtils.getUIColor("Slider.tickColor", Color.BLACK);
/*     */     
/* 161 */     this.defaultBackground = UIManager.getColor("Slider.background");
/* 162 */     this.defaultForeground = UIManager.getColor("Slider.foreground");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(JSlider slider) {
/* 167 */     super.uninstallDefaults(slider);
/*     */     
/* 169 */     this.trackValueColor = null;
/* 170 */     this.trackColor = null;
/* 171 */     this.thumbColor = null;
/* 172 */     this.thumbBorderColor = null;
/* 173 */     this.focusBaseColor = null;
/* 174 */     this.focusedColor = null;
/* 175 */     this.focusedThumbBorderColor = null;
/* 176 */     this.hoverThumbColor = null;
/* 177 */     this.pressedThumbColor = null;
/* 178 */     this.disabledTrackColor = null;
/* 179 */     this.disabledThumbColor = null;
/* 180 */     this.disabledThumbBorderColor = null;
/* 181 */     this.tickColor = null;
/*     */     
/* 183 */     this.defaultBackground = null;
/* 184 */     this.defaultForeground = null;
/*     */     
/* 186 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BasicSliderUI.TrackListener createTrackListener(JSlider slider) {
/* 191 */     return new FlatTrackListener();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener(JSlider slider) {
/* 196 */     return FlatStylingSupport.createPropertyChangeListener(slider, this::installStyle, super
/* 197 */         .createPropertyChangeListener(slider));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 203 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.slider, "Slider"));
/* 204 */     } catch (RuntimeException ex) {
/* 205 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 211 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 216 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.slider, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 222 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 228 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBaseline(JComponent c, int width, int height) {
/* 233 */     if (c == null)
/* 234 */       throw new NullPointerException(); 
/* 235 */     if (width < 0 || height < 0) {
/* 236 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 239 */     if (this.slider.getOrientation() == 1) {
/* 240 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 244 */     Font font = UIManager.getFont("defaultFont");
/* 245 */     if (font == null)
/* 246 */       font = this.slider.getFont(); 
/* 247 */     FontMetrics fm = this.slider.getFontMetrics(font);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 252 */     Insets insets = this.slider.getInsets();
/* 253 */     int thumbHeight = (getThumbSize()).height;
/* 254 */     int contentHeight = height - insets.top - insets.bottom - this.focusInsets.top - this.focusInsets.bottom;
/*     */ 
/*     */     
/* 257 */     int centerSpacing = thumbHeight + (this.slider.getPaintTicks() ? getTickLength() : 0) + (this.slider.getPaintLabels() ? getHeightOfTallestLabel() : 0);
/* 258 */     int trackY = insets.top + this.focusInsets.top + (contentHeight - centerSpacing - 1) / 2;
/* 259 */     int trackHeight = thumbHeight;
/*     */ 
/*     */     
/* 262 */     return trackY + Math.round((trackHeight - fm.getHeight()) / 2.0F) + fm.getAscent() - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredHorizontalSize() {
/* 267 */     return UIScale.scale(super.getPreferredHorizontalSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredVerticalSize() {
/* 272 */     return UIScale.scale(super.getPreferredVerticalSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumHorizontalSize() {
/* 277 */     return UIScale.scale(super.getMinimumHorizontalSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumVerticalSize() {
/* 282 */     return UIScale.scale(super.getMinimumVerticalSize());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getTickLength() {
/* 287 */     return UIScale.scale(super.getTickLength());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getThumbSize() {
/* 292 */     return calcThumbSize(this.slider, this.thumbSize, this.focusWidth);
/*     */   }
/*     */   
/*     */   public static Dimension calcThumbSize(JSlider slider, Dimension thumbSize, int focusWidth) {
/* 296 */     int fw = UIScale.scale(focusWidth);
/* 297 */     int w = UIScale.scale(thumbSize.width) + fw + fw;
/* 298 */     int h = UIScale.scale(thumbSize.height) + fw + fw;
/* 299 */     return (slider.getOrientation() == 0) ? 
/* 300 */       new Dimension(w, h) : 
/* 301 */       new Dimension(h, w);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 306 */     this.oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     super.paint(g, c);
/*     */     
/* 327 */     FlatUIUtils.resetRenderingHints(g, this.oldRenderingHints);
/* 328 */     this.oldRenderingHints = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintLabels(Graphics g) {
/* 333 */     FlatUIUtils.runWithoutRenderingHints(g, this.oldRenderingHints, () -> super.paintLabels(g));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintFocus(Graphics g) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintTrack(Graphics g) {
/*     */     RoundRectangle2D track;
/* 345 */     boolean enabled = this.slider.isEnabled();
/* 346 */     float tw = UIScale.scale(this.trackWidth);
/* 347 */     float arc = tw;
/*     */     
/* 349 */     RoundRectangle2D coloredTrack = null;
/*     */     
/* 351 */     if (this.slider.getOrientation() == 0)
/* 352 */     { float y = this.trackRect.y + (this.trackRect.height - tw) / 2.0F;
/* 353 */       if (enabled && isRoundThumb()) {
/* 354 */         if (this.slider.getComponentOrientation().isLeftToRight()) {
/* 355 */           int cw = this.thumbRect.x + this.thumbRect.width / 2 - this.trackRect.x;
/* 356 */           coloredTrack = new RoundRectangle2D.Float(this.trackRect.x, y, cw, tw, arc, arc);
/* 357 */           track = new RoundRectangle2D.Float((this.trackRect.x + cw), y, (this.trackRect.width - cw), tw, arc, arc);
/*     */         } else {
/* 359 */           int cw = this.trackRect.x + this.trackRect.width - this.thumbRect.x - this.thumbRect.width / 2;
/* 360 */           coloredTrack = new RoundRectangle2D.Float((this.trackRect.x + this.trackRect.width - cw), y, cw, tw, arc, arc);
/* 361 */           track = new RoundRectangle2D.Float(this.trackRect.x, y, (this.trackRect.width - cw), tw, arc, arc);
/*     */         } 
/*     */       } else {
/* 364 */         track = new RoundRectangle2D.Float(this.trackRect.x, y, this.trackRect.width, tw, arc, arc);
/*     */       }  }
/* 366 */     else { float x = this.trackRect.x + (this.trackRect.width - tw) / 2.0F;
/* 367 */       if (enabled && isRoundThumb()) {
/* 368 */         int ch = this.thumbRect.y + this.thumbRect.height / 2 - this.trackRect.y;
/* 369 */         track = new RoundRectangle2D.Float(x, this.trackRect.y, tw, ch, arc, arc);
/* 370 */         coloredTrack = new RoundRectangle2D.Float(x, (this.trackRect.y + ch), tw, (this.trackRect.height - ch), arc, arc);
/*     */       } else {
/* 372 */         track = new RoundRectangle2D.Float(x, this.trackRect.y, tw, this.trackRect.height, arc, arc);
/*     */       }  }
/*     */     
/* 375 */     if (coloredTrack != null) {
/* 376 */       if (this.slider.getInverted()) {
/* 377 */         RoundRectangle2D temp = track;
/* 378 */         track = coloredTrack;
/* 379 */         coloredTrack = temp;
/*     */       } 
/*     */       
/* 382 */       g.setColor(getTrackValueColor());
/* 383 */       ((Graphics2D)g).fill(coloredTrack);
/*     */     } 
/*     */     
/* 386 */     g.setColor(enabled ? getTrackColor() : this.disabledTrackColor);
/* 387 */     ((Graphics2D)g).fill(track);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintTicks(Graphics g) {
/* 395 */     super.paintTicks((Graphics)new Graphics2DProxy((Graphics2D)g)
/*     */         {
/*     */           public void setColor(Color c) {
/* 398 */             super.setColor(FlatSliderUI.this.tickColor);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintThumb(Graphics g) {
/* 405 */     Color thumbColor = getThumbColor();
/* 406 */     Color color = stateColor(this.slider, this.thumbHover, this.thumbPressed, thumbColor, this.disabledThumbColor, (Color)null, this.hoverThumbColor, this.pressedThumbColor);
/*     */     
/* 408 */     color = FlatUIUtils.deriveColor(color, thumbColor);
/*     */     
/* 410 */     Color foreground = this.slider.getForeground();
/*     */ 
/*     */     
/* 413 */     Color borderColor = (this.thumbBorderColor != null && foreground == this.defaultForeground) ? stateColor(this.slider, false, false, this.thumbBorderColor, this.disabledThumbBorderColor, this.focusedThumbBorderColor, (Color)null, (Color)null) : null;
/*     */     
/* 415 */     Color focusedColor = FlatUIUtils.deriveColor(this.focusedColor, 
/* 416 */         (foreground != this.defaultForeground) ? foreground : this.focusBaseColor);
/*     */     
/* 418 */     paintThumb(g, this.slider, this.thumbRect, isRoundThumb(), color, borderColor, focusedColor, this.thumbBorderWidth, this.focusWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void paintThumb(Graphics g, JSlider slider, Rectangle thumbRect, boolean roundThumb, Color thumbColor, Color thumbBorderColor, Color focusedColor, float thumbBorderWidth, int focusWidth) {
/* 424 */     double systemScaleFactor = UIScale.getSystemScaleFactor((Graphics2D)g);
/* 425 */     if (systemScaleFactor != 1.0D && systemScaleFactor != 2.0D) {
/*     */       
/* 427 */       HiDPIUtils.paintAtScale1x((Graphics2D)g, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, (g2d, x2, y2, width2, height2, scaleFactor) -> paintThumbImpl(g, slider, x2, y2, width2, height2, roundThumb, thumbColor, thumbBorderColor, focusedColor, (float)(thumbBorderWidth * scaleFactor), (float)(focusWidth * scaleFactor)));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 437 */     paintThumbImpl(g, slider, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, roundThumb, thumbColor, thumbBorderColor, focusedColor, thumbBorderWidth, focusWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void paintThumbImpl(Graphics g, JSlider slider, int x, int y, int width, int height, boolean roundThumb, Color thumbColor, Color thumbBorderColor, Color focusedColor, float thumbBorderWidth, float focusWidth) {
/* 446 */     int fw = Math.round(UIScale.scale(focusWidth));
/* 447 */     int tx = x + fw;
/* 448 */     int ty = y + fw;
/* 449 */     int tw = width - fw - fw;
/* 450 */     int th = height - fw - fw;
/* 451 */     boolean focused = FlatUIUtils.isPermanentFocusOwner(slider);
/*     */     
/* 453 */     if (roundThumb) {
/*     */       
/* 455 */       if (focused) {
/* 456 */         g.setColor(focusedColor);
/* 457 */         ((Graphics2D)g).fill(createRoundThumbShape(x, y, width, height));
/*     */       } 
/*     */       
/* 460 */       if (thumbBorderColor != null) {
/*     */         
/* 462 */         g.setColor(thumbBorderColor);
/* 463 */         ((Graphics2D)g).fill(createRoundThumbShape(tx, ty, tw, th));
/*     */ 
/*     */         
/* 466 */         float lw = UIScale.scale(thumbBorderWidth);
/* 467 */         g.setColor(thumbColor);
/* 468 */         ((Graphics2D)g).fill(createRoundThumbShape(tx + lw, ty + lw, tw - lw - lw, th - lw - lw));
/*     */       }
/*     */       else {
/*     */         
/* 472 */         g.setColor(thumbColor);
/* 473 */         ((Graphics2D)g).fill(createRoundThumbShape(tx, ty, tw, th));
/*     */       } 
/*     */     } else {
/* 476 */       Graphics2D g2 = (Graphics2D)g.create();
/*     */       try {
/* 478 */         g2.translate(x, y);
/* 479 */         if (slider.getOrientation() == 1) {
/* 480 */           if (slider.getComponentOrientation().isLeftToRight()) {
/* 481 */             g2.translate(0, height);
/* 482 */             g2.rotate(Math.toRadians(270.0D));
/*     */           } else {
/* 484 */             g2.translate(width, 0);
/* 485 */             g2.rotate(Math.toRadians(90.0D));
/*     */           } 
/*     */ 
/*     */           
/* 489 */           int temp = tw;
/* 490 */           tw = th;
/* 491 */           th = temp;
/*     */         } 
/*     */ 
/*     */         
/* 495 */         if (focused) {
/* 496 */           g2.setColor(focusedColor);
/* 497 */           g2.fill(createDirectionalThumbShape(0.0F, 0.0F, (tw + fw + fw), (th + fw + fw) + fw * 0.4142F, fw));
/*     */         } 
/*     */ 
/*     */         
/* 501 */         if (thumbBorderColor != null) {
/*     */           
/* 503 */           g2.setColor(thumbBorderColor);
/* 504 */           g2.fill(createDirectionalThumbShape(fw, fw, tw, th, 0.0F));
/*     */ 
/*     */           
/* 507 */           float lw = UIScale.scale(thumbBorderWidth);
/* 508 */           g2.setColor(thumbColor);
/* 509 */           g2.fill(createDirectionalThumbShape(fw + lw, fw + lw, tw - lw - lw, th - lw - lw - lw * 0.4142F, 0.0F));
/*     */         }
/*     */         else {
/*     */           
/* 513 */           g2.setColor(thumbColor);
/* 514 */           g2.fill(createDirectionalThumbShape(fw, fw, tw, th, 0.0F));
/*     */         } 
/*     */       } finally {
/* 517 */         g2.dispose();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Shape createRoundThumbShape(float x, float y, float w, float h) {
/* 523 */     if (w == h) {
/* 524 */       return new Ellipse2D.Float(x, y, w, h);
/*     */     }
/* 526 */     float arc = Math.min(w, h);
/* 527 */     return new RoundRectangle2D.Float(x, y, w, h, arc, arc);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Shape createDirectionalThumbShape(float x, float y, float w, float h, float arc) {
/* 532 */     float wh = w / 2.0F;
/*     */     
/* 534 */     Path2D path = new Path2D.Float(1, 9);
/* 535 */     path.moveTo((x + wh), (y + h));
/* 536 */     path.lineTo(x, (y + h - wh));
/* 537 */     path.lineTo(x, (y + arc));
/* 538 */     path.quadTo(x, y, (x + arc), y);
/* 539 */     path.lineTo((x + w - arc), y);
/* 540 */     path.quadTo((x + w), y, (x + w), (y + arc));
/* 541 */     path.lineTo((x + w), (y + h - wh));
/* 542 */     path.closePath();
/*     */     
/* 544 */     return path;
/*     */   }
/*     */   
/*     */   protected Color getTrackValueColor() {
/* 548 */     Color foreground = this.slider.getForeground();
/* 549 */     return (foreground != this.defaultForeground) ? foreground : this.trackValueColor;
/*     */   }
/*     */   
/*     */   protected Color getTrackColor() {
/* 553 */     Color backround = this.slider.getBackground();
/* 554 */     return (backround != this.defaultBackground) ? backround : this.trackColor;
/*     */   }
/*     */   
/*     */   protected Color getThumbColor() {
/* 558 */     Color foreground = this.slider.getForeground();
/* 559 */     return (foreground != this.defaultForeground) ? foreground : this.thumbColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color stateColor(JSlider slider, boolean hover, boolean pressed, Color enabledColor, Color disabledColor, Color focusedColor, Color hoverColor, Color pressedColor) {
/* 565 */     if (disabledColor != null && !slider.isEnabled())
/* 566 */       return disabledColor; 
/* 567 */     if (pressedColor != null && pressed)
/* 568 */       return pressedColor; 
/* 569 */     if (hoverColor != null && hover)
/* 570 */       return hoverColor; 
/* 571 */     if (focusedColor != null && FlatUIUtils.isPermanentFocusOwner(slider))
/* 572 */       return focusedColor; 
/* 573 */     return enabledColor;
/*     */   }
/*     */   
/*     */   protected boolean isRoundThumb() {
/* 577 */     return (!this.slider.getPaintTicks() && !this.slider.getPaintLabels());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThumbLocation(int x, int y) {
/* 582 */     if (!isRoundThumb()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 587 */       Rectangle r = new Rectangle(this.thumbRect);
/* 588 */       this.thumbRect.setLocation(x, y);
/* 589 */       SwingUtilities.computeUnion(this.thumbRect.x, this.thumbRect.y, this.thumbRect.width, this.thumbRect.height, r);
/*     */ 
/*     */       
/* 592 */       int extra = (int)Math.ceil((UIScale.scale(this.focusWidth) * 0.4142F));
/* 593 */       if (this.slider.getOrientation() == 0) {
/* 594 */         r.height += extra;
/*     */       } else {
/* 596 */         r.width += extra;
/* 597 */         if (!this.slider.getComponentOrientation().isLeftToRight()) {
/* 598 */           r.x -= extra;
/*     */         }
/*     */       } 
/* 601 */       this.slider.repaint(r);
/*     */     } else {
/* 603 */       super.setThumbLocation(x, y);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlatTrackListener
/*     */     extends BasicSliderUI.TrackListener
/*     */   {
/*     */     public void mouseEntered(MouseEvent e) {
/* 613 */       setThumbHover(isOverThumb(e));
/* 614 */       super.mouseEntered(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 619 */       setThumbHover(false);
/* 620 */       super.mouseExited(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 625 */       setThumbHover(isOverThumb(e));
/* 626 */       super.mouseMoved(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 631 */       setThumbPressed(isOverThumb(e));
/*     */       
/* 633 */       if (!FlatSliderUI.this.slider.isEnabled()) {
/*     */         return;
/*     */       }
/*     */       
/* 637 */       if (UIManager.getBoolean("Slider.scrollOnTrackClick")) {
/* 638 */         super.mousePressed(e);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 644 */       int x = e.getX();
/* 645 */       int y = e.getY();
/*     */ 
/*     */       
/* 648 */       FlatSliderUI.this.calculateGeometry();
/* 649 */       if (FlatSliderUI.this.thumbRect.contains(x, y)) {
/* 650 */         super.mousePressed(e);
/*     */         
/*     */         return;
/*     */       } 
/* 654 */       if (UIManager.getBoolean("Slider.onlyLeftMouseButtonDrag") && 
/* 655 */         !SwingUtilities.isLeftMouseButton(e)) {
/*     */         return;
/*     */       }
/*     */       
/* 659 */       int tx = FlatSliderUI.this.thumbRect.x + FlatSliderUI.this.thumbRect.width / 2 - x;
/* 660 */       int ty = FlatSliderUI.this.thumbRect.y + FlatSliderUI.this.thumbRect.height / 2 - y;
/* 661 */       e.translatePoint(tx, ty);
/*     */ 
/*     */       
/* 664 */       super.mousePressed(e);
/*     */ 
/*     */       
/* 667 */       e.translatePoint(-tx, -ty);
/*     */ 
/*     */       
/* 670 */       mouseDragged(e);
/*     */       
/* 672 */       setThumbPressed(true);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 677 */       setThumbPressed(false);
/* 678 */       super.mouseReleased(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 683 */       super.mouseDragged(e);
/*     */       
/* 685 */       if (FlatSliderUI.this.isDragging() && FlatSliderUI.this
/* 686 */         .slider.getSnapToTicks() && FlatSliderUI.this
/* 687 */         .slider.isEnabled() && 
/* 688 */         !UIManager.getBoolean("Slider.snapToTicksOnReleased")) {
/*     */         
/* 690 */         FlatSliderUI.this.calculateThumbLocation();
/* 691 */         FlatSliderUI.this.slider.repaint();
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void setThumbHover(boolean hover) {
/* 696 */       if (hover != FlatSliderUI.this.thumbHover) {
/* 697 */         FlatSliderUI.this.thumbHover = hover;
/* 698 */         FlatSliderUI.this.slider.repaint(FlatSliderUI.this.thumbRect);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void setThumbPressed(boolean pressed) {
/* 703 */       if (pressed != FlatSliderUI.this.thumbPressed) {
/* 704 */         FlatSliderUI.this.thumbPressed = pressed;
/* 705 */         FlatSliderUI.this.slider.repaint(FlatSliderUI.this.thumbRect);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected boolean isOverThumb(MouseEvent e) {
/* 710 */       return (e != null && FlatSliderUI.this.slider.isEnabled() && FlatSliderUI.this.thumbRect.contains(e.getX(), e.getY()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatSliderUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */