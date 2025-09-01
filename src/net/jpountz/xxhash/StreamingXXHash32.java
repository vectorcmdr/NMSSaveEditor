package net.jpountz.xxhash;

import java.io.Closeable;
import java.util.zip.Checksum;

public abstract class StreamingXXHash32 implements Closeable {
   final int seed;

   StreamingXXHash32(int seed) {
      this.seed = seed;
   }

   public abstract int getValue();

   public abstract void update(byte[] var1, int var2, int var3);

   public abstract void reset();

   public void close() {
   }

   public String toString() {
      return this.getClass().getSimpleName() + "(seed=" + this.seed + ")";
   }

   public final Checksum asChecksum() {
      return new Checksum() {
         public long getValue() {
            return (long)StreamingXXHash32.this.getValue() & 268435455L;
         }

         public void reset() {
            StreamingXXHash32.this.reset();
         }

         public void update(int b) {
            StreamingXXHash32.this.update(new byte[]{(byte)b}, 0, 1);
         }

         public void update(byte[] b, int off, int len) {
            StreamingXXHash32.this.update(b, off, len);
         }

         public String toString() {
            return StreamingXXHash32.this.toString();
         }
      };
   }

   interface Factory {
      StreamingXXHash32 newStreamingHash(int var1);
   }
}
