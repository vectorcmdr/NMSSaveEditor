/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import java.util.function.BiPredicate;
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
/*    */ class StackUtilsImpl
/*    */   extends StackUtils
/*    */ {
/*    */   boolean wasInvokedFromImpl(BiPredicate<String, String> predicate, int limit) {
/* 29 */     int count = -2;
/* 30 */     StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
/* 31 */     for (StackTraceElement stackTraceElement : stackTrace) {
/* 32 */       if (predicate.test(stackTraceElement.getClassName(), stackTraceElement.getMethodName())) {
/* 33 */         return true;
/*    */       }
/* 35 */       count++;
/* 36 */       if (limit > 0 && count > limit)
/* 37 */         return false; 
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\StackUtilsImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */