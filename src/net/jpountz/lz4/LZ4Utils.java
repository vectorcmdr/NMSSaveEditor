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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ enum LZ4Utils
/*    */ {
/*    */   private static final int MAX_INPUT_SIZE = 2113929216;
/*    */   
/*    */   static int maxCompressedLength(int length) {
/* 35 */     if (length < 0)
/* 36 */       throw new IllegalArgumentException("length must be >= 0, got " + length); 
/* 37 */     if (length >= 2113929216) {
/* 38 */       throw new IllegalArgumentException("length must be < 2113929216");
/*    */     }
/* 40 */     return length + length / 255 + 16;
/*    */   }
/*    */   
/*    */   static int hash(int i) {
/* 44 */     return i * -1640531535 >>> 20;
/*    */   }
/*    */   
/*    */   static int hash64k(int i) {
/* 48 */     return i * -1640531535 >>> 19;
/*    */   }
/*    */   
/*    */   static int hashHC(int i) {
/* 52 */     return i * -1640531535 >>> 17;
/*    */   }
/*    */   
/*    */   static class Match {
/*    */     int start;
/*    */     
/*    */     void fix(int correction) {
/* 59 */       this.start += correction;
/* 60 */       this.ref += correction;
/* 61 */       this.len -= correction;
/*    */     }
/*    */     int ref; int len;
/*    */     int end() {
/* 65 */       return this.start + this.len;
/*    */     }
/*    */   }
/*    */   
/*    */   static void copyTo(Match m1, Match m2) {
/* 70 */     m2.len = m1.len;
/* 71 */     m2.start = m1.start;
/* 72 */     m2.ref = m1.ref;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */