/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.SystemInfo;
/*    */ import java.awt.Window;
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
/*    */ public class FlatNativeWindowsLibrary
/*    */ {
/* 32 */   private static long osBuildNumber = Long.MIN_VALUE;
/*    */   
/*    */   public static boolean isLoaded() {
/* 35 */     return (SystemInfo.isWindows && FlatNativeLibrary.isLoaded());
/*    */   }
/*    */ 
/*    */   
/*    */   public static final int DWMWCP_DEFAULT = 0;
/*    */   public static final int DWMWCP_DONOTROUND = 1;
/*    */   public static final int DWMWCP_ROUND = 2;
/*    */   public static final int DWMWCP_ROUNDSMALL = 3;
/*    */   
/*    */   public static long getOSBuildNumber() {
/* 45 */     if (osBuildNumber == Long.MIN_VALUE)
/* 46 */       osBuildNumber = getOSBuildNumberImpl(); 
/* 47 */     return osBuildNumber;
/*    */   }
/*    */   
/*    */   private static native long getOSBuildNumberImpl();
/*    */   
/*    */   public static native long getHWND(Window paramWindow);
/*    */   
/*    */   public static native boolean setWindowCornerPreference(long paramLong, int paramInt);
/*    */   
/*    */   public static native boolean setWindowBorderColor(long paramLong, int paramInt1, int paramInt2, int paramInt3);
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatNativeWindowsLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */