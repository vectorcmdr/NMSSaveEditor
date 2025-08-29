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
/*     */ public class LZ4CompressorWithLength
/*     */ {
/*     */   private final LZ4Compressor compressor;
/*     */   
/*     */   public LZ4CompressorWithLength(LZ4Compressor compressor) {
/*  42 */     this.compressor = compressor;
/*     */   }
/*     */   
/*     */   private void putOriginalLength(byte[] dest, int destOff, int originalLength) {
/*  46 */     dest[destOff] = (byte)originalLength;
/*  47 */     dest[destOff + 1] = (byte)(originalLength >> 8);
/*  48 */     dest[destOff + 2] = (byte)(originalLength >> 16);
/*  49 */     dest[destOff + 3] = (byte)(originalLength >> 24);
/*     */   }
/*     */   
/*     */   private void putOriginalLength(ByteBuffer dest, int destOff, int originalLength) {
/*  53 */     dest.put(destOff, (byte)originalLength);
/*  54 */     dest.put(destOff + 1, (byte)(originalLength >> 8));
/*  55 */     dest.put(destOff + 2, (byte)(originalLength >> 16));
/*  56 */     dest.put(destOff + 3, (byte)(originalLength >> 24));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxCompressedLength(int length) {
/*  66 */     return this.compressor.maxCompressedLength(length) + 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] compress(byte[] src) {
/*  77 */     return compress(src, 0, src.length);
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
/*     */   public byte[] compress(byte[] src, int srcOff, int srcLen) {
/* 101 */     int maxCompressedLength = maxCompressedLength(srcLen);
/* 102 */     byte[] compressed = new byte[maxCompressedLength];
/* 103 */     int compressedLength = compress(src, srcOff, srcLen, compressed, 0);
/* 104 */     return Arrays.copyOf(compressed, compressedLength);
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
/*     */   public int compress(byte[] src, byte[] dest) {
/* 117 */     return compress(src, 0, src.length, dest, 0);
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
/*     */   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff) {
/* 133 */     return compress(src, srcOff, srcLen, dest, destOff, dest.length - destOff);
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
/*     */   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
/* 156 */     int compressedLength = this.compressor.compress(src, srcOff, srcLen, dest, destOff + 4, maxDestLen - 4);
/* 157 */     putOriginalLength(dest, destOff, srcLen);
/* 158 */     return compressedLength + 4;
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
/*     */   public void compress(ByteBuffer src, ByteBuffer dest) {
/* 170 */     int compressedLength = compress(src, src.position(), src.remaining(), dest, dest.position(), dest.remaining());
/* 171 */     src.position(src.limit());
/* 172 */     dest.position(dest.position() + compressedLength);
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
/*     */   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
/* 197 */     int compressedLength = this.compressor.compress(src, srcOff, srcLen, dest, destOff + 4, maxDestLen - 4);
/* 198 */     putOriginalLength(dest, destOff, srcLen);
/* 199 */     return compressedLength + 4;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4CompressorWithLength.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */