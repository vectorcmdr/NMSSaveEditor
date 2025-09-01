package net.jpountz.xxhash;

import java.io.Closeable;
import java.util.zip.Checksum;

public abstract class StreamingXXHash64 implements Closeable {
   final long seed;

   StreamingXXHash64(long seed) {
      this.seed = seed;
   }

   public abstract long getValue();

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
            return StreamingXXHash64.this.getValue();
         }

         public void reset() {
            StreamingXXHash64.this.reset();
         }

         public void update(int b) {
            StreamingXXHash64.this.update(new byte[]{(byte)b}, 0, 1);
         }

         public void update(byte[] b, int off, int len) {
            StreamingXXHash64.this.update(b, off, len);
         }

         public String toString() {
            return StreamingXXHash64.this.toString();
         }
      };
   }

   interface Factory {
      StreamingXXHash64 newStreamingHash(long var1);
   }
}
