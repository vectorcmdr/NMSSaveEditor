/*    */ package net.jpountz.util;
/*    */ 
/*    */ import java.nio.ByteOrder;
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
/*    */ public enum Utils
/*    */ {
/*    */   public static final ByteOrder NATIVE_BYTE_ORDER;
/*    */   private static final boolean unalignedAccessAllowed;
/*    */   
/*    */   static {
/* 24 */     NATIVE_BYTE_ORDER = ByteOrder.nativeOrder();
/*    */ 
/*    */ 
/*    */     
/* 28 */     String arch = System.getProperty("os.arch");
/* 29 */     unalignedAccessAllowed = (arch.equals("i386") || arch.equals("x86") || arch.equals("amd64") || arch.equals("x86_64") || arch.equals("aarch64") || arch.equals("ppc64le"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isUnalignedAccessAllowed() {
/* 35 */     return unalignedAccessAllowed;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpount\\util\Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */