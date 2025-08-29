/*    */ package com.formdev.flatlaf.util;
/*    */ 
/*    */ import com.formdev.flatlaf.FlatLaf;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
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
/*    */ class LoggingFacadeImpl
/*    */   implements LoggingFacade
/*    */ {
/* 29 */   private static final Logger LOG = Logger.getLogger(FlatLaf.class.getName());
/*    */ 
/*    */   
/*    */   public void logSevere(String message, Throwable t) {
/* 33 */     LOG.log(Level.SEVERE, message, t);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logConfig(String message, Throwable t) {
/* 38 */     LOG.log(Level.CONFIG, message, t);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\LoggingFacadeImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */