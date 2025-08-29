/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Component;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.plaf.basic.BasicBorders;
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
/*    */ public class FlatMarginBorder
/*    */   extends BasicBorders.MarginBorder
/*    */ {
/*    */   protected int left;
/*    */   protected int right;
/*    */   protected int top;
/*    */   protected int bottom;
/*    */   
/*    */   public FlatMarginBorder() {
/* 35 */     this.left = this.right = this.top = this.bottom = 0;
/*    */   }
/*    */   
/*    */   public FlatMarginBorder(Insets insets) {
/* 39 */     this.left = insets.left;
/* 40 */     this.top = insets.top;
/* 41 */     this.right = insets.right;
/* 42 */     this.bottom = insets.bottom;
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 47 */     insets = super.getBorderInsets(c, insets);
/* 48 */     insets.top = UIScale.scale(insets.top + this.top);
/* 49 */     insets.left = UIScale.scale(insets.left + this.left);
/* 50 */     insets.bottom = UIScale.scale(insets.bottom + this.bottom);
/* 51 */     insets.right = UIScale.scale(insets.right + this.right);
/* 52 */     return insets;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMarginBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */