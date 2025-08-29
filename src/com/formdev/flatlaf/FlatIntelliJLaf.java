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
/*    */ public class FlatIntelliJLaf
/*    */   extends FlatLightLaf
/*    */ {
/*    */   public static final String NAME = "FlatLaf IntelliJ";
/*    */   
/*    */   public static boolean setup() {
/* 41 */     return setup(new FlatIntelliJLaf());
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
/* 59 */     installLafInfo("FlatLaf IntelliJ", (Class)FlatIntelliJLaf.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 64 */     return "FlatLaf IntelliJ";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 69 */     return "FlatLaf IntelliJ Look and Feel";
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatIntelliJLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */