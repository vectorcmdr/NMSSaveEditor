/*    */ package com.formdev.flatlaf;
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
/*    */ public class FlatDarculaLaf
/*    */   extends FlatDarkLaf
/*    */ {
/*    */   public static final String NAME = "FlatLaf Darcula";
/*    */   
/*    */   public static boolean setup() {
/* 41 */     return setup(new FlatDarculaLaf());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public static boolean install() {
/* 49 */     return setup();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void installLafInfo() {
/* 59 */     installLafInfo("FlatLaf Darcula", (Class)FlatDarculaLaf.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 64 */     return "FlatLaf Darcula";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 69 */     return "FlatLaf Darcula Look and Feel";
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatDarculaLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */