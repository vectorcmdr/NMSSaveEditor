/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics2D;
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
/*    */ public abstract class FlatInternalFrameAbstractIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private final Color hoverBackground;
/*    */   private final Color pressedBackground;
/*    */   
/*    */   public FlatInternalFrameAbstractIcon() {
/* 43 */     this(UIManager.getDimension("InternalFrame.buttonSize"), 
/* 44 */         UIManager.getColor("InternalFrame.buttonHoverBackground"), 
/* 45 */         UIManager.getColor("InternalFrame.buttonPressedBackground"));
/*    */   }
/*    */   
/*    */   public FlatInternalFrameAbstractIcon(Dimension size, Color hoverBackground, Color pressedBackground) {
/* 49 */     super(size.width, size.height, null);
/* 50 */     this.hoverBackground = hoverBackground;
/* 51 */     this.pressedBackground = pressedBackground;
/*    */   }
/*    */   
/*    */   protected void paintBackground(Component c, Graphics2D g) {
/* 55 */     Color background = FlatButtonUI.buttonStateColor(c, null, null, null, this.hoverBackground, this.pressedBackground);
/* 56 */     if (background != null) {
/* 57 */       g.setColor(FlatUIUtils.deriveColor(background, c.getBackground()));
/* 58 */       g.fillRect(0, 0, this.width, this.height);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatInternalFrameAbstractIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */