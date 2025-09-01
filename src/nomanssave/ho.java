package nomanssave;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class ho extends FilterOutputStream {
   private static final LZ4Factory se = LZ4Factory.safeInstance();
   private final LZ4Compressor sf;
   private byte[] buffer;
   private int sg;
   private int sh;
   private int si;

   public ho(OutputStream var1) {
      super(var1);
      this.sf = se.fastCompressor();
      this.buffer = new byte[1048576];
      this.sg = 0;
      this.sh = 0;
      this.si = 0;
   }

   public void write(int var1) {
      if (this.sg == this.buffer.length) {
         this.ek();
      }

      this.buffer[this.sg++] = (byte)var1;
   }

   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }

   public void write(byte[] var1, int var2, int var3) {
      if (var3 == this.buffer.length) {
         this.ek();
      }

      while(var3 >= this.buffer.length - this.sg) {
         int var4 = this.buffer.length - this.sg;
         System.arraycopy(var1, var2, this.buffer, this.sg, var4);
         this.sg = this.buffer.length;
         this.ek();
         var2 += var4;
         var3 -= var4;
      }

      if (var3 > 0) {
         System.arraycopy(var1, var2, this.buffer, this.sg, var3);
         this.sg += var3;
      }

   }

   private void ek() {
      int var1 = this.sf.maxCompressedLength(this.sg);
      byte[] var2 = new byte[var1];
      int var3 = this.sf.compress((byte[])this.buffer, 0, this.sg, (byte[])var2, 0, var1);
      byte[] var4 = new byte[]{(byte)(255 & this.sg), (byte)(255 & this.sg >> 8), (byte)(255 & this.sg >> 16), (byte)(255 & this.sg >> 24), (byte)(255 & var3), (byte)(255 & var3 >> 8), (byte)(255 & var3 >> 16), (byte)(255 & var3 >> 24)};
      this.out.write(var4);
      this.out.write(var2, 0, var3);
      this.sh += this.sg;
      this.sg = 0;
      this.si += var3 + 8;
   }

   public int ch() {
      return this.sh;
   }

   public int ci() {
      return this.si;
   }

   public void flush() {
      if (this.sg > 0) {
         this.ek();
      }

      this.out.flush();
   }

   public void close() {
      try {
         if (this.sg > 0) {
            this.ek();
         }
      } finally {
         this.out.close();
      }

   }
}
