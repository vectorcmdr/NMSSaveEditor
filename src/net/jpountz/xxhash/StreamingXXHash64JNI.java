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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StreamingXXHash64JNI
/*    */   extends StreamingXXHash64
/*    */ {
/*    */   private long state;
/*    */   
/*    */   static class Factory
/*    */     implements StreamingXXHash64.Factory
/*    */   {
/* 32 */     public static final StreamingXXHash64.Factory INSTANCE = new Factory();
/*    */ 
/*    */     
/*    */     public StreamingXXHash64 newStreamingHash(long seed) {
/* 36 */       return new StreamingXXHash64JNI(seed);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   StreamingXXHash64JNI(long seed) {
/* 44 */     super(seed);
/* 45 */     this.state = XXHashJNI.XXH64_init(seed);
/*    */   }
/*    */   
/*    */   private void checkState() {
/* 49 */     if (this.state == 0L) {
/* 50 */       throw new AssertionError("Already finalized");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void reset() {
/* 56 */     checkState();
/* 57 */     XXHashJNI.XXH64_free(this.state);
/* 58 */     this.state = XXHashJNI.XXH64_init(this.seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized long getValue() {
/* 63 */     checkState();
/* 64 */     return XXHashJNI.XXH64_digest(this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void update(byte[] bytes, int off, int len) {
/* 69 */     checkState();
/* 70 */     XXHashJNI.XXH64_update(this.state, bytes, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void close() {
/* 75 */     if (this.state != 0L) {
/* 76 */       super.close();
/* 77 */       XXHashJNI.XXH64_free(this.state);
/* 78 */       this.state = 0L;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected synchronized void finalize() throws Throwable {
/* 85 */     super.finalize();
/* 86 */     if (this.state != 0L) {
/*    */       
/* 88 */       XXHashJNI.XXH64_free(this.state);
/* 89 */       this.state = 0L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\StreamingXXHash64JNI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */