/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.JMenuBar;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.plaf.MenuBarUI;
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
/*    */ public class FlatMenuItemBorder
/*    */   extends FlatMarginBorder
/*    */ {
/* 39 */   private final Insets menuBarItemMargins = UIManager.getInsets("MenuBar.itemMargins");
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 43 */     Container parent = c.getParent();
/* 44 */     if (parent instanceof JMenuBar) {
/*    */       
/* 46 */       MenuBarUI ui = ((JMenuBar)parent).getUI();
/*    */ 
/*    */       
/* 49 */       Insets margins = (ui instanceof FlatMenuBarUI && ((FlatMenuBarUI)ui).itemMargins != null) ? ((FlatMenuBarUI)ui).itemMargins : this.menuBarItemMargins;
/* 50 */       insets.top = UIScale.scale(margins.top);
/* 51 */       insets.left = UIScale.scale(margins.left);
/* 52 */       insets.bottom = UIScale.scale(margins.bottom);
/* 53 */       insets.right = UIScale.scale(margins.right);
/* 54 */       return insets;
/*    */     } 
/* 56 */     return super.getBorderInsets(c, insets);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMenuItemBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */