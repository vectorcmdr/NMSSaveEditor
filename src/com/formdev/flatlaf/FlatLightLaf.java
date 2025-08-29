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
/*    */ public class FlatLightLaf
/*    */   extends FlatLaf
/*    */ {
/*    */   public static final String NAME = "FlatLaf Light";
/*    */   
/*    */   public static boolean setup() {
/* 40 */     return setup(new FlatLightLaf());
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
/* 58 */     installLafInfo("FlatLaf Light", (Class)FlatLightLaf.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "FlatLaf Light";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 68 */     return "FlatLaf Light Look and Feel";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDark() {
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatLightLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */