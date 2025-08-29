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
/*    */ public class FlatDarkLaf
/*    */   extends FlatLaf
/*    */ {
/*    */   public static final String NAME = "FlatLaf Dark";
/*    */   
/*    */   public static boolean setup() {
/* 40 */     return setup(new FlatDarkLaf());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public static boolean install() {
/* 48 */     return setup();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void installLafInfo() {
/* 58 */     installLafInfo("FlatLaf Dark", (Class)FlatDarkLaf.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "FlatLaf Dark";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 68 */     return "FlatLaf Dark Look and Feel";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDark() {
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatDarkLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */