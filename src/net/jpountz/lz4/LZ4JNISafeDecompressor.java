/*    */ package net.jpountz.lz4;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import net.jpountz.util.ByteBufferUtils;
/*    */ import net.jpountz.util.SafeUtils;
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
/*    */ final class LZ4JNISafeDecompressor
/*    */   extends LZ4SafeDecompressor
/*    */ {
/* 31 */   public static final LZ4JNISafeDecompressor INSTANCE = new LZ4JNISafeDecompressor();
/*    */   
/*    */   private static LZ4SafeDecompressor SAFE_INSTANCE;
/*    */   
/*    */   public final int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
/* 36 */     SafeUtils.checkRange(src, srcOff, srcLen);
/* 37 */     SafeUtils.checkRange(dest, destOff, maxDestLen);
/* 38 */     int result = LZ4JNI.LZ4_decompress_safe(src, null, srcOff, srcLen, dest, null, destOff, maxDestLen);
/* 39 */     if (result < 0) {
/* 40 */       throw new LZ4Exception("Error decoding offset " + (srcOff - result) + " of input buffer");
/*    */     }
/* 42 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public int decompress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
/* 47 */     ByteBufferUtils.checkNotReadOnly(dest);
/* 48 */     ByteBufferUtils.checkRange(src, srcOff, srcLen);
/* 49 */     ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
/*    */     
/* 51 */     if ((src.hasArray() || src.isDirect()) && (dest.hasArray() || dest.isDirect())) {
/* 52 */       byte[] srcArr = null, destArr = null;
/* 53 */       ByteBuffer srcBuf = null, destBuf = null;
/* 54 */       if (src.hasArray()) {
/* 55 */         srcArr = src.array();
/* 56 */         srcOff += src.arrayOffset();
/*    */       } else {
/* 58 */         assert src.isDirect();
/* 59 */         srcBuf = src;
/*    */       } 
/* 61 */       if (dest.hasArray()) {
/* 62 */         destArr = dest.array();
/* 63 */         destOff += dest.arrayOffset();
/*    */       } else {
/* 65 */         assert dest.isDirect();
/* 66 */         destBuf = dest;
/*    */       } 
/*    */       
/* 69 */       int result = LZ4JNI.LZ4_decompress_safe(srcArr, srcBuf, srcOff, srcLen, destArr, destBuf, destOff, maxDestLen);
/* 70 */       if (result < 0) {
/* 71 */         throw new LZ4Exception("Error decoding offset " + (srcOff - result) + " of input buffer");
/*    */       }
/* 73 */       return result;
/*    */     } 
/* 75 */     LZ4SafeDecompressor safeInstance = SAFE_INSTANCE;
/* 76 */     if (safeInstance == null) {
/* 77 */       safeInstance = SAFE_INSTANCE = LZ4Factory.safeInstance().safeDecompressor();
/*    */     }
/* 79 */     return safeInstance.decompress(src, srcOff, srcLen, dest, destOff, maxDestLen);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4JNISafeDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */