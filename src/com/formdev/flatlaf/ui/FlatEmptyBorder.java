/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Component;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.plaf.BorderUIResource;
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
/*    */ public class FlatEmptyBorder
/*    */   extends BorderUIResource.EmptyBorderUIResource
/*    */ {
/*    */   public FlatEmptyBorder() {
/* 35 */     super(0, 0, 0, 0);
/*    */   }
/*    */   
/*    */   public FlatEmptyBorder(int top, int left, int bottom, int right) {
/* 39 */     super(top, left, bottom, right);
/*    */   }
/*    */   
/*    */   public FlatEmptyBorder(Insets insets) {
/* 43 */     super(insets);
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets() {
/* 48 */     return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 53 */     return scaleInsets(c, insets, this.top, this.left, this.bottom, this.right);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static Insets scaleInsets(Component c, Insets insets, int top, int left, int bottom, int right) {
/* 59 */     boolean leftToRight = (left == right || c == null || c.getComponentOrientation().isLeftToRight());
/* 60 */     insets.left = UIScale.scale(leftToRight ? left : right);
/* 61 */     insets.top = UIScale.scale(top);
/* 62 */     insets.right = UIScale.scale(leftToRight ? right : left);
/* 63 */     insets.bottom = UIScale.scale(bottom);
/* 64 */     return insets;
/*    */   }
/*    */   
/*    */   public Insets getUnscaledBorderInsets() {
/* 68 */     return super.getBorderInsets();
/*    */   }
/*    */   
/*    */   public Object applyStyleProperty(Insets insets) {
/* 72 */     Insets oldInsets = getUnscaledBorderInsets();
/* 73 */     this.top = insets.top;
/* 74 */     this.left = insets.left;
/* 75 */     this.bottom = insets.bottom;
/* 76 */     this.right = insets.right;
/* 77 */     return oldInsets;
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getStyleableValue() {
/* 82 */     return new Insets(this.top, this.left, this.bottom, this.right);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatEmptyBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */