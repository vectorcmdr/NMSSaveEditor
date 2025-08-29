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
/*    */ 
/*    */ final class LZ4JNIFastDecompressor
/*    */   extends LZ4FastDecompressor
/*    */ {
/* 32 */   public static final LZ4JNIFastDecompressor INSTANCE = new LZ4JNIFastDecompressor();
/*    */   
/*    */   private static LZ4FastDecompressor SAFE_INSTANCE;
/*    */   
/*    */   public final int decompress(byte[] src, int srcOff, byte[] dest, int destOff, int destLen) {
/* 37 */     SafeUtils.checkRange(src, srcOff);
/* 38 */     SafeUtils.checkRange(dest, destOff, destLen);
/* 39 */     int result = LZ4JNI.LZ4_decompress_fast(src, null, srcOff, dest, null, destOff, destLen);
/* 40 */     if (result < 0) {
/* 41 */       throw new LZ4Exception("Error decoding offset " + (srcOff - result) + " of input buffer");
/*    */     }
/* 43 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public int decompress(ByteBuffer src, int srcOff, ByteBuffer dest, int destOff, int destLen) {
/* 48 */     ByteBufferUtils.checkNotReadOnly(dest);
/* 49 */     ByteBufferUtils.checkRange(src, srcOff);
/* 50 */     ByteBufferUtils.checkRange(dest, destOff, destLen);
/*    */     
/* 52 */     if ((src.hasArray() || src.isDirect()) && (dest.hasArray() || dest.isDirect())) {
/* 53 */       byte[] srcArr = null, destArr = null;
/* 54 */       ByteBuffer srcBuf = null, destBuf = null;
/* 55 */       if (src.hasArray()) {
/* 56 */         srcArr = src.array();
/* 57 */         srcOff += src.arrayOffset();
/*    */       } else {
/* 59 */         assert src.isDirect();
/* 60 */         srcBuf = src;
/*    */       } 
/* 62 */       if (dest.hasArray()) {
/* 63 */         destArr = dest.array();
/* 64 */         destOff += dest.arrayOffset();
/*    */       } else {
/* 66 */         assert dest.isDirect();
/* 67 */         destBuf = dest;
/*    */       } 
/*    */       
/* 70 */       int result = LZ4JNI.LZ4_decompress_fast(srcArr, srcBuf, srcOff, destArr, destBuf, destOff, destLen);
/* 71 */       if (result < 0) {
/* 72 */         throw new LZ4Exception("Error decoding offset " + (srcOff - result) + " of input buffer");
/*    */       }
/* 74 */       return result;
/*    */     } 
/* 76 */     LZ4FastDecompressor safeInstance = SAFE_INSTANCE;
/* 77 */     if (safeInstance == null) {
/* 78 */       safeInstance = SAFE_INSTANCE = LZ4Factory.safeInstance().fastDecompressor();
/*    */     }
/* 80 */     return safeInstance.decompress(src, srcOff, dest, destOff, destLen);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4JNIFastDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */