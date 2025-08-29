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
/*    */ class StackUtils
/*    */ {
/* 26 */   private static final StackUtils INSTANCE = new StackUtilsImpl();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean wasInvokedFrom(String className, String methodName, int limit) {
/* 36 */     return wasInvokedFrom((c, m) -> (c.equals(className) && m.equals(methodName)), limit);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean wasInvokedFrom(BiPredicate<String, String> predicate, int limit) {
/* 44 */     return INSTANCE.wasInvokedFromImpl(predicate, limit);
/*    */   }
/*    */   
/*    */   boolean wasInvokedFromImpl(BiPredicate<String, String> predicate, int limit) {
/* 48 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\StackUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */