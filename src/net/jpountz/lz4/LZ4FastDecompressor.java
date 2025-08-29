/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteBuffer;
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
/*     */ public abstract class LZ4FastDecompressor
/*     */   implements LZ4Decompressor
/*     */ {
/*     */   public abstract int decompress(byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, int paramInt2, int paramInt3);
/*     */   
/*     */   public abstract int decompress(ByteBuffer paramByteBuffer1, int paramInt1, ByteBuffer paramByteBuffer2, int paramInt2, int paramInt3);
/*     */   
/*     */   public final int decompress(byte[] src, byte[] dest, int destLen) {
/*  74 */     return decompress(src, 0, dest, 0, destLen);
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
/*     */   public final int decompress(byte[] src, byte[] dest) {
/*  86 */     return decompress(src, dest, dest.length);
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
/*     */   public final byte[] decompress(byte[] src, int srcOff, int destLen) {
/* 108 */     byte[] decompressed = new byte[destLen];
/* 109 */     decompress(src, srcOff, decompressed, 0, destLen);
/* 110 */     return decompressed;
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
/*     */   public final byte[] decompress(byte[] src, int destLen) {
/* 122 */     return decompress(src, 0, destLen);
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
/* 134 */     int read = decompress(src, src.position(), dest, dest.position(), dest.remaining());
/* 135 */     dest.position(dest.limit());
/* 136 */     src.position(src.position() + read);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 141 */     return getClass().getSimpleName();
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4FastDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */