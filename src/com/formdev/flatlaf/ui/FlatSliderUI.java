package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Float;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.plaf.basic.BasicSliderUI.TrackListener;

public class FlatSliderUI extends BasicSliderUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected int trackWidth;
   @FlatStylingSupport.Styleable
   protected Dimension thumbSize;
   @FlatStylingSupport.Styleable
   protected int focusWidth;
   @FlatStylingSupport.Styleable
   protected float thumbBorderWidth;
   @FlatStylingSupport.Styleable
   protected Color trackValueColor;
   @FlatStylingSupport.Styleable
   protected Color trackColor;
   @FlatStylingSupport.Styleable
   protected Color thumbColor;
   @FlatStylingSupport.Styleable
   protected Color thumbBorderColor;
   protected Color focusBaseColor;
   @FlatStylingSupport.Styleable
   protected Color focusedColor;
   @FlatStylingSupport.Styleable
   protected Color focusedThumbBorderColor;
   @FlatStylingSupport.Styleable
   protected Color hoverThumbColor;
   @FlatStylingSupport.Styleable
   protected Color pressedThumbColor;
   @FlatStylingSupport.Styleable
   protected Color disabledTrackColor;
   @FlatStylingSupport.Styleable
   protected Color disabledThumbColor;
   @FlatStylingSupport.Styleable
   protected Color disabledThumbBorderColor;
   @FlatStylingSupport.Styleable
   protected Color tickColor;
   private Color defaultBackground;
   private Color defaultForeground;
   protected boolean thumbHover;
   protected boolean thumbPressed;
   private Object[] oldRenderingHints;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatSliderUI();
   }

   public FlatSliderUI() {
      super((JSlider)null);
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults(JSlider slider) {
      super.installDefaults(slider);
      LookAndFeel.installProperty(slider, "opaque", false);
      this.trackWidth = UIManager.getInt("Slider.trackWidth");
      this.thumbSize = UIManager.getDimension("Slider.thumbSize");
      if (this.thumbSize == null) {
         int thumbWidth = UIManager.getInt("Slider.thumbWidth");
         this.thumbSize = new Dimension(thumbWidth, thumbWidth);
      }

      this.focusWidth = FlatUIUtils.getUIInt("Slider.focusWidth", 4);
      this.thumbBorderWidth = FlatUIUtils.getUIFloat("Slider.thumbBorderWidth", 1.0F);
      this.trackValueColor = FlatUIUtils.getUIColor("Slider.trackValueColor", "Slider.thumbColor");
      this.trackColor = UIManager.getColor("Slider.trackColor");
      this.thumbColor = UIManager.getColor("Slider.thumbColor");
      this.thumbBorderColor = UIManager.getColor("Slider.thumbBorderColor");
      this.focusBaseColor = UIManager.getColor("Component.focusColor");
      this.focusedColor = FlatUIUtils.getUIColor("Slider.focusedColor", this.focusBaseColor);
      this.focusedThumbBorderColor = FlatUIUtils.getUIColor("Slider.focusedThumbBorderColor", "Component.focusedBorderColor");
      this.hoverThumbColor = UIManager.getColor("Slider.hoverThumbColor");
      this.pressedThumbColor = UIManager.getColor("Slider.pressedThumbColor");
      this.disabledTrackColor = UIManager.getColor("Slider.disabledTrackColor");
      this.disabledThumbColor = UIManager.getColor("Slider.disabledThumbColor");
      this.disabledThumbBorderColor = FlatUIUtils.getUIColor("Slider.disabledThumbBorderColor", "Component.disabledBorderColor");
      this.tickColor = FlatUIUtils.getUIColor("Slider.tickColor", Color.BLACK);
      this.defaultBackground = UIManager.getColor("Slider.background");
      this.defaultForeground = UIManager.getColor("Slider.foreground");
   }

   protected void uninstallDefaults(JSlider slider) {
      super.uninstallDefaults(slider);
      this.trackValueColor = null;
      this.trackColor = null;
      this.thumbColor = null;
      this.thumbBorderColor = null;
      this.focusBaseColor = null;
      this.focusedColor = null;
      this.focusedThumbBorderColor = null;
      this.hoverThumbColor = null;
      this.pressedThumbColor = null;
      this.disabledTrackColor = null;
      this.disabledThumbColor = null;
      this.disabledThumbBorderColor = null;
      this.tickColor = null;
      this.defaultBackground = null;
      this.defaultForeground = null;
      this.oldStyleValues = null;
   }

   protected TrackListener createTrackListener(JSlider slider) {
      return new FlatSliderUI.FlatTrackListener();
   }

   protected PropertyChangeListener createPropertyChangeListener(JSlider slider) {
      return FlatStylingSupport.createPropertyChangeListener(slider, this::installStyle, super.createPropertyChangeListener(slider));
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.slider, "Slider"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
   }

   protected Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.slider, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   public int getBaseline(JComponent c, int width, int height) {
      if (c == null) {
         throw new NullPointerException();
      } else if (width >= 0 && height >= 0) {
         if (this.slider.getOrientation() == 1) {
            return -1;
         } else {
            Font font = UIManager.getFont("defaultFont");
            if (font == null) {
               font = this.slider.getFont();
            }

            FontMetrics fm = this.slider.getFontMetrics(font);
            Insets insets = this.slider.getInsets();
            int thumbHeight = this.getThumbSize().height;
            int contentHeight = height - insets.top - insets.bottom - this.focusInsets.top - this.focusInsets.bottom;
            int centerSpacing = thumbHeight + (this.slider.getPaintTicks() ? this.getTickLength() : 0) + (this.slider.getPaintLabels() ? this.getHeightOfTallestLabel() : 0);
            int trackY = insets.top + this.focusInsets.top + (contentHeight - centerSpacing - 1) / 2;
            return trackY + Math.round((float)(thumbHeight - fm.getHeight()) / 2.0F) + fm.getAscent() - 1;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Dimension getPreferredHorizontalSize() {
      return UIScale.scale(super.getPreferredHorizontalSize());
   }

   public Dimension getPreferredVerticalSize() {
      return UIScale.scale(super.getPreferredVerticalSize());
   }

   public Dimension getMinimumHorizontalSize() {
      return UIScale.scale(super.getMinimumHorizontalSize());
   }

   public Dimension getMinimumVerticalSize() {
      return UIScale.scale(super.getMinimumVerticalSize());
   }

   protected int getTickLength() {
      return UIScale.scale(super.getTickLength());
   }

   protected Dimension getThumbSize() {
      return calcThumbSize(this.slider, this.thumbSize, this.focusWidth);
   }

   public static Dimension calcThumbSize(JSlider slider, Dimension thumbSize, int focusWidth) {
      int fw = UIScale.scale(focusWidth);
      int w = UIScale.scale(thumbSize.width) + fw + fw;
      int h = UIScale.scale(thumbSize.height) + fw + fw;
      return slider.getOrientation() == 0 ? new Dimension(w, h) : new Dimension(h, w);
   }

   public void paint(Graphics g, JComponent c) {
      this.oldRenderingHints = FlatUIUtils.setRenderingHints(g);
      super.paint(g, c);
      FlatUIUtils.resetRenderingHints(g, this.oldRenderingHints);
      this.oldRenderingHints = null;
   }

   public void paintLabels(Graphics g) {
      FlatUIUtils.runWithoutRenderingHints(g, this.oldRenderingHints, () -> {
         super.paintLabels(g);
      });
   }

   public void paintFocus(Graphics g) {
   }

   public void paintTrack(Graphics g) {
      boolean enabled = this.slider.isEnabled();
      float tw = UIScale.scale((float)this.trackWidth);
      RoundRectangle2D coloredTrack = null;
      Float track;
      float y;
      int cw;
      if (this.slider.getOrientation() == 0) {
         y = (float)this.trackRect.y + ((float)this.trackRect.height - tw) / 2.0F;
         if (enabled && this.isRoundThumb()) {
            if (this.slider.getComponentOrientation().isLeftToRight()) {
               cw = this.thumbRect.x + this.thumbRect.width / 2 - this.trackRect.x;
               coloredTrack = new Float((float)this.trackRect.x, y, (float)cw, tw, tw, tw);
               track = new Float((float)(this.trackRect.x + cw), y, (float)(this.trackRect.width - cw), tw, tw, tw);
            } else {
               cw = this.trackRect.x + this.trackRect.width - this.thumbRect.x - this.thumbRect.width / 2;
               coloredTrack = new Float((float)(this.trackRect.x + this.trackRect.width - cw), y, (float)cw, tw, tw, tw);
               track = new Float((float)this.trackRect.x, y, (float)(this.trackRect.width - cw), tw, tw, tw);
            }
         } else {
            track = new Float((float)this.trackRect.x, y, (float)this.trackRect.width, tw, tw, tw);
         }
      } else {
         y = (float)this.trackRect.x + ((float)this.trackRect.width - tw) / 2.0F;
         if (enabled && this.isRoundThumb()) {
            cw = this.thumbRect.y + this.thumbRect.height / 2 - this.trackRect.y;
            track = new Float(y, (float)this.trackRect.y, tw, (float)cw, tw, tw);
            coloredTrack = new Float(y, (float)(this.trackRect.y + cw), tw, (float)(this.trackRect.height - cw), tw, tw);
         } else {
            track = new Float(y, (float)this.trackRect.y, tw, (float)this.trackRect.height, tw, tw);
         }
      }

      if (coloredTrack != null) {
         if (this.slider.getInverted()) {
            RoundRectangle2D temp = track;
            track = coloredTrack;
            coloredTrack = temp;
         }

         g.setColor(this.getTrackValueColor());
         ((Graphics2D)g).fill(coloredTrack);
      }

      g.setColor(enabled ? this.getTrackColor() : this.disabledTrackColor);
      ((Graphics2D)g).fill(track);
   }

   public void paintTicks(Graphics g) {
      super.paintTicks(new Graphics2DProxy((Graphics2D)g) {
         public void setColor(Color c) {
            super.setColor(FlatSliderUI.this.tickColor);
         }
      });
   }

   public void paintThumb(Graphics g) {
      Color thumbColor = this.getThumbColor();
      Color color = stateColor(this.slider, this.thumbHover, this.thumbPressed, thumbColor, this.disabledThumbColor, (Color)null, this.hoverThumbColor, this.pressedThumbColor);
      color = FlatUIUtils.deriveColor(color, thumbColor);
      Color foreground = this.slider.getForeground();
      Color borderColor = this.thumbBorderColor != null && foreground == this.defaultForeground ? stateColor(this.slider, false, false, this.thumbBorderColor, this.disabledThumbBorderColor, this.focusedThumbBorderColor, (Color)null, (Color)null) : null;
      Color focusedColor = FlatUIUtils.deriveColor(this.focusedColor, foreground != this.defaultForeground ? foreground : this.focusBaseColor);
      paintThumb(g, this.slider, this.thumbRect, this.isRoundThumb(), color, borderColor, focusedColor, this.thumbBorderWidth, this.focusWidth);
   }

   public static void paintThumb(Graphics g, JSlider slider, Rectangle thumbRect, boolean roundThumb, Color thumbColor, Color thumbBorderColor, Color focusedColor, float thumbBorderWidth, int focusWidth) {
      double systemScaleFactor = UIScale.getSystemScaleFactor((Graphics2D)g);
      if (systemScaleFactor != 1.0D && systemScaleFactor != 2.0D) {
         HiDPIUtils.paintAtScale1x((Graphics2D)g, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, (g2d, x2, y2, width2, height2, scaleFactor) -> {
            paintThumbImpl(g, slider, x2, y2, width2, height2, roundThumb, thumbColor, thumbBorderColor, focusedColor, (float)((double)thumbBorderWidth * scaleFactor), (float)((double)focusWidth * scaleFactor));
         });
      } else {
         paintThumbImpl(g, slider, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, roundThumb, thumbColor, thumbBorderColor, focusedColor, thumbBorderWidth, (float)focusWidth);
      }
   }

   private static void paintThumbImpl(Graphics g, JSlider slider, int x, int y, int width, int height, boolean roundThumb, Color thumbColor, Color thumbBorderColor, Color focusedColor, float thumbBorderWidth, float focusWidth) {
      int fw = Math.round(UIScale.scale(focusWidth));
      int tx = x + fw;
      int ty = y + fw;
      int tw = width - fw - fw;
      int th = height - fw - fw;
      boolean focused = FlatUIUtils.isPermanentFocusOwner(slider);
      if (roundThumb) {
         if (focused) {
            g.setColor(focusedColor);
            ((Graphics2D)g).fill(createRoundThumbShape((float)x, (float)y, (float)width, (float)height));
         }

         if (thumbBorderColor != null) {
            g.setColor(thumbBorderColor);
            ((Graphics2D)g).fill(createRoundThumbShape((float)tx, (float)ty, (float)tw, (float)th));
            float lw = UIScale.scale(thumbBorderWidth);
            g.setColor(thumbColor);
            ((Graphics2D)g).fill(createRoundThumbShape((float)tx + lw, (float)ty + lw, (float)tw - lw - lw, (float)th - lw - lw));
         } else {
            g.setColor(thumbColor);
            ((Graphics2D)g).fill(createRoundThumbShape((float)tx, (float)ty, (float)tw, (float)th));
         }
      } else {
         Graphics2D g2 = (Graphics2D)g.create();

         try {
            g2.translate(x, y);
            if (slider.getOrientation() == 1) {
               if (slider.getComponentOrientation().isLeftToRight()) {
                  g2.translate(0, height);
                  g2.rotate(Math.toRadians(270.0D));
               } else {
                  g2.translate(width, 0);
                  g2.rotate(Math.toRadians(90.0D));
               }

               int temp = tw;
               tw = th;
               th = temp;
            }

            if (focused) {
               g2.setColor(focusedColor);
               g2.fill(createDirectionalThumbShape(0.0F, 0.0F, (float)(tw + fw + fw), (float)(th + fw + fw) + (float)fw * 0.4142F, (float)fw));
            }

            if (thumbBorderColor != null) {
               g2.setColor(thumbBorderColor);
               g2.fill(createDirectionalThumbShape((float)fw, (float)fw, (float)tw, (float)th, 0.0F));
               float lw = UIScale.scale(thumbBorderWidth);
               g2.setColor(thumbColor);
               g2.fill(createDirectionalThumbShape((float)fw + lw, (float)fw + lw, (float)tw - lw - lw, (float)th - lw - lw - lw * 0.4142F, 0.0F));
            } else {
               g2.setColor(thumbColor);
               g2.fill(createDirectionalThumbShape((float)fw, (float)fw, (float)tw, (float)th, 0.0F));
            }
         } finally {
            g2.dispose();
         }
      }

   }

   public static Shape createRoundThumbShape(float x, float y, float w, float h) {
      if (w == h) {
         return new java.awt.geom.Ellipse2D.Float(x, y, w, h);
      } else {
         float arc = Math.min(w, h);
         return new Float(x, y, w, h, arc, arc);
      }
   }

   public static Shape createDirectionalThumbShape(float x, float y, float w, float h, float arc) {
      float wh = w / 2.0F;
      Path2D path = new java.awt.geom.Path2D.Float(1, 9);
      path.moveTo((double)(x + wh), (double)(y + h));
      path.lineTo((double)x, (double)(y + (h - wh)));
      path.lineTo((double)x, (double)(y + arc));
      path.quadTo((double)x, (double)y, (double)(x + arc), (double)y);
      path.lineTo((double)(x + (w - arc)), (double)y);
      path.quadTo((double)(x + w), (double)y, (double)(x + w), (double)(y + arc));
      path.lineTo((double)(x + w), (double)(y + (h - wh)));
      path.closePath();
      return path;
   }

   protected Color getTrackValueColor() {
      Color foreground = this.slider.getForeground();
      return foreground != this.defaultForeground ? foreground : this.trackValueColor;
   }

   protected Color getTrackColor() {
      Color backround = this.slider.getBackground();
      return backround != this.defaultBackground ? backround : this.trackColor;
   }

   protected Color getThumbColor() {
      Color foreground = this.slider.getForeground();
      return foreground != this.defaultForeground ? foreground : this.thumbColor;
   }

   public static Color stateColor(JSlider slider, boolean hover, boolean pressed, Color enabledColor, Color disabledColor, Color focusedColor, Color hoverColor, Color pressedColor) {
      if (disabledColor != null && !slider.isEnabled()) {
         return disabledColor;
      } else if (pressedColor != null && pressed) {
         return pressedColor;
      } else if (hoverColor != null && hover) {
         return hoverColor;
      } else {
         return focusedColor != null && FlatUIUtils.isPermanentFocusOwner(slider) ? focusedColor : enabledColor;
      }
   }

   protected boolean isRoundThumb() {
      return !this.slider.getPaintTicks() && !this.slider.getPaintLabels();
   }

   public void setThumbLocation(int x, int y) {
      if (!this.isRoundThumb()) {
         Rectangle r = new Rectangle(this.thumbRect);
         this.thumbRect.setLocation(x, y);
         SwingUtilities.computeUnion(this.thumbRect.x, this.thumbRect.y, this.thumbRect.width, this.thumbRect.height, r);
         int extra = (int)Math.ceil((double)((float)UIScale.scale(this.focusWidth) * 0.4142F));
         if (this.slider.getOrientation() == 0) {
            r.height += extra;
         } else {
            r.width += extra;
            if (!this.slider.getComponentOrientation().isLeftToRight()) {
               r.x -= extra;
            }
         }

         this.slider.repaint(r);
      } else {
         super.setThumbLocation(x, y);
      }

   }

   protected class FlatTrackListener extends TrackListener {
      protected FlatTrackListener() {
         super(FlatSliderUI.this);
      }

      public void mouseEntered(MouseEvent e) {
         this.setThumbHover(this.isOverThumb(e));
         super.mouseEntered(e);
      }

      public void mouseExited(MouseEvent e) {
         this.setThumbHover(false);
         super.mouseExited(e);
      }

      public void mouseMoved(MouseEvent e) {
         this.setThumbHover(this.isOverThumb(e));
         super.mouseMoved(e);
      }

      public void mousePressed(MouseEvent e) {
         this.setThumbPressed(this.isOverThumb(e));
         if (FlatSliderUI.this.slider.isEnabled()) {
            if (UIManager.getBoolean("Slider.scrollOnTrackClick")) {
               super.mousePressed(e);
            } else {
               int x = e.getX();
               int y = e.getY();
               FlatSliderUI.this.calculateGeometry();
               if (FlatSliderUI.this.thumbRect.contains(x, y)) {
                  super.mousePressed(e);
               } else if (!UIManager.getBoolean("Slider.onlyLeftMouseButtonDrag") || SwingUtilities.isLeftMouseButton(e)) {
                  int tx = FlatSliderUI.this.thumbRect.x + FlatSliderUI.this.thumbRect.width / 2 - x;
                  int ty = FlatSliderUI.this.thumbRect.y + FlatSliderUI.this.thumbRect.height / 2 - y;
                  e.translatePoint(tx, ty);
                  super.mousePressed(e);
                  e.translatePoint(-tx, -ty);
                  this.mouseDragged(e);
                  this.setThumbPressed(true);
               }
            }
         }
      }

      public void mouseReleased(MouseEvent e) {
         this.setThumbPressed(false);
         super.mouseReleased(e);
      }

      public void mouseDragged(MouseEvent e) {
         super.mouseDragged(e);
         if (FlatSliderUI.this.isDragging() && FlatSliderUI.this.slider.getSnapToTicks() && FlatSliderUI.this.slider.isEnabled() && !UIManager.getBoolean("Slider.snapToTicksOnReleased")) {
            FlatSliderUI.this.calculateThumbLocation();
            FlatSliderUI.this.slider.repaint();
         }

      }

      protected void setThumbHover(boolean hover) {
         if (hover != FlatSliderUI.this.thumbHover) {
            FlatSliderUI.this.thumbHover = hover;
            FlatSliderUI.this.slider.repaint(FlatSliderUI.this.thumbRect);
         }

      }

      protected void setThumbPressed(boolean pressed) {
         if (pressed != FlatSliderUI.this.thumbPressed) {
            FlatSliderUI.this.thumbPressed = pressed;
            FlatSliderUI.this.slider.repaint(FlatSliderUI.this.thumbRect);
         }

      }

      protected boolean isOverThumb(MouseEvent e) {
         return e != null && FlatSliderUI.this.slider.isEnabled() && FlatSliderUI.this.thumbRect.contains(e.getX(), e.getY());
      }
   }
}
