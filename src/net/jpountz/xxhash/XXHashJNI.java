/*    */ package net.jpountz.xxhash;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import net.jpountz.util.Native;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ enum XXHashJNI
/*    */ {
/*    */   private static native void init();
/*    */   
/*    */   static native int XXH32(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3);
/*    */   
/*    */   static native int XXH32BB(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3);
/*    */   
/*    */   static native long XXH32_init(int paramInt);
/*    */   
/*    */   static native void XXH32_update(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*    */   
/*    */   static native int XXH32_digest(long paramLong);
/*    */   
/*    */   static {
/* 27 */     Native.load();
/* 28 */     init();
/*    */   }
/*    */   
/*    */   static native void XXH32_free(long paramLong);
/*    */   
/*    */   static native long XXH64(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, long paramLong);
/*    */   
/*    */   static native long XXH64BB(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, long paramLong);
/*    */   
/*    */   static native long XXH64_init(long paramLong);
/*    */   
/*    */   static native void XXH64_update(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*    */   
/*    */   static native long XXH64_digest(long paramLong);
/*    */   
/*    */   static native void XXH64_free(long paramLong);
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHashJNI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */