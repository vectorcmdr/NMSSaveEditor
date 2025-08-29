/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LZ4SafeDecompressor
/*     */   implements LZ4UnknownSizeDecompressor
/*     */ {
/*     */   public abstract int decompress(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, int paramInt4);
/*     */   
/*     */   public abstract int decompress(ByteBuffer paramByteBuffer1, int paramInt1, int paramInt2, ByteBuffer paramByteBuffer2, int paramInt3, int paramInt4);
/*     */   
/*     */   public final int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff) {
/*  77 */     return decompress(src, srcOff, srcLen, dest, destOff, dest.length - destOff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int decompress(byte[] src, byte[] dest) {
/*  90 */     return decompress(src, 0, src.length, dest, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte[] decompress(byte[] src, int srcOff, int srcLen, int maxDestLen) {
/* 118 */     byte[] decompressed = new byte[maxDestLen];
/* 119 */     int decompressedLength = decompress(src, srcOff, srcLen, decompressed, 0, maxDestLen);
/* 120 */     if (decompressedLength != decompressed.length) {
/* 121 */       decompressed = Arrays.copyOf(decompressed, decompressedLength);
/*     */     }
/* 123 */     return decompressed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte[] decompress(byte[] src, int maxDestLen) {
/* 136 */     return decompress(src, 0, src.length, maxDestLen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void decompress(ByteBuffer src, ByteBuffer dest) {
/* 148 */     int decompressed = decompress(src, src.position(), src.remaining(), dest, dest.position(), dest.remaining());
/* 149 */     src.position(src.limit());
/* 150 */     dest.position(dest.position() + decompressed);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 155 */     return getClass().getSimpleName();
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4SafeDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */