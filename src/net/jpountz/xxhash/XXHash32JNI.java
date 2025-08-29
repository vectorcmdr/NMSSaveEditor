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
/*    */ final class XXHash32JNI
/*    */   extends XXHash32
/*    */ {
/* 26 */   public static final XXHash32 INSTANCE = new XXHash32JNI();
/*    */   
/*    */   private static XXHash32 SAFE_INSTANCE;
/*    */   
/*    */   public int hash(byte[] buf, int off, int len, int seed) {
/* 31 */     SafeUtils.checkRange(buf, off, len);
/* 32 */     return XXHashJNI.XXH32(buf, off, len, seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hash(ByteBuffer buf, int off, int len, int seed) {
/* 37 */     if (buf.isDirect()) {
/* 38 */       ByteBufferUtils.checkRange(buf, off, len);
/* 39 */       return XXHashJNI.XXH32BB(buf, off, len, seed);
/* 40 */     }  if (buf.hasArray()) {
/* 41 */       return hash(buf.array(), off + buf.arrayOffset(), len, seed);
/*    */     }
/* 43 */     XXHash32 safeInstance = SAFE_INSTANCE;
/* 44 */     if (safeInstance == null) {
/* 45 */       safeInstance = SAFE_INSTANCE = XXHashFactory.safeInstance().hash32();
/*    */     }
/* 47 */     return safeInstance.hash(buf, off, len, seed);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHash32JNI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */