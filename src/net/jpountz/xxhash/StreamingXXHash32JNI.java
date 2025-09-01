package net.jpountz.xxhash;

final class StreamingXXHash32JNI extends StreamingXXHash32 {
   private long state;

   StreamingXXHash32JNI(int seed) {
      super(seed);
      this.state = XXHashJNI.XXH32_init(seed);
   }

   private void checkState() {
      if (this.state == 0L) {
         throw new AssertionError("Already finalized");
      }
   }

   public synchronized void reset() {
      this.checkState();
      XXHashJNI.XXH32_free(this.state);
      this.state = XXHashJNI.XXH32_init(this.seed);
   }

   public synchronized int getValue() {
      this.checkState();
      return XXHashJNI.XXH32_digest(this.state);
   }

   public synchronized void update(byte[] bytes, int off, int len) {
      this.checkState();
      XXHashJNI.XXH32_update(this.state, bytes, off, len);
   }

   public synchronized void close() {
      if (this.state != 0L) {
         super.close();
         XXHashJNI.XXH32_free(this.state);
         this.state = 0L;
      }

   }

   protected synchronized void finalize() throws Throwable {
      super.finalize();
      if (this.state != 0L) {
         XXHashJNI.XXH32_free(this.state);
         this.state = 0L;
      }

   }

   static class Factory implements StreamingXXHash32.Factory {
      public static final StreamingXXHash32.Factory INSTANCE = new StreamingXXHash32JNI.Factory();

      public StreamingXXHash32 newStreamingHash(int seed) {
         return new StreamingXXHash32JNI(seed);
      }
   }
}
