/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.ComponentUI;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatPopupMenuSeparatorUI
/*    */   extends FlatSeparatorUI
/*    */ {
/*    */   public static ComponentUI createUI(JComponent c) {
/* 42 */     return FlatUIUtils.canUseSharedUI(c) ? 
/* 43 */       FlatUIUtils.createSharedUI(FlatPopupMenuSeparatorUI.class, () -> new FlatPopupMenuSeparatorUI(true)) : 
/* 44 */       new FlatPopupMenuSeparatorUI(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected FlatPopupMenuSeparatorUI(boolean shared) {
/* 49 */     super(shared);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getPropertyPrefix() {
/* 54 */     return "PopupMenuSeparator";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   String getStyleType() {
/* 60 */     return "PopupMenuSeparator";
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatPopupMenuSeparatorUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */