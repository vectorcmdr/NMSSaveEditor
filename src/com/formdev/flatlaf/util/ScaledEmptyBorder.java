/*    */ package com.formdev.flatlaf.util;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.border.EmptyBorder;
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
/*    */ public class ScaledEmptyBorder
/*    */   extends EmptyBorder
/*    */ {
/*    */   public ScaledEmptyBorder(int top, int left, int bottom, int right) {
/* 33 */     super(top, left, bottom, right);
/*    */   }
/*    */   
/*    */   public ScaledEmptyBorder(Insets insets) {
/* 37 */     super(insets);
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets() {
/* 42 */     return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 47 */     insets.left = UIScale.scale(this.left);
/* 48 */     insets.top = UIScale.scale(this.top);
/* 49 */     insets.right = UIScale.scale(this.right);
/* 50 */     insets.bottom = UIScale.scale(this.bottom);
/* 51 */     return insets;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\ScaledEmptyBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */