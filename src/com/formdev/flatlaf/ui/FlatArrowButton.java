/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.UIResource;
/*     */ import javax.swing.plaf.basic.BasicArrowButton;
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
/*     */ public class FlatArrowButton
/*     */   extends BasicArrowButton
/*     */   implements UIResource
/*     */ {
/*     */   public static final int DEFAULT_ARROW_WIDTH = 9;
/*     */   protected boolean chevron;
/*     */   protected Color foreground;
/*     */   protected Color disabledForeground;
/*     */   protected Color hoverForeground;
/*     */   protected Color hoverBackground;
/*     */   protected Color pressedForeground;
/*     */   protected Color pressedBackground;
/*  51 */   private int arrowWidth = 9;
/*  52 */   private float arrowThickness = 1.0F;
/*  53 */   private float xOffset = 0.0F;
/*  54 */   private float yOffset = 0.0F;
/*     */   
/*     */   private boolean roundBorderAutoXOffset = true;
/*     */   
/*     */   private boolean hover;
/*     */   
/*     */   private boolean pressed;
/*     */   
/*     */   public FlatArrowButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
/*  63 */     super(direction, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
/*  64 */     updateStyle(type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
/*     */ 
/*     */     
/*  67 */     setOpaque(false);
/*  68 */     setBorder((Border)null);
/*     */     
/*  70 */     if (hoverForeground != null || hoverBackground != null || pressedForeground != null || pressedBackground != null)
/*     */     {
/*     */       
/*  73 */       addMouseListener(new MouseAdapter()
/*     */           {
/*     */             public void mouseEntered(MouseEvent e) {
/*  76 */               FlatArrowButton.this.hover = true;
/*  77 */               FlatArrowButton.this.repaint();
/*     */             }
/*     */ 
/*     */             
/*     */             public void mouseExited(MouseEvent e) {
/*  82 */               FlatArrowButton.this.hover = false;
/*  83 */               FlatArrowButton.this.repaint();
/*     */             }
/*     */ 
/*     */             
/*     */             public void mousePressed(MouseEvent e) {
/*  88 */               if (SwingUtilities.isLeftMouseButton(e)) {
/*  89 */                 FlatArrowButton.this.pressed = true;
/*  90 */                 FlatArrowButton.this.repaint();
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/*     */             public void mouseReleased(MouseEvent e) {
/*  96 */               if (SwingUtilities.isLeftMouseButton(e)) {
/*  97 */                 FlatArrowButton.this.pressed = false;
/*  98 */                 FlatArrowButton.this.repaint();
/*     */               } 
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateStyle(String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
/* 109 */     this.chevron = FlatUIUtils.isChevron(type);
/* 110 */     this.foreground = foreground;
/* 111 */     this.disabledForeground = disabledForeground;
/* 112 */     this.hoverForeground = hoverForeground;
/* 113 */     this.hoverBackground = hoverBackground;
/* 114 */     this.pressedForeground = pressedForeground;
/* 115 */     this.pressedBackground = pressedBackground;
/*     */   }
/*     */   
/*     */   public int getArrowWidth() {
/* 119 */     return this.arrowWidth;
/*     */   }
/*     */   
/*     */   public void setArrowWidth(int arrowWidth) {
/* 123 */     this.arrowWidth = arrowWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getArrowThickness() {
/* 128 */     return this.arrowThickness;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArrowThickness(float arrowThickness) {
/* 133 */     this.arrowThickness = arrowThickness;
/*     */   }
/*     */   
/*     */   protected boolean isHover() {
/* 137 */     return this.hover;
/*     */   }
/*     */   
/*     */   protected boolean isPressed() {
/* 141 */     return this.pressed;
/*     */   }
/*     */   
/*     */   public float getXOffset() {
/* 145 */     return this.xOffset;
/*     */   }
/*     */   
/*     */   public void setXOffset(float xOffset) {
/* 149 */     this.xOffset = xOffset;
/*     */   }
/*     */   
/*     */   public float getYOffset() {
/* 153 */     return this.yOffset;
/*     */   }
/*     */   
/*     */   public void setYOffset(float yOffset) {
/* 157 */     this.yOffset = yOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRoundBorderAutoXOffset() {
/* 162 */     return this.roundBorderAutoXOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoundBorderAutoXOffset(boolean roundBorderAutoXOffset) {
/* 167 */     this.roundBorderAutoXOffset = roundBorderAutoXOffset;
/*     */   }
/*     */   
/*     */   protected Color deriveBackground(Color background) {
/* 171 */     return background;
/*     */   }
/*     */   
/*     */   protected Color deriveForeground(Color foreground) {
/* 175 */     return FlatUIUtils.deriveColor(foreground, this.foreground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Color getArrowColor() {
/* 184 */     return isEnabled() ? (
/* 185 */       (this.pressedForeground != null && isPressed()) ? 
/* 186 */       this.pressedForeground : (
/* 187 */       (this.hoverForeground != null && isHover()) ? 
/* 188 */       this.hoverForeground : 
/* 189 */       this.foreground)) : 
/* 190 */       this.disabledForeground;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 195 */     return UIScale.scale(super.getPreferredSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 200 */     return UIScale.scale(super.getMinimumSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 205 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/*     */ 
/*     */     
/* 208 */     if (isEnabled()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 213 */       Color background = (this.pressedBackground != null && isPressed()) ? this.pressedBackground : ((this.hoverBackground != null && isHover()) ? this.hoverBackground : null);
/*     */       
/* 215 */       if (background != null) {
/* 216 */         g.setColor(deriveBackground(background));
/* 217 */         paintBackground((Graphics2D)g);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 222 */     g.setColor(deriveForeground(getArrowColor()));
/* 223 */     paintArrow((Graphics2D)g);
/*     */     
/* 225 */     FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */   }
/*     */   
/*     */   protected void paintBackground(Graphics2D g) {
/* 229 */     g.fillRect(0, 0, getWidth(), getHeight());
/*     */   }
/*     */   
/*     */   protected void paintArrow(Graphics2D g) {
/* 233 */     int x = 0;
/*     */ 
/*     */     
/* 236 */     if (isRoundBorderAutoXOffset()) {
/* 237 */       Container parent = getParent();
/* 238 */       boolean vert = (this.direction == 1 || this.direction == 5);
/* 239 */       if (vert && parent instanceof JComponent && FlatUIUtils.hasRoundBorder((JComponent)parent)) {
/* 240 */         x -= UIScale.scale(parent.getComponentOrientation().isLeftToRight() ? 1 : -1);
/*     */       }
/*     */     } 
/* 243 */     FlatUIUtils.paintArrow(g, x, 0, getWidth(), getHeight(), getDirection(), this.chevron, 
/* 244 */         getArrowWidth(), getArrowThickness(), getXOffset(), getYOffset());
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatArrowButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */