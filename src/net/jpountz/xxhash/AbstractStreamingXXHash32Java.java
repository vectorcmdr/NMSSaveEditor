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
/*    */ abstract class AbstractStreamingXXHash32Java
/*    */   extends StreamingXXHash32
/*    */ {
/*    */   int v1;
/*    */   int v2;
/*    */   int v3;
/*    */   int v4;
/*    */   int memSize;
/*    */   long totalLen;
/*    */   final byte[] memory;
/*    */   
/*    */   AbstractStreamingXXHash32Java(int seed) {
/* 29 */     super(seed);
/* 30 */     this.memory = new byte[16];
/* 31 */     reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 36 */     this.v1 = this.seed + -1640531535 + -2048144777;
/* 37 */     this.v2 = this.seed + -2048144777;
/* 38 */     this.v3 = this.seed + 0;
/* 39 */     this.v4 = this.seed - -1640531535;
/* 40 */     this.totalLen = 0L;
/* 41 */     this.memSize = 0;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\AbstractStreamingXXHash32Java.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */