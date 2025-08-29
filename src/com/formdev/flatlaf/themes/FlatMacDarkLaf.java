/*    */ package com.formdev.flatlaf.themes;
/*    */ 
/*    */ import com.formdev.flatlaf.FlatDarkLaf;
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
/*    */ public class FlatMacDarkLaf
/*    */   extends FlatDarkLaf
/*    */ {
/*    */   public static final String NAME = "FlatLaf macOS Dark";
/*    */   
/*    */   public static boolean setup() {
/* 41 */     return setup((LookAndFeel)new FlatMacDarkLaf());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void installLafInfo() {
/* 51 */     installLafInfo("FlatLaf macOS Dark", FlatMacDarkLaf.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 56 */     return "FlatLaf macOS Dark";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 61 */     return "FlatLaf macOS Dark Look and Feel";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDark() {
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\themes\FlatMacDarkLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */