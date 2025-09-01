package nomanssave;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class hb extends FilterOutputStream {
   private static final LZ4Factory se = LZ4Factory.safeInstance();
   private static final int sm = 65536;
   private final LZ4Compressor sf;
   private byte[] buffer;
   private int sg;
   private int si;

   public hb(OutputStream var1) {
      super(var1);
      this.sf = se.fastCompressor();
      this.buffer = new byte[65536];
      this.sg = 0;
      this.si = 0;
   }

   private void aK(int var1) {
      if (this.sg + var1 > this.buffer.length) {
         var1 += this.buffer.length;
         int var2 = (this.buffer.length + var1) / 65536;
         if ((this.buffer.length + var1) % 65536 > 0) {
            ++var2;
         }

         var2 *= 65536;
         byte[] var3 = new byte[var2];
         System.arraycopy(this.buffer, 0, var3, 0, this.sg);
         this.buffer = var3;
      }

   }

   public void write(int var1) {
      this.aK(1);
      this.buffer[this.sg++] = (byte)var1;
   }

   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }

   public void write(byte[] var1, int var2, int var3) {
      this.aK(var3);
      System.arraycopy(var1, var2, this.buffer, this.sg, var3);
      this.sg += var3;
   }

   public int ch() {
      return this.sg;
   }

   public int ci() {
      return this.si;
   }

   public void flush() {
      this.out.flush();
   }

   public void close() {
      try {
         if (this.sg > 0) {
            int var1 = this.sf.maxCompressedLength(this.sg);
            byte[] var2 = new byte[var1];
            this.si = this.sf.compress((byte[])this.buffer, 0, this.sg, (byte[])var2, 0, var1);
            this.out.write(var2, 0, this.si);
         }
      } finally {
         this.out.close();
      }

   }
}
