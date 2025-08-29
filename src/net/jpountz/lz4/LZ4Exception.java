/*    */ package net.jpountz.lz4;
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
/*    */ public class LZ4Exception
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public LZ4Exception(String msg, Throwable t) {
/* 27 */     super(msg, t);
/*    */   }
/*    */   
/*    */   public LZ4Exception(String msg) {
/* 31 */     super(msg);
/*    */   }
/*    */   
/*    */   public LZ4Exception() {}
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4Exception.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */