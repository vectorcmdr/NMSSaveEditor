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
/*     */ public class LZ4DecompressorWithLength
/*     */ {
/*     */   private final LZ4FastDecompressor fastDecompressor;
/*     */   private final LZ4SafeDecompressor safeDecompressor;
/*     */   
/*     */   public static int getDecompressedLength(byte[] src) {
/*  42 */     return getDecompressedLength(src, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDecompressedLength(byte[] src, int srcOff) {
/*  53 */     return src[srcOff] & 0xFF | (src[srcOff + 1] & 0xFF) << 8 | (src[srcOff + 2] & 0xFF) << 16 | src[srcOff + 3] << 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDecompressedLength(ByteBuffer src) {
/*  63 */     return getDecompressedLength(src, src.position());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDecompressedLength(ByteBuffer src, int srcOff) {
/*  74 */     return src.get(srcOff) & 0xFF | (src.get(srcOff + 1) & 0xFF) << 8 | (src.get(srcOff + 2) & 0xFF) << 16 | src.get(srcOff + 3) << 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4DecompressorWithLength(LZ4FastDecompressor fastDecompressor) {
/*  85 */     this.fastDecompressor = fastDecompressor;
/*  86 */     this.safeDecompressor = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4DecompressorWithLength(LZ4SafeDecompressor safeDecompressor) {
/*  95 */     this.fastDecompressor = null;
/*  96 */     this.safeDecompressor = safeDecompressor;
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
/*     */   public int decompress(byte[] src, byte[] dest) {
/* 108 */     return decompress(src, 0, dest, 0);
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
/*     */   public int decompress(byte[] src, int srcOff, byte[] dest, int destOff) {
/* 126 */     if (this.safeDecompressor != null) {
/* 127 */       return decompress(src, srcOff, src.length - srcOff, dest, destOff);
/*     */     }
/* 129 */     int destLen = getDecompressedLength(src, srcOff);
/* 130 */     return this.fastDecompressor.decompress(src, srcOff + 4, dest, destOff, destLen) + 4;
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
/*     */   public int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff) {
/* 149 */     if (this.safeDecompressor == null) {
/* 150 */       return decompress(src, srcOff, dest, destOff);
/*     */     }
/* 152 */     int destLen = getDecompressedLength(src, srcOff);
/* 153 */     return this.safeDecompressor.decompress(src, srcOff + 4, srcLen - 4, dest, destOff, destLen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] decompress(byte[] src) {
/* 164 */     return decompress(src, 0);
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
/*     */   public byte[] decompress(byte[] src, int srcOff) {
/* 181 */     if (this.safeDecompressor != null) {
/* 182 */       return decompress(src, srcOff, src.length - srcOff);
/*     */     }
/* 184 */     int destLen = getDecompressedLength(src, srcOff);
/* 185 */     return this.fastDecompressor.decompress(src, srcOff + 4, destLen);
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
/*     */   public byte[] decompress(byte[] src, int srcOff, int srcLen) {
/* 203 */     if (this.safeDecompressor == null) {
/* 204 */       return decompress(src, srcOff);
/*     */     }
/* 206 */     int destLen = getDecompressedLength(src, srcOff);
/* 207 */     return this.safeDecompressor.decompress(src, srcOff + 4, srcLen - 4, destLen);
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
/*     */   public void decompress(ByteBuffer src, ByteBuffer dest) {
/* 220 */     int destLen = getDecompressedLength(src, src.position());
/* 221 */     if (this.safeDecompressor == null) {
/* 222 */       int read = this.fastDecompressor.decompress(src, src.position() + 4, dest, dest.position(), destLen);
/* 223 */       src.position(src.position() + 4 + read);
/* 224 */       dest.position(dest.position() + destLen);
/*     */     } else {
/* 226 */       int written = this.safeDecompressor.decompress(src, src.position() + 4, src.remaining() - 4, dest, dest.position(), destLen);
/* 227 */       src.position(src.limit());
/* 228 */       dest.position(dest.position() + written);
/*     */     } 
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
/*     */   public int decompress(ByteBuffer src, int srcOff, ByteBuffer dest, int destOff) {
/* 247 */     if (this.safeDecompressor != null) {
/* 248 */       return decompress(src, srcOff, src.remaining() - srcOff, dest, destOff);
/*     */     }
/* 250 */     int destLen = getDecompressedLength(src, srcOff);
/* 251 */     return this.fastDecompressor.decompress(src, srcOff + 4, dest, destOff, destLen) + 4;
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
/*     */   public int decompress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff) {
/* 271 */     if (this.safeDecompressor == null) {
/* 272 */       return decompress(src, srcOff, dest, destOff);
/*     */     }
/* 274 */     int destLen = getDecompressedLength(src, srcOff);
/* 275 */     return this.safeDecompressor.decompress(src, srcOff + 4, srcLen - 4, dest, destOff, destLen);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4DecompressorWithLength.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */