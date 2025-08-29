/*    */ package net.jpountz.xxhash;
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
/*    */ abstract class AbstractStreamingXXHash64Java
/*    */   extends StreamingXXHash64
/*    */ {
/*    */   int memSize;
/*    */   long v1;
/*    */   long v2;
/*    */   long v3;
/*    */   long v4;
/*    */   long totalLen;
/*    */   final byte[] memory;
/*    */   
/*    */   AbstractStreamingXXHash64Java(long seed) {
/* 30 */     super(seed);
/* 31 */     this.memory = new byte[32];
/* 32 */     reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.v1 = this.seed + -7046029288634856825L + -4417276706812531889L;
/* 38 */     this.v2 = this.seed + -4417276706812531889L;
/* 39 */     this.v3 = this.seed + 0L;
/* 40 */     this.v4 = this.seed - -7046029288634856825L;
/* 41 */     this.totalLen = 0L;
/* 42 */     this.memSize = 0;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\AbstractStreamingXXHash64Java.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */