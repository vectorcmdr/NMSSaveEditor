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
/*    */ public abstract class XXHash32
/*    */ {
/*    */   public abstract int hash(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3);
/*    */   
/*    */   public abstract int hash(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3);
/*    */   
/*    */   public final int hash(ByteBuffer buf, int seed) {
/* 63 */     int hash = hash(buf, buf.position(), buf.remaining(), seed);
/* 64 */     buf.position(buf.limit());
/* 65 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return getClass().getSimpleName();
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHash32.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */