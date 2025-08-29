/*     */ package net.jpountz.xxhash;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.util.zip.Checksum;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class StreamingXXHash32
/*     */   implements Closeable
/*     */ {
/*     */   final int seed;
/*     */   
/*     */   StreamingXXHash32(int seed) {
/*  55 */     this.seed = seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void update(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void reset();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  92 */     return getClass().getSimpleName() + "(seed=" + this.seed + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Checksum asChecksum() {
/* 102 */     return new Checksum()
/*     */       {
/*     */         public long getValue()
/*     */         {
/* 106 */           return StreamingXXHash32.this.getValue() & 0xFFFFFFFL;
/*     */         }
/*     */ 
/*     */         
/*     */         public void reset() {
/* 111 */           StreamingXXHash32.this.reset();
/*     */         }
/*     */ 
/*     */         
/*     */         public void update(int b) {
/* 116 */           StreamingXXHash32.this.update(new byte[] { (byte)b }, 0, 1);
/*     */         }
/*     */ 
/*     */         
/*     */         public void update(byte[] b, int off, int len) {
/* 121 */           StreamingXXHash32.this.update(b, off, len);
/*     */         }
/*     */ 
/*     */         
/*     */         public String toString() {
/* 126 */           return StreamingXXHash32.this.toString();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static interface Factory {
/*     */     StreamingXXHash32 newStreamingHash(int param1Int);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\StreamingXXHash32.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */