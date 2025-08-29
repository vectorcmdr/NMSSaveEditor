/*    */ package com.formdev.flatlaf.util;
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
/*    */ public interface LoggingFacade
/*    */ {
/* 24 */   public static final LoggingFacade INSTANCE = new LoggingFacadeImpl();
/*    */   
/*    */   void logSevere(String paramString, Throwable paramThrowable);
/*    */   
/*    */   void logConfig(String paramString, Throwable paramThrowable);
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\LoggingFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */