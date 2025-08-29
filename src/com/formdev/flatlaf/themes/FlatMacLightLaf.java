/*    */ package com.formdev.flatlaf.themes;
/*    */ 
/*    */ import com.formdev.flatlaf.FlatLightLaf;
/*    */ import javax.swing.LookAndFeel;
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
/*    */ public class FlatMacLightLaf
/*    */   extends FlatLightLaf
/*    */ {
/*    */   public static final String NAME = "FlatLaf macOS Light";
/*    */   
/*    */   public static boolean setup() {
/* 41 */     return setup((LookAndFeel)new FlatMacLightLaf());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void installLafInfo() {
/* 51 */     installLafInfo("FlatLaf macOS Light", FlatMacLightLaf.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 56 */     return "FlatLaf macOS Light";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 61 */     return "FlatLaf macOS Light Look and Feel";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDark() {
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\themes\FlatMacLightLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */