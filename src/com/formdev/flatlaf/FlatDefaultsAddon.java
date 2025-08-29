/*    */ package com.formdev.flatlaf;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import javax.swing.LookAndFeel;
/*    */ import javax.swing.UIDefaults;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FlatDefaultsAddon
/*    */ {
/*    */   public InputStream getDefaults(Class<?> lafClass) {
/* 49 */     Class<?> addonClass = getClass();
/*    */     
/* 51 */     String propertiesName = '/' + addonClass.getPackage().getName().replace('.', '/') + '/' + lafClass.getSimpleName() + ".properties";
/* 52 */     return addonClass.getResourceAsStream(propertiesName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void afterDefaultsLoading(LookAndFeel laf, UIDefaults defaults) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority() {
/* 69 */     return 10000;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatDefaultsAddon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */