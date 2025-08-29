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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatCheckBoxUI
/*    */   extends FlatRadioButtonUI
/*    */ {
/*    */   public static ComponentUI createUI(JComponent c) {
/* 46 */     return (FlatUIUtils.canUseSharedUI(c) && !FlatUIUtils.needsLightAWTPeer(c)) ? 
/* 47 */       FlatUIUtils.createSharedUI(FlatCheckBoxUI.class, () -> new FlatCheckBoxUI(true)) : 
/* 48 */       new FlatCheckBoxUI(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected FlatCheckBoxUI(boolean shared) {
/* 53 */     super(shared);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyPrefix() {
/* 58 */     return "CheckBox.";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   String getStyleType() {
/* 64 */     return "CheckBox";
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatCheckBoxUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */