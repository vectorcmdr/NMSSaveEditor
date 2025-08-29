/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JSpinner;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.plaf.SpinnerUI;
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
/*    */ public class FlatRoundBorder
/*    */   extends FlatBorder
/*    */ {
/*    */   @Styleable
/* 36 */   protected int arc = UIManager.getInt("Component.arc");
/*    */ 
/*    */   
/*    */   @Styleable
/*    */   protected Boolean roundRect;
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 44 */     if (isMacStyleSpinner(c)) {
/* 45 */       int macStyleButtonsWidth = ((FlatSpinnerUI)((JSpinner)c).getUI()).getMacStyleButtonsWidth();
/* 46 */       width -= macStyleButtonsWidth;
/* 47 */       if (!c.getComponentOrientation().isLeftToRight()) {
/* 48 */         x += macStyleButtonsWidth;
/*    */       }
/*    */     } 
/* 51 */     super.paintBorder(c, g, x, y, width, height);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getArc(Component c) {
/* 56 */     if (isCellEditor(c)) {
/* 57 */       return 0;
/*    */     }
/* 59 */     Boolean roundRect = FlatUIUtils.isRoundRect(c);
/* 60 */     if (roundRect == null)
/* 61 */       roundRect = this.roundRect; 
/* 62 */     return (roundRect != null) ? (
/* 63 */       roundRect.booleanValue() ? 32767 : 0) : (
/* 64 */       isMacStyleSpinner(c) ? 0 : this.arc);
/*    */   }
/*    */   
/*    */   private boolean isMacStyleSpinner(Component c) {
/* 68 */     if (c instanceof JSpinner) {
/* 69 */       SpinnerUI ui = ((JSpinner)c).getUI();
/* 70 */       if (ui instanceof FlatSpinnerUI)
/* 71 */         return ((FlatSpinnerUI)ui).isMacStyle(); 
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatRoundBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */