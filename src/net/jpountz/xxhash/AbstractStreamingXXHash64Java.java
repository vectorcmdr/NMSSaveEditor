package net.jpountz.xxhash;

abstract class AbstractStreamingXXHash64Java extends StreamingXXHash64 {
   int memSize;
   long v1;
   long v2;
   long v3;
   long v4;
   long totalLen;
   final byte[] memory = new byte[32];

   AbstractStreamingXXHash64Java(long seed) {
      super(seed);
      this.reset();
   }

   public void reset() {
      this.v1 = this.seed + -7046029288634856825L + -4417276706812531889L;
      this.v2 = this.seed + -4417276706812531889L;
      this.v3 = this.seed + 0L;
      this.v4 = this.seed - -7046029288634856825L;
      this.totalLen = 0L;
      this.memSize = 0;
   }
}
