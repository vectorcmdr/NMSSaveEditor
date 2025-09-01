package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;

public class FlatArrowButton extends BasicArrowButton implements UIResource {
   public static final int DEFAULT_ARROW_WIDTH = 9;
   protected boolean chevron;
   protected Color foreground;
   protected Color disabledForeground;
   protected Color hoverForeground;
   protected Color hoverBackground;
   protected Color pressedForeground;
   protected Color pressedBackground;
   private int arrowWidth = 9;
   private float arrowThickness = 1.0F;
   private float xOffset = 0.0F;
   private float yOffset = 0.0F;
   private boolean roundBorderAutoXOffset = true;
   private boolean hover;
   private boolean pressed;

   public FlatArrowButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
      super(direction, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
      this.updateStyle(type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
      this.setOpaque(false);
      this.setBorder((Border)null);
      if (hoverForeground != null || hoverBackground != null || pressedForeground != null || pressedBackground != null) {
         this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
               FlatArrowButton.this.hover = true;
               FlatArrowButton.this.repaint();
            }

            public void mouseExited(MouseEvent e) {
               FlatArrowButton.this.hover = false;
               FlatArrowButton.this.repaint();
            }

            public void mousePressed(MouseEvent e) {
               if (SwingUtilities.isLeftMouseButton(e)) {
                  FlatArrowButton.this.pressed = true;
                  FlatArrowButton.this.repaint();
               }

            }

            public void mouseReleased(MouseEvent e) {
               if (SwingUtilities.isLeftMouseButton(e)) {
                  FlatArrowButton.this.pressed = false;
                  FlatArrowButton.this.repaint();
               }

            }
         });
      }

   }

   public void updateStyle(String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
      this.chevron = FlatUIUtils.isChevron(type);
      this.foreground = foreground;
      this.disabledForeground = disabledForeground;
      this.hoverForeground = hoverForeground;
      this.hoverBackground = hoverBackground;
      this.pressedForeground = pressedForeground;
      this.pressedBackground = pressedBackground;
   }

   public int getArrowWidth() {
      return this.arrowWidth;
   }

   public void setArrowWidth(int arrowWidth) {
      this.arrowWidth = arrowWidth;
   }

   public float getArrowThickness() {
      return this.arrowThickness;
   }

   public void setArrowThickness(float arrowThickness) {
      this.arrowThickness = arrowThickness;
   }

   protected boolean isHover() {
      return this.hover;
   }

   protected boolean isPressed() {
      return this.pressed;
   }

   public float getXOffset() {
      return this.xOffset;
   }

   public void setXOffset(float xOffset) {
      this.xOffset = xOffset;
   }

   public float getYOffset() {
      return this.yOffset;
   }

   public void setYOffset(float yOffset) {
      this.yOffset = yOffset;
   }

   public boolean isRoundBorderAutoXOffset() {
      return this.roundBorderAutoXOffset;
   }

   public void setRoundBorderAutoXOffset(boolean roundBorderAutoXOffset) {
      this.roundBorderAutoXOffset = roundBorderAutoXOffset;
   }

   protected Color deriveBackground(Color background) {
      return background;
   }

   protected Color deriveForeground(Color foreground) {
      return FlatUIUtils.deriveColor(foreground, this.foreground);
   }

   protected Color getArrowColor() {
      return this.isEnabled() ? (this.pressedForeground != null && this.isPressed() ? this.pressedForeground : (this.hoverForeground != null && this.isHover() ? this.hoverForeground : this.foreground)) : this.disabledForeground;
   }

   public Dimension getPreferredSize() {
      return UIScale.scale(super.getPreferredSize());
   }

   public Dimension getMinimumSize() {
      return UIScale.scale(super.getMinimumSize());
   }

   public void paint(Graphics g) {
      Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
      if (this.isEnabled()) {
         Color background = this.pressedBackground != null && this.isPressed() ? this.pressedBackground : (this.hoverBackground != null && this.isHover() ? this.hoverBackground : null);
         if (background != null) {
            g.setColor(this.deriveBackground(background));
            this.paintBackground((Graphics2D)g);
         }
      }

      g.setColor(this.deriveForeground(this.getArrowColor()));
      this.paintArrow((Graphics2D)g);
      FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
   }

   protected void paintBackground(Graphics2D g) {
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
   }

   protected void paintArrow(Graphics2D g) {
      int x = 0;
      if (this.isRoundBorderAutoXOffset()) {
         Container parent = this.getParent();
         boolean vert = this.direction == 1 || this.direction == 5;
         if (vert && parent instanceof JComponent && FlatUIUtils.hasRoundBorder((JComponent)parent)) {
            x -= UIScale.scale(parent.getComponentOrientation().isLeftToRight() ? 1 : -1);
         }
      }

      FlatUIUtils.paintArrow(g, x, 0, this.getWidth(), this.getHeight(), this.getDirection(), this.chevron, this.getArrowWidth(), this.getArrowThickness(), this.getXOffset(), this.getYOffset());
   }
}
