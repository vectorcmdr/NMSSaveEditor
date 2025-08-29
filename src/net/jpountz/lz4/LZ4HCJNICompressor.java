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
/*    */ final class LZ4HCJNICompressor
/*    */   extends LZ4Compressor
/*    */ {
/* 32 */   public static final LZ4HCJNICompressor INSTANCE = new LZ4HCJNICompressor();
/*    */   private static LZ4Compressor SAFE_INSTANCE;
/*    */   private final int compressionLevel;
/*    */   
/*    */   LZ4HCJNICompressor() {
/* 37 */     this(9);
/*    */   } LZ4HCJNICompressor(int compressionLevel) {
/* 39 */     this.compressionLevel = compressionLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
/* 44 */     SafeUtils.checkRange(src, srcOff, srcLen);
/* 45 */     SafeUtils.checkRange(dest, destOff, maxDestLen);
/* 46 */     int result = LZ4JNI.LZ4_compressHC(src, null, srcOff, srcLen, dest, null, destOff, maxDestLen, this.compressionLevel);
/* 47 */     if (result <= 0) {
/* 48 */       throw new LZ4Exception();
/*    */     }
/* 50 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
/* 55 */     ByteBufferUtils.checkNotReadOnly(dest);
/* 56 */     ByteBufferUtils.checkRange(src, srcOff, srcLen);
/* 57 */     ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
/*    */     
/* 59 */     if ((src.hasArray() || src.isDirect()) && (dest.hasArray() || dest.isDirect())) {
/* 60 */       byte[] srcArr = null, destArr = null;
/* 61 */       ByteBuffer srcBuf = null, destBuf = null;
/* 62 */       if (src.hasArray()) {
/* 63 */         srcArr = src.array();
/* 64 */         srcOff += src.arrayOffset();
/*    */       } else {
/* 66 */         assert src.isDirect();
/* 67 */         srcBuf = src;
/*    */       } 
/* 69 */       if (dest.hasArray()) {
/* 70 */         destArr = dest.array();
/* 71 */         destOff += dest.arrayOffset();
/*    */       } else {
/* 73 */         assert dest.isDirect();
/* 74 */         destBuf = dest;
/*    */       } 
/*    */       
/* 77 */       int result = LZ4JNI.LZ4_compressHC(srcArr, srcBuf, srcOff, srcLen, destArr, destBuf, destOff, maxDestLen, this.compressionLevel);
/* 78 */       if (result <= 0) {
/* 79 */         throw new LZ4Exception();
/*    */       }
/* 81 */       return result;
/*    */     } 
/* 83 */     LZ4Compressor safeInstance = SAFE_INSTANCE;
/* 84 */     if (safeInstance == null) {
/* 85 */       safeInstance = SAFE_INSTANCE = LZ4Factory.safeInstance().highCompressor(this.compressionLevel);
/*    */     }
/* 87 */     return safeInstance.compress(src, srcOff, srcLen, dest, destOff, maxDestLen);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4HCJNICompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */