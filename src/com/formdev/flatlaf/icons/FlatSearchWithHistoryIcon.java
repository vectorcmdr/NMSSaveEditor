/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
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
/*    */ public class FlatSearchWithHistoryIcon
/*    */   extends FlatSearchIcon
/*    */ {
/*    */   public FlatSearchWithHistoryIcon() {
/* 33 */     this(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public FlatSearchWithHistoryIcon(boolean ignoreButtonState) {
/* 38 */     super(ignoreButtonState);
/*    */   }
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
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 54 */     g.translate(-2, 0);
/* 55 */     super.paintIcon(c, g);
/* 56 */     g.translate(2, 0);
/*    */ 
/*    */     
/* 59 */     g.fill(FlatUIUtils.createPath(new double[] { 11.0D, 7.0D, 16.0D, 7.0D, 13.5D, 10.0D }));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatSearchWithHistoryIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */