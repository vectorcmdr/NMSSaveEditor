/*    */ package net.jpountz.xxhash;
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
/*    */ final class XXHash64JNI
/*    */   extends XXHash64
/*    */ {
/* 26 */   public static final XXHash64 INSTANCE = new XXHash64JNI();
/*    */   
/*    */   private static XXHash64 SAFE_INSTANCE;
/*    */   
/*    */   public long hash(byte[] buf, int off, int len, long seed) {
/* 31 */     SafeUtils.checkRange(buf, off, len);
/* 32 */     return XXHashJNI.XXH64(buf, off, len, seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public long hash(ByteBuffer buf, int off, int len, long seed) {
/* 37 */     if (buf.isDirect()) {
/* 38 */       ByteBufferUtils.checkRange(buf, off, len);
/* 39 */       return XXHashJNI.XXH64BB(buf, off, len, seed);
/* 40 */     }  if (buf.hasArray()) {
/* 41 */       return hash(buf.array(), off + buf.arrayOffset(), len, seed);
/*    */     }
/* 43 */     XXHash64 safeInstance = SAFE_INSTANCE;
/* 44 */     if (safeInstance == null) {
/* 45 */       safeInstance = SAFE_INSTANCE = XXHashFactory.safeInstance().hash64();
/*    */     }
/* 47 */     return safeInstance.hash(buf, off, len, seed);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHash64JNI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */