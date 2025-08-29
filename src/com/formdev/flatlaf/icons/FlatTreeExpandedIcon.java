/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatTreeUI;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
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
/*    */ public class FlatTreeExpandedIcon
/*    */   extends FlatTreeCollapsedIcon
/*    */ {
/*    */   public FlatTreeExpandedIcon() {
/* 34 */     super(UIManager.getColor("Tree.icon.expandedColor"));
/*    */   }
/*    */ 
/*    */   
/*    */   void setStyleColorFromTreeUI(Component c, Graphics2D g) {
/* 39 */     setStyleColorFromTreeUI(c, g, ui -> ui.iconExpandedColor);
/*    */   }
/*    */ 
/*    */   
/*    */   void rotate(Component c, Graphics2D g) {
/* 44 */     g.rotate(Math.toRadians(90.0D), this.width / 2.0D, this.height / 2.0D);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatTreeExpandedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */