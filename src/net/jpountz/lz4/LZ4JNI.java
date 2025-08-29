/*    */ package net.jpountz.lz4;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ enum LZ4JNI
/*    */ {
/*    */   static native void init();
/*    */   
/*    */   static native int LZ4_compress_limitedOutput(byte[] paramArrayOfbyte1, ByteBuffer paramByteBuffer1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, ByteBuffer paramByteBuffer2, int paramInt3, int paramInt4);
/*    */   
/*    */   static native int LZ4_compressHC(byte[] paramArrayOfbyte1, ByteBuffer paramByteBuffer1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, ByteBuffer paramByteBuffer2, int paramInt3, int paramInt4, int paramInt5);
/*    */   
/*    */   static native int LZ4_decompress_fast(byte[] paramArrayOfbyte1, ByteBuffer paramByteBuffer1, int paramInt1, byte[] paramArrayOfbyte2, ByteBuffer paramByteBuffer2, int paramInt2, int paramInt3);
/*    */   
/*    */   static native int LZ4_decompress_safe(byte[] paramArrayOfbyte1, ByteBuffer paramByteBuffer1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, ByteBuffer paramByteBuffer2, int paramInt3, int paramInt4);
/*    */   
/*    */   static native int LZ4_compressBound(int paramInt);
/*    */   
/*    */   static {
/* 31 */     Native.load();
/* 32 */     init();
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4JNI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */