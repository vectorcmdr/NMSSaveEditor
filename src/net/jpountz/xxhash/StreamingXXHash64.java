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
/*     */ public abstract class StreamingXXHash64
/*     */   implements Closeable
/*     */ {
/*     */   final long seed;
/*     */   
/*     */   StreamingXXHash64(long seed) {
/*  55 */     this.seed = seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract long getValue();
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
/* 106 */           return StreamingXXHash64.this.getValue();
/*     */         }
/*     */ 
/*     */         
/*     */         public void reset() {
/* 111 */           StreamingXXHash64.this.reset();
/*     */         }
/*     */ 
/*     */         
/*     */         public void update(int b) {
/* 116 */           StreamingXXHash64.this.update(new byte[] { (byte)b }, 0, 1);
/*     */         }
/*     */ 
/*     */         
/*     */         public void update(byte[] b, int off, int len) {
/* 121 */           StreamingXXHash64.this.update(b, off, len);
/*     */         }
/*     */ 
/*     */         
/*     */         public String toString() {
/* 126 */           return StreamingXXHash64.this.toString();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static interface Factory {
/*     */     StreamingXXHash64 newStreamingHash(long param1Long);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\StreamingXXHash64.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */