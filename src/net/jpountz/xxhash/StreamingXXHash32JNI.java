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
/*    */ final class StreamingXXHash32JNI
/*    */   extends StreamingXXHash32
/*    */ {
/*    */   private long state;
/*    */   
/*    */   static class Factory
/*    */     implements StreamingXXHash32.Factory
/*    */   {
/* 31 */     public static final StreamingXXHash32.Factory INSTANCE = new Factory();
/*    */ 
/*    */     
/*    */     public StreamingXXHash32 newStreamingHash(int seed) {
/* 35 */       return new StreamingXXHash32JNI(seed);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   StreamingXXHash32JNI(int seed) {
/* 43 */     super(seed);
/* 44 */     this.state = XXHashJNI.XXH32_init(seed);
/*    */   }
/*    */   
/*    */   private void checkState() {
/* 48 */     if (this.state == 0L) {
/* 49 */       throw new AssertionError("Already finalized");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void reset() {
/* 55 */     checkState();
/* 56 */     XXHashJNI.XXH32_free(this.state);
/* 57 */     this.state = XXHashJNI.XXH32_init(this.seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized int getValue() {
/* 62 */     checkState();
/* 63 */     return XXHashJNI.XXH32_digest(this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void update(byte[] bytes, int off, int len) {
/* 68 */     checkState();
/* 69 */     XXHashJNI.XXH32_update(this.state, bytes, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void close() {
/* 74 */     if (this.state != 0L) {
/* 75 */       super.close();
/* 76 */       XXHashJNI.XXH32_free(this.state);
/* 77 */       this.state = 0L;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected synchronized void finalize() throws Throwable {
/* 83 */     super.finalize();
/* 84 */     if (this.state != 0L) {
/*    */       
/* 86 */       XXHashJNI.XXH32_free(this.state);
/* 87 */       this.state = 0L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\StreamingXXHash32JNI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */