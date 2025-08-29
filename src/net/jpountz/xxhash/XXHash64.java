/*    */ package net.jpountz.xxhash;
/*    */ 
/*    */ import java.nio.ByteBuffer;
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
/*    */ public abstract class XXHash64
/*    */ {
/*    */   public abstract long hash(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, long paramLong);
/*    */   
/*    */   public abstract long hash(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, long paramLong);
/*    */   
/*    */   public final long hash(ByteBuffer buf, long seed) {
/* 63 */     long hash = hash(buf, buf.position(), buf.remaining(), seed);
/* 64 */     buf.position(buf.limit());
/* 65 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return getClass().getSimpleName();
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHash64.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */