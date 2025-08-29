/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import com.formdev.flatlaf.util.HiDPIUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FlatWindowAbstractIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private final Color hoverBackground;
/*    */   private final Color pressedBackground;
/*    */   
/*    */   public FlatWindowAbstractIcon() {
/* 45 */     this(UIManager.getDimension("TitlePane.buttonSize"), 
/* 46 */         UIManager.getColor("TitlePane.buttonHoverBackground"), 
/* 47 */         UIManager.getColor("TitlePane.buttonPressedBackground"));
/*    */   }
/*    */   
/*    */   public FlatWindowAbstractIcon(Dimension size, Color hoverBackground, Color pressedBackground) {
/* 51 */     super(size.width, size.height, null);
/* 52 */     this.hoverBackground = hoverBackground;
/* 53 */     this.pressedBackground = pressedBackground;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 58 */     paintBackground(c, g);
/*    */     
/* 60 */     g.setColor(getForeground(c));
/* 61 */     HiDPIUtils.paintAtScale1x(g, 0, 0, this.width, this.height, this::paintIconAt1x);
/*    */   }
/*    */   
/*    */   protected abstract void paintIconAt1x(Graphics2D paramGraphics2D, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble);
/*    */   
/*    */   protected void paintBackground(Component c, Graphics2D g) {
/* 67 */     Color background = FlatButtonUI.buttonStateColor(c, null, null, null, this.hoverBackground, this.pressedBackground);
/* 68 */     if (background != null) {
/*    */       
/* 70 */       Object oldHint = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/* 71 */       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*    */       
/* 73 */       g.setColor(FlatUIUtils.deriveColor(background, c.getBackground()));
/* 74 */       g.fillRect(0, 0, this.width, this.height);
/*    */       
/* 76 */       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldHint);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected Color getForeground(Component c) {
/* 81 */     return c.getForeground();
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatWindowAbstractIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */